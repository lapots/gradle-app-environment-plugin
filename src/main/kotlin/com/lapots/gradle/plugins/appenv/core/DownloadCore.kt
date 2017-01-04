package com.lapots.gradle.plugins.appenv.core

import com.lapots.gradle.plugins.appenv.ApplicationEnvironmentExtension
import com.lapots.gradle.plugins.appenv.core.PluginConstants.DEFAULT_SEPARATOR
import mu.KLogging
import org.apache.commons.io.FilenameUtils
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

/**
 * Does downloading.
 */
class DownloadCore(val extension: ApplicationEnvironmentExtension) : KLogging() {

    fun execute() {
        logger.debug { "Attempt to download from ${extension.srcLink} " +
                "and save into ${extension.installPath}" }
        val filename = FilenameUtils.getName(extension.srcLink)
        val installPath = Paths.get(
                FilenameUtils.separatorsToSystem(extension.installPath + DEFAULT_SEPARATOR + filename)
        )
        URL(extension.srcLink).openStream().use {
            if (!Files.exists(installPath)) { Files.createDirectories(installPath) }
            logger.info { "Proceed downloading to $installPath" }
            // allows downloading from URL !!!
            Files.copy(it, installPath, StandardCopyOption.REPLACE_EXISTING)
        }
    }
}
