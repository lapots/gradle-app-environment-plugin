package com.lapots.gradle.plugins.appenv

/**
 * Extension for {@link ApplicationEnvironmentPlugin}.
 */
open class ApplicationEnvironmentExtension {
    lateinit var srcLink: String
    lateinit var downloadPath: String
    lateinit var installPath: String
}
