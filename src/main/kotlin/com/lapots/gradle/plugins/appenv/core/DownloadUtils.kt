package com.lapots.gradle.plugins.appenv.core

import com.github.kittinunf.fuel.Fuel
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.apache.http.HttpResponse
import org.apache.http.client.ResponseHandler
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import java.io.File
import java.net.URL

/**
 * Class for downloading resource from the link.
 */
object DownloadUtils {

    fun download(link: String, storePath: String) {
        // downloadWithApacheHttp(link, storePath)
        println("Attempt to download from $link and save into $storePath")
        downloadWithFuel(link, storePath)
    }
/*
    private fun downloadWithApacheHttp(linkUrl: String, storeFile: String) {
        val httpClient = HttpClients.createDefault()
        try {
            val get = HttpGet(URL(linkUrl).toURI())
            httpClient.execute(get, FileDownloadReponseHandler(File(storeFile)))
        } catch (e: Exception) {
            throw IllegalStateException(e)
        } finally {
            IOUtils.closeQuietly(httpClient)
        }
    }
*/
    private fun downloadWithFuel(linkUrl: String, storePath: String) {
        Fuel.download(linkUrl)
                .destination { response, url -> File(storePath) }
                .response { request, response, result -> }
    }
/*
    class FileDownloadReponseHandler(val target: File) : ResponseHandler<File> {

        override fun handleResponse(response: HttpResponse): File {
            FileUtils.copyInputStreamToFile(response.entity?.content, target)
            return target
        }
    }
*/
}
