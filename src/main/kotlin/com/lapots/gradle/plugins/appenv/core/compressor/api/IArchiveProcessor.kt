package com.lapots.gradle.plugins.appenv.core.compressor.api

import java.io.InputStream

/**
 * Interface for different decompress streams.
 */
@Deprecated("Moved to Groovy")
interface IArchiveProcessor {
    fun process(inputStream: InputStream, destination: String): Int
}
