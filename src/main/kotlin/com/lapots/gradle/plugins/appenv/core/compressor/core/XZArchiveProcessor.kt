package com.lapots.gradle.plugins.appenv.core.compressor.core

import com.lapots.gradle.plugins.appenv.core.compressor.api.IArchiveProcessor
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileOutputStream

import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Implementation of {@link IArchiveProcessor} for XZ compression.
 *
 * Generally .xz compresses single files so we should not check
 * where the .xz content is directory.
 */
@Deprecated("Moved to Groovy")
object XZArchiveProcessor : IArchiveProcessor {

    fun file(absolutePath: String) : File = File(absolutePath).apply {
        if (!this.exists()) {
            createNewFile()
        }
    }

    override fun process(inputStream: InputStream, destination: String): Int {
        val archiveStream = XZCompressorInputStream(inputStream)

        val file = file(destination)
        IOUtils.copy(archiveStream, FileOutputStream(file))

        archiveStream.close()
        return 0
    }
}
