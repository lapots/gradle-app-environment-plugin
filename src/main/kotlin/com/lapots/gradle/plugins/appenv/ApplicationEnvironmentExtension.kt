package com.lapots.gradle.plugins.appenv

/**
 * Extension for {@link ApplicationEnvironmentPlugin}.
 */
open class ApplicationEnvironmentExtension {
    var installationId = ""
    var srcLink = ""
    var downloadPath = ""
    var installPath = ""

    fun installationId(closure: ApplicationEnvironmentExtension.() -> String) : ApplicationEnvironmentExtension {
        installationId = closure()
        return this
    }

    fun srcLink(closure: ApplicationEnvironmentExtension.() -> String) : ApplicationEnvironmentExtension {
        srcLink = closure()
        return this
    }

    fun downloadPath(closure: ApplicationEnvironmentExtension.() -> String) : ApplicationEnvironmentExtension {
        downloadPath = closure()
        return this
    }

    fun installPath(closure: ApplicationEnvironmentExtension.() -> String) : ApplicationEnvironmentExtension {
        installPath = closure()
        return this
    }
}
