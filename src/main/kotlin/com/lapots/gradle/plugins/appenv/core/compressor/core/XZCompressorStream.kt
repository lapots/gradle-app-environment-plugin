package com.lapots.gradle.plugins.appenv.core.compressor.core

import com.lapots.gradle.plugins.appenv.core.compressor.api.ICompressorStream
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream
import java.io.InputStream

/**
 * Implementation of {@link ICompressorStream} for XZ compressor.
 */
object XZCompressorStream : ICompressorStream {
    override fun getUnpackStream(inputStream: InputStream): InputStream {
        return XZCompressorInputStream(inputStream)
    }
}
