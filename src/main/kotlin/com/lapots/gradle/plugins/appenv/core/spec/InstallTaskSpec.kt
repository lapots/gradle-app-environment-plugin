package com.lapots.gradle.plugins.appenv.core.spec

/**
 * Spec for Install task.
 */
interface InstallTaskSpec {

    fun file(name: String)

    fun from(downloadPath: String)

    fun to(installationPath: String)
}