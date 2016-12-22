package com.lapots.gradle.plugins.appenv.core.download

import java.net.URL

/**
 * Factory that allows to handle http, https resources.
 */
class DownloadClientFactory {
    /**
     * Returns corresponding client for url.
     */
    fun getClient(resource: String) : IDownloader? {
        // get type from URL
        when (resource) {
            "http" -> return HttpDownloader(URL(resource))
        }
        return null
    }
}
