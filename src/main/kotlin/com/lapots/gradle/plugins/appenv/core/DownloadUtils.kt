package com.lapots.gradle.plugins.appenv.core

import org.apache.commons.io.FilenameUtils
import java.net.URL
import java.nio.file.*

/**
 * Class for downloading resource from the link.
 */
object DownloadUtils {

    fun download(link: String, storePath: String) {
        println("Attempt to download from $link and save into $storePath")
        downloadWithFuel(link, storePath)
    }

    private fun downloadWithFuel(linkUrl: String, storePath: String) {
        println("Filename: ${ FilenameUtils.getName(linkUrl) }")
        val finalPath = FilenameUtils.separatorsToSystem(storePath + "/" + FilenameUtils.getName(linkUrl))
        val path = Paths.get(finalPath)
        URL(linkUrl).openStream().use {
            if (!Files.exists(path)) { Files.createDirectories(path) }
            // adjust to create if not exist
            Files.copy(it, path, StandardCopyOption.REPLACE_EXISTING)
        }
    }

}
