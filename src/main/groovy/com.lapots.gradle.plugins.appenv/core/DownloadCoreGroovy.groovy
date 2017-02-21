package com.lapots.gradle.plugins.appenv.core

import groovy.util.logging.Slf4j
import org.apache.commons.io.FilenameUtils

import java.nio.file.Files
import java.nio.file.StandardCopyOption

@Slf4j
class DownloadCoreGroovy implements IExecutorGroovy {
    def extension

    @Override
    def execute() {
        def filename = FilenameUtils.getName(extension.srcLink)
        def installPath = PluginUtilsGroovy.buildAbsolutePath(extension.downloadPath, filename)
        new URL(extension.srcLink).withInputStream {
            if (!Files.exists(installPath)) {
                log.info "$installPath is not found!"
                Files.createDirectories(installPath)
                Files.copy(it, installPath, StandardCopyOption.REPLACE_EXISTING)
            } else {
                log.info "$installPath already exists! Won't be downloaded!"
            }
        }
    }

}
