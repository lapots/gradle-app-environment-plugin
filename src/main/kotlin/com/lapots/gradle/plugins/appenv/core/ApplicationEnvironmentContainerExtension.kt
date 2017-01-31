package com.lapots.gradle.plugins.appenv.core

/**
 * Keeps all environments.
 */
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
    open class ApplicationEnvironmentExtension {
        var installationId = ""
        var srcLink = ""
        var downloadPath = ""
        var installPath = ""

        fun srcLink(closure: () -> String) {
            srcLink = closure()
        }

        fun downloadPath(closure: () -> String) {
            downloadPath = closure()
        }

        fun installPath(closure: () -> String) {
            installPath = closure()
        }

        override fun toString(): String {
            return "ApplicationEnvironmentExtension(" +
                        "installationId='$installationId', " +
                        "srcLink='$srcLink', " +
                        "downloadPath='$downloadPath', " +
                        "installPath='$installPath')"
        }

    }
}
