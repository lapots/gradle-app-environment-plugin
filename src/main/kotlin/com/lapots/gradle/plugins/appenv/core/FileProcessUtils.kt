package com.lapots.gradle.plugins.appenv.core

import org.apache.commons.compress.compressors.xz.XZCompressorInputStream
import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.IOUtils
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Class for unpacking (or installing) downloaded files.
 */
object FileProcessUtils {

    fun install(src: String) {
        when (FilenameUtils.getExtension(src)) {
            "msi"   -> installMsi(src)
            "xz"    -> unpackXz(src)
            "tar"   -> unpackTar(src)
            else -> {
                println("Unable to process further: $src")
                // were are done
            }
        }
    }

    private fun unpackXz(src: String) {
        println("Attempt to unpack XZ")

        val inputFileStream = FileInputStream(src)
        val inputStream = BufferedInputStream(inputFileStream)
        // remove xz extension
        val xzContentName = ""
        val outputStream = FileOutputStream(xzContentName)
        val compressorInput = XZCompressorInputStream(inputStream)

        IOUtils.copy(compressorInput, outputStream)

        // delegate further processing
        install(xzContentName)
    }

    private fun unpackTar(src: String) {
        println("Attempt to unpack TAR")
    }

    // general trouble with MSI is that it varies from version
    // to version of Windows OS
    private fun installMsi(src: String) {
        println("Attempt to install MSI")
    }
}