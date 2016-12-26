package com.lapots.gradle.plugins.appenv.core

import com.lapots.gradle.plugins.appenv.core.download.DownloadClientFactory
import org.apache.commons.io.*
import org.apache.http.HttpResponse
import java.io.File

/**
 * Class for downloading resource from the link.
 */
object ResourceController {

    val downloadClientFactory = DownloadClientFactory()

    fun download(link: String, storePath: String) {
        val client = downloadClientFactory.getClient(link)
        val response = client?.download()
        saveHttpResponseToFile(response, storePath)
    }

    private fun saveHttpResponseToFile(response: HttpResponse, storePath: String) {
        FileUtils.copyInputStreamToFile(
                response.getEntity().getContent(), File(storePath)
        )
    }
}
