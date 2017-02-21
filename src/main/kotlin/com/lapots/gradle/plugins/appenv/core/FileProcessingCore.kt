package com.lapots.gradle.plugins.appenv.core

import com.lapots.gradle.plugins.appenv.core.PluginConstants.SUPPORTED_ARCHIVE_EXTENSIONS
import com.lapots.gradle.plugins.appenv.core.compressor.UnpackStream
import com.lapots.gradle.plugins.appenv.core.process.ProcessStream
import mu.KLogging
import org.apache.commons.io.FilenameUtils

/**
 * Processes downloaded file.
 */
class FileProcessingCore(extension: ApplicationEnvironmentExtension): AbstractExecutor(extension) {
    companion object : KLogging()

    override fun execute() {
        processFile(FilenameUtils.getName(extension.srcLink))
    }

    private fun processFile(file: String) {
        when (FilenameUtils.getExtension(file)) {
            in SUPPORTED_ARCHIVE_EXTENSIONS -> processArchive(file)
            "msi"   -> processMsi(file)
            else    -> {
                logger.info { "Cannot process further: $file"}
            }
        }
    }

    private fun processArchive(archive: String) {
        logger.debug { "Unpacking archive $archive" }

        val file = PluginUtils.buildPath(extension.downloadPath, archive)
        logger.info { "Attempt to unpack archive $file" }
        UnpackStream() do_unpack {
            from { file } // /downloads/file.tar.xz
            to { extension.installPath } // /installations
        }

        // in case of multiple extension archive
        processFile(FilenameUtils.getBaseName(file))
    }

    private fun processMsi(msi: String) {
        logger.info { "Attempt to process $msi" }

        val file = PluginUtils.buildPath(extension.downloadPath, msi)
        logger.info { "Attempt to install $file" }
        ProcessStream() do_install {
            from { file }
            to { extension.installPath }
        }
    }
}
