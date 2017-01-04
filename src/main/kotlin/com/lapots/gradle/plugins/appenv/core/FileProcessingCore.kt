package com.lapots.gradle.plugins.appenv.core

import com.lapots.gradle.plugins.appenv.ApplicationEnvironmentExtension
import com.lapots.gradle.plugins.appenv.core.PluginConstants.DEFAULT_SEPARATOR
import com.lapots.gradle.plugins.appenv.core.PluginConstants.SUPPORTED_ARCHIVE_EXTENSIONS
import com.lapots.gradle.plugins.appenv.core.compressor.UnpackStream
import mu.KLogging
import org.apache.commons.io.FilenameUtils

/**
 * Processes downloaded file.
 */
class FileProcessingCore(val extension: ApplicationEnvironmentExtension) {

    companion object : KLogging()

    fun execute() {
        val ext = FilenameUtils.getExtension(extension.srcLink)
        when (ext) {
            in SUPPORTED_ARCHIVE_EXTENSIONS -> processArchive()
            "msi"   -> processMsi()
            else    -> {
                logger.info { "Cannot process further: ${ extension.srcLink }"}
            }
        }
    }

    private fun processArchive() {
        logger.debug { "Unpacking archive ${extension.srcLink}" }

        val filename = FilenameUtils.getName(extension.srcLink)
        val downloadPath = extension.downloadPath

        val file = FilenameUtils.separatorsToSystem(downloadPath + DEFAULT_SEPARATOR + filename)

        UnpackStream() do_unpack {
            from { file } // /downloads/file.tar.xz
            to { extension.installPath } // /installations
        }
    }

    private fun processMsi() {
        // investigate common-exec library
        logger.info { "MSI processing currently unsupported!"}
    }
}
