package com.lapots.gradle.plugins.appenv.core.compressor.core

import com.lapots.gradle.plugins.appenv.core.compressor.api.ICompressorStream
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import java.io.InputStream

/**
 * Implementation of {@link ICompressorStream} for TAR compressor.
 */
object TarCompressorStream : ICompressorStream {
    override fun getUnpackStream(inputStream: InputStream): InputStream {
        return TarArchiveInputStream(inputStream)
    }
}
