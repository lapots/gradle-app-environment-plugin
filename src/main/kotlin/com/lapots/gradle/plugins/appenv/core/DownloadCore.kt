package com.lapots.gradle.plugins.appenv.core

import mu.KLogging
import org.apache.commons.io.FilenameUtils
import java.net.URL
import java.nio.file.Files
import java.nio.file.StandardCopyOption

/**
 * Does downloading.
 */
class DownloadCore(extension: ApplicationEnvironmentExtension):
        AbstractExecutor(extension) {

    companion object : KLogging()

    override fun execute() {
        logger.info { "Attempt to download from ${extension.srcLink} " +
                "and save into ${extension.downloadPath}" }
        val filename = FilenameUtils.getName(extension.srcLink)
        val installPath = PluginUtils.buildAbsolutePath(extension.downloadPath, filename)
        URL(extension.srcLink).openStream().use {
            if (!Files.exists(installPath)) {
                Files.createDirectories(installPath)
                logger.info { "Proceed downloading to $installPath" }
                // allows downloading from URL !!!
                Files.copy(it, installPath, StandardCopyOption.REPLACE_EXISTING)
            } else {
                logger.info { "File $installPath already exists!" }
            }
        }
    }
}
