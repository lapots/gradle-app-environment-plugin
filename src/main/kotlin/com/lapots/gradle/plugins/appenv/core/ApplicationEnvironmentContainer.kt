package com.lapots.gradle.plugins.appenv.core

import com.lapots.gradle.plugins.appenv.ApplicationEnvironmentExtension

/**
 * Keeps all environments.
 */
class ApplicationEnvironmentContainer {
    lateinit var environs : Map<String, ApplicationEnvironmentExtension>
}