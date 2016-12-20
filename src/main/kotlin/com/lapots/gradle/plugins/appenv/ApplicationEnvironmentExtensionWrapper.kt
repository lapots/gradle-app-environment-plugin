package com.lapots.gradle.plugins.appenv

/**
 * Wrapper for application environment extensions, allowing to hold multiple
 * environments
 */
class ApplicationEnvironmentExtensionWrapper {
    val environments:
            MutableList<ApplicationEnvironmentExtension> = mutableListOf()

    fun environment(environ: ApplicationEnvironmentExtension) {
        environments.add(environ)
    }
}