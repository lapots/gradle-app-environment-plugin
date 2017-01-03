package com.lapots.gradle.plugins.appenv

import groovy.lang.Closure
import org.gradle.api.Project
import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * Extension for {@link ApplicationEnvironmentPlugin}.
 */
open class ApplicationEnvironmentExtension {
    lateinit var srcLink: String
    lateinit var downloadPath: String
}
