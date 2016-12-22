package com.lapots.gradle.plugins.appenv.core.download

import java.net.URL

/**
 * {@link IDownloader} implementation for HTTP resources
 */
class HttpDownloader(val link: URL) : IDownloader {
    override fun download() {
        throw UnsupportedOperationException("not implemented")
    }
}
