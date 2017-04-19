package com.lapots.gradle.plugins.appenv.core

/**
 * Configurable plugin extension.
 */
class ApplicationEnvironmentExtension {
    var installationId = ""
    var srcLink = ""
    var downloadPath = ""
    var installPath = ""

    fun process(closure: ApplicationEnvironmentExtension.() -> Unit) {
        closure()
    }

    fun setEverything(installationId: String, srcLink: String, downloadPath: String, installPath: String) {
        this.installationId = installationId
        this.srcLink = srcLink
        this.downloadPath = downloadPath
        this.installPath = installPath
    }

    override fun toString(): String {
        return "ApplicationEnvironmentExtension(" +
                "installationId='$installationId', " +
                "srcLink='$srcLink', " +
                "downloadPath='$downloadPath', " +
                "installPath='$installPath')"
    }
}