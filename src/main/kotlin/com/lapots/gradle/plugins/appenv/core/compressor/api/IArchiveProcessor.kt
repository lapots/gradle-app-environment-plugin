package com.lapots.gradle.plugins.appenv.core.compressor.api

import java.io.InputStream

/**
 * Interface for different decompress streams.
 */
interface IArchiveProcessor {
    fun process(inputStream: InputStream, destination: String): Int
}
