package com.lapots.gradle.plugins.appenv.core.dsl

import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream
import java.io.InputStream

/**
 * Factory for different unpack algorithms.
 */
object UnpackFactory {
    val unpackMap = mapOf(
            "xz" to XZCompressorStream,
            "tar" to TarCompressorStream
    )

    interface ICompressorStream {
        fun getUnpackStream(inputStream: InputStream) : InputStream
    }

    object XZCompressorStream : ICompressorStream {
        override fun getUnpackStream(inputStream: InputStream): InputStream {
            return XZCompressorInputStream(inputStream)
        }
    }

    object TarCompressorStream : ICompressorStream {
        override fun getUnpackStream(inputStream: InputStream): InputStream {
            return TarArchiveInputStream(inputStream)
        }

    }

    fun getUnpacker(extension: String) : ICompressorStream? {
        return unpackMap[extension]
    }
}
