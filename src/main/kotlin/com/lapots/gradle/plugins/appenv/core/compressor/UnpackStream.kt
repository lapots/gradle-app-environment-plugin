package com.lapots.gradle.plugins.appenv.core.compressor

import com.lapots.gradle.plugins.appenv.core.PluginUtils
import com.lapots.gradle.plugins.appenv.core.compressor.api.IArchiveProcessor
import com.lapots.gradle.plugins.appenv.core.compressor.core.TarArchiveProcessor
import com.lapots.gradle.plugins.appenv.core.compressor.core.XZArchiveProcessor
import mu.KLogging

import org.apache.commons.io.FilenameUtils

import java.io.BufferedInputStream
import java.io.FileInputStream

/**
 * DSL for fancy unpacking.
 */
@Deprecated("Moved to Groovy")
class UnpackStream  {
    var archive = "" // /downloads/file.tar.xz
    var destination = "" // /installations

    companion object : KLogging()

    infix fun do_unpack(closure: UnpackStream.() -> Unit) {
        closure() // self delegating
        processResource()
    }

    fun from(closure: () -> String) : UnpackStream {
        archive = closure()
        return this
    }

    fun to(closure: () -> String) : UnpackStream {
        destination = closure()
        return this
    }

    private fun processResource() {
        logger.debug { "Attempt to process resource $archive" }
        // if no destination than assuming that unpack into source directory
        if (destination.isEmpty()) { destination = FilenameUtils.getPath(archive) }

        FileInputStream(archive).use {  inputFile ->
            BufferedInputStream(inputFile).use {
                val compressorInput = (CompressorContainer provide_compressor_for archive)
                if (null != compressorInput) {
                    val destFile = PluginUtils.buildPath(destination, FilenameUtils.getBaseName(archive))
                    compressorInput.process(it, destFile)
                } else {
                    throw IllegalStateException("Not found de-compressor for extension!")
                }
            }
        }
    }

    // investigate Apache Compress in-built factories
    object CompressorContainer {
        val unpackMap = mapOf(
                "xz" to XZArchiveProcessor,
                "tar" to TarArchiveProcessor
        )

        infix fun provide_compressor_for(filename: String?) : IArchiveProcessor? {
            return unpackMap[FilenameUtils.getExtension(filename)]
        }
    }
}
