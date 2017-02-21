package com.lapots.gradle.plugins.appenv.core

abstract class AbstractExecutor(var extension: ApplicationEnvironmentExtension) {
    abstract fun execute()
}