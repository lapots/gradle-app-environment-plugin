package com.lapots.gradle.plugins.appenv.core

import org.apache.commons.io.FilenameUtils

import java.nio.file.Paths

class PluginUtilsGroovy {

    static def buildAbsolutePath(path, file) {
        Paths.get(buildPath(path, file))
    }

    def static buildPath(path, file) {
        FilenameUtils.separatorsToSystem(path + PluginConstantsGroovy.DEFAULT_SEPARATOR + file)
    }
}
