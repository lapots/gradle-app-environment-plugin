package com.lapots.gradle.plugins.appenv.core

/**
 * Keeps all environments.
 */
open class ApplicationEnvironmentContainerExtension {
    val environs = mutableMapOf<String, ApplicationEnvironmentExtension>()

    var installationId = ""
    var srcLink = ""
    var installPath = ""
    var downloadPath = ""

    fun app(id: String, closure : ApplicationEnvironmentExtension.() -> Unit) {
        val extension = ApplicationEnvironmentExtension()
        extension.installationId = id
        environs.put(id, extension)

        extension.process(closure)

        extension.setEverything(installationId, srcLink, downloadPath, installPath)
    }
}
