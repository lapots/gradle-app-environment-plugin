package com.lapots.gradle.plugins.appenv.core

import com.lapots.gradle.plugins.appenv.core.dsl.UnpackStream
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
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
        val xzContentName =
                UnpackStream().unpack {
                    from { src }
        }

        // delegate further processing
        install(xzContentName)
    }

    private fun unpackTar(src: String) {
        println("Attempt to unpack TAR")

        val tarContentName =
                UnpackStream().unpack {
                    from { src }
        }

        install(tarContentName)
    }

    // general trouble with MSI is that it varies from version
    // to version of Windows OS
    private fun installMsi(src: String) {
        println("Attempt to install MSI")
    }
}