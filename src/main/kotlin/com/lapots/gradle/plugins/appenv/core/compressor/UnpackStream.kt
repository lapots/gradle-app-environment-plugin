package com.lapots.gradle.plugins.appenv.core.compressor

import com.lapots.gradle.plugins.appenv.core.PluginConstants.DEFAULT_SEPARATOR
import com.lapots.gradle.plugins.appenv.core.compressor.api.ICompressorStream
import com.lapots.gradle.plugins.appenv.core.compressor.core.TarCompressorStream
import com.lapots.gradle.plugins.appenv.core.compressor.core.XZCompressorStream
import com.lapots.gradle.plugins.appenv.core.exception.IOPluginException
import mu.KLogging
import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.IOUtils
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * DSL for fancy unpacking.
 */
class UnpackStream  {
    lateinit var source : String // /downloads/file.tar.xz
    var destination = "" // /installations

    companion object : KLogging()

    fun file(name: String) : File = File(name).apply {
        if (!parentFile.mkdirs()) {
            throw IOPluginException("Unable to create folder $parentFile")
        }
    }

    infix fun do_unpack(closure: UnpackStream.() -> Unit) : String {
        closure() // self delegating
        return processResource()
    }

    fun from(closure: () -> String) : UnpackStream {
        source = closure()
        return this
    }

    fun to(closure: () -> String) : UnpackStream {
        destination = closure()
        return this
    }

    private fun processResource() : String {
        logger.debug { "Attempt to process resource $source" }
        FileInputStream(source).use {  inputFile ->
            BufferedInputStream(inputFile).use {
                val compressorInput = (CompressorContainer provide_compressor_for source) ?.getUnpackStream(it)
                compressorInput ?: throw IllegalStateException("Not found de-compressor for extension!")

                // if no destination than assuming that unpack into source directory
                if (destination.isEmpty()) { destination = FilenameUtils.getPath(source) }

                // as it archive it has extension by default
                val outputFileName = destination + DEFAULT_SEPARATOR + FilenameUtils.getBaseName(source)
                val newFile = file(outputFileName)

                logger.info { "Attempt to unpack $source into $outputFileName" }
                IOUtils.copy(compressorInput, FileOutputStream(newFile))

                return outputFileName
            }
        }
    }

    object CompressorContainer {
        val unpackMap = mapOf(
                "xz" to XZCompressorStream,
                "tar" to TarCompressorStream
        )

        infix fun provide_compressor_for(filename: String?) : ICompressorStream? {
            return unpackMap[FilenameUtils.getExtension(filename)]
        }
    }
}
