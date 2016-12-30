package com.lapots.gradle.plugins.appenv

import groovy.lang.Closure
import org.gradle.api.Project
import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * Extension for {@link ApplicationEnvironmentPlugin}.
 */
open class ApplicationEnvironmentExtension {
    val project: Any?

    constructor(project: Any?) {
        this.project = project
    }

    var srcLink: String = ""
    var downloadPath: String = ""
}
