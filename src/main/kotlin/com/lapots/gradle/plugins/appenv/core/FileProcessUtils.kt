package com.lapots.gradle.plugins.appenv.core

import org.apache.commons.io.FilenameUtils
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
                // were are done
            }
        }
    }

    private fun unpackXz(src: String) {
        val inputFileStream = FileInputStream(src)
        val inputStream = BufferedInputStream(inputFileStream)
        // remove xz extension
        val xzContentName = ""
        val outputStream = FileOutputStream(xzContentName)
        val compressorInput = XZCompressorInputStream(inputStream)

        val buffer = byte[1000]
        val n = 0
        while (-1 != (n = compressorInput.read(buffer))) {
            outputStream.write(buffer, 0, n)
        }
        outputStream.close()
        compressorInput.close()

        // delegate further processing
        install(xzContentName)
    }

    private fun unpackTar(src: String) {

    }

    private fun installMsi(src: String) {
    }
}