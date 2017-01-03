package com.lapots.gradle.plugins.appenv.core

import com.github.kittinunf.fuel.Fuel
import java.io.File

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
