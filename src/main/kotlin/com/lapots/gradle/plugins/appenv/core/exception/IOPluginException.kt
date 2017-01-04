package com.lapots.gradle.plugins.appenv.core.exception

/**
 * Exception for IO error handling in plugin.
 */
class IOPluginException(val msg: String) : RuntimeException(msg) {
}