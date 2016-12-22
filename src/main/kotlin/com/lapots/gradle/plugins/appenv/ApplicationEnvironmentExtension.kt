package com.lapots.gradle.plugins.appenv

/**
 * Extension for {@link ApplicationEnvironmentPlugin}.
 */
data class ApplicationEnvironmentExtension(
        val srcLink: String,
        val downloadPath: String,
        val installationPath: String,
        val startCommand: String = "",
        var stopCommand: String = "") {
}
