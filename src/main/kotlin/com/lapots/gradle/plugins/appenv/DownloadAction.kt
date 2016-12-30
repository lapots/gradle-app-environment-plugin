package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.DownloadUtils
import groovy.lang.Closure
import org.gradle.api.Project

/**
 * Downloads files.
 */
class DownloadAction(val project: Project?) {

    var srcLink = ""
    var downloadPath = ""

    fun execute() {
        DownloadUtils.download(srcLink, downloadPath)
    }

    // investigate DownloadAction.() -> Unit
    fun env(envConfig: Closure<*>) {
        envConfig.delegate = this
        envConfig.call()
    }

    fun srcLink(link: String) {
        this.srcLink = link
    }

    fun downloadPath(path: String) {
        this.downloadPath = path
    }
}