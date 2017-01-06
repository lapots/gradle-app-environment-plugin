package com.lapots.gradle.plugins.appenv.core.compressor.core

import com.lapots.gradle.plugins.appenv.core.compressor.api.IArchiveProcessor
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream
import org.apache.commons.io.IOUtils
import java.io.FileOutputStream

import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Implementation of {@link IArchiveProcessor} for XZ compression.
 */
object XZArchiveProcessor : IArchiveProcessor {
    override fun process(inputStream: InputStream, destination: String): Int {
        val archiveStream = XZCompressorInputStream(inputStream)

        val destinationPath = Paths.get(destination)
        // check corresponding item existence
        if (!Files.exists(destinationPath)) { Files.createDirectories(destinationPath) }

        IOUtils.copy(archiveStream, FileOutputStream(destination))

        archiveStream.close()
        return 0
    }
}
