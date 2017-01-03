package com.lapots.gradle.plugins.appenv.core.dsl

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.IOUtils
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * DSL for fancy unpacking.
 */
class UnpackStream {
    var source: String? = null

    fun unpack(closure: UnpackStream.() -> Unit) : String {
        closure() // self delegating
        return processResource()
    }

    fun from(closure: UnpackStream.() -> String) : UnpackStream {
        source = closure()
        return this
    }

    private fun processResource() : String {
        val inputFileStream = FileInputStream(source)
        val inputStream = BufferedInputStream(inputFileStream)

        // output file name
        val outputFileName = ""
        val outputStream = FileOutputStream(outputFileName)

        val compressorInput = UnpackFactory
                .getUnpacker(FilenameUtils.getExtension(source))
                ?.getUnpackStream(inputStream)

        compressorInput ?:
            throw IllegalStateException("Not found compressor for extension!")

        IOUtils.copy(compressorInput, outputStream)

        return outputFileName
    }
}
