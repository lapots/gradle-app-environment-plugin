package com.lapots.gradle.plugins.appenv.core

import com.lapots.gradle.plugins.appenv.ApplicationEnvironmentExtension

/**
 * Keeps all environments.
 */
open class ApplicationEnvironmentContainer {
    lateinit var environs : MutableMap<String, ApplicationEnvironmentExtension>

    fun app(closure: ApplicationEnvironmentExtension.() -> Unit) : ApplicationEnvironmentExtension {
        val extension = ApplicationEnvironmentExtension()
        extension.closure()

        environs.put(extension.installationId, extension)
        return extension
    }
}
