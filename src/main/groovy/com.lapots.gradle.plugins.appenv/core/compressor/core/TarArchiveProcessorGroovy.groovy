package com.lapots.gradle.plugins.appenv.core.compressor.core

import com.lapots.gradle.plugins.appenv.core.PluginUtilsGroovy
import com.lapots.gradle.plugins.appenv.core.compressor.api.IArchiveProcessorGroovy
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.io.IOUtils

class TarArchiveProcessorGroovy implements IArchiveProcessorGroovy {
    @Override
    def process(Object inputStream, Object destination) {
        def archiveStream = new TarArchiveInputStream(inputStream)

        def archiveEntry = archiveStream.nextTarEntry
        while (archiveEntry != null) {
            def file = new File(PluginUtilsGroovy.buildPath(destination, archiveEntry.name))
            if (archiveEntry.isDirectory()) {
                file.mkdirs()
            } else {
                IOUtils.copy(archiveStream, new FileOutputStream(file))
            }
            archiveEntry = archiveStream.nextTarEntry
        }

        archiveStream.close()
        0
    }
}
