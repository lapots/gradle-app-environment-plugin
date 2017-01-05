package com.lapots.gradle.plugins.appenv.core

import com.lapots.gradle.plugins.appenv.ApplicationEnvironmentExtension
import com.lapots.gradle.plugins.appenv.core.PluginConstants.DEFAULT_SEPARATOR
import com.lapots.gradle.plugins.appenv.core.PluginConstants.SUPPORTED_ARCHIVE_EXTENSIONS
import com.lapots.gradle.plugins.appenv.core.compressor.UnpackStream
import com.lapots.gradle.plugins.appenv.core.process.ProcessStream
import mu.KLogging
import org.apache.commons.io.FilenameUtils

/**
 * Processes downloaded file.
 */
class FileProcessingCore(val extension: ApplicationEnvironmentExtension) {

    companion object : KLogging()

    fun execute() {
        // TODO: adjust logic to support downloadPath instead of srcLink
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

        logger.info { "Attempt to unpack archive $file" }
        UnpackStream() do_unpack {
            from { file } // /downloads/file.tar.xz
            to { extension.installPath } // /installations
        }
    }

    private fun processMsi() {
        // investigate common-exec library
        logger.info { "Attempt to process msi" }

        val filename = FilenameUtils.getName(extension.srcLink)
        val downloadPath = extension.downloadPath

        val file = FilenameUtils.separatorsToSystem(downloadPath + DEFAULT_SEPARATOR + filename)

        logger.info { "Attempt to install $file" }
        ProcessStream() do_install {
            from { file }
            to { extension.installPath }
        }
    }
}
