package com.lapots.gradle.plugins.appenv.core

/**
 * Keeps all environments.
 */
@Deprecated("Some issue with closure evaluation.")
open class ApplicationEnvironmentContainerExtension {
    val environs = mutableMapOf<String, ApplicationEnvironmentExtension>()

    fun app(id: String, closure : ApplicationEnvironmentExtension.() -> Unit) {
        val extension = ApplicationEnvironmentExtension()
        extension.installationId = id
        environs.put(id, extension)

        extension.closure()
    }

    /**
     * Sub extension.
     */
    @Deprecated("Some issue with closure evaluation.")
    open class ApplicationEnvironmentExtension {
        var installationId = ""
        var srcLink = ""
        var downloadPath = ""
        var installPath = ""

        override fun toString(): String {
            return "ApplicationEnvironmentExtension(" +
                        "installationId='$installationId', " +
                        "srcLink='$srcLink', " +
                        "downloadPath='$downloadPath', " +
                        "installPath='$installPath')"
        }

    }
}
