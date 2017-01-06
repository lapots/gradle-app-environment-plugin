package com.lapots.gradle.plugins.appenv.core.compressor.core

import com.lapots.gradle.plugins.appenv.core.PluginUtils
import com.lapots.gradle.plugins.appenv.core.compressor.api.IArchiveProcessor
import mu.KLogging
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileOutputStream

import java.io.InputStream

/**
 * Implementation of {@link IArchiveProcessor} for TAR compression.
 */
object TarArchiveProcessor : IArchiveProcessor, KLogging() {

    override fun process(inputStream: InputStream, destination: String): Int {
        val archiveStream = TarArchiveInputStream(inputStream)

        var archiveEntry = archiveStream.nextTarEntry
        while(archiveEntry != null) {
            archiveEntry = archiveStream.nextTarEntry

            val file = File(PluginUtils.buildPath(destination, archiveEntry.name))
            if (!file.parentFile.exists()) { file.parentFile.mkdirs() }

            if (archiveEntry.isDirectory) { file.mkdirs() }
            else { IOUtils.copy(archiveStream, FileOutputStream(file)) }
        }

        archiveStream.close()
        return 0
    }
}
