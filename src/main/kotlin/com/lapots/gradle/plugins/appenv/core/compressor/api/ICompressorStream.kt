package com.lapots.gradle.plugins.appenv.core.compressor.api

import java.io.InputStream

/**
 * Interface for different decompress streams.
 */
interface ICompressorStream {
    fun getUnpackStream(inputStream: InputStream) : InputStream
}
