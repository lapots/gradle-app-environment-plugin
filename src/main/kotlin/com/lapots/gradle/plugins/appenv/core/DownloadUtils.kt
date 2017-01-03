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
        println("Attempt to download from $link and save into $storePath")
        downloadWithFuel(link, storePath)
    }

    private fun downloadWithFuel(linkUrl: String, storePath: String) {
        Fuel.download(linkUrl)
                .destination { response, url -> File(storePath) }
                .response { request, response, result -> }
    }

}
