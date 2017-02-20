package com.lapots.gradle.plugins.appenv.core

import org.apache.commons.io.FilenameUtils

import java.nio.file.Files
import java.nio.file.StandardCopyOption

class DownloadCoreGroovy implements IExecutorGroovy {
    def extension

    @Override
    def execute() {
        def filename = FilenameUtils.getName(extension.srcLink)
        def installPath = PluginUtils.buildAbsolutePath(extension.downloadPath, filename)
        new URL(extension.srcLink).withInputStream {
            if (!Files.exists(installPath)) {
                Files.createDirectories(installPath)
                Files.copy(it, installPath, StandardCopyOption.REPLACE_EXISTING)
            }
        }
    }

}
