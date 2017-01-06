package com.lapots.gradle.plugins.appenv.core

import com.lapots.gradle.plugins.appenv.core.PluginConstants.DEFAULT_SEPARATOR
import mu.KLogging
import org.apache.commons.io.FilenameUtils
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Utility classes.
 */
object PluginUtils : KLogging() {

    // constructs from [file/path, simple.zip] -> file/path/simple.zip
    fun buildAbsolutePath(path: String, file: String): Path {
        return Paths.get(buildPath(path, file))
    }

    fun buildPath(path: String, file: String): String {
        return FilenameUtils.separatorsToSystem(path + DEFAULT_SEPARATOR + file)
    }
}