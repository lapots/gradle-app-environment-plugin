package com.lapots.gradle.plugins.appenv.core.spec

/**
 * Specification for download task.
 */
interface DownloadTaskSpec {

    fun from(srcLink: () -> String)

    fun to(downloadPath: () -> String)
}
