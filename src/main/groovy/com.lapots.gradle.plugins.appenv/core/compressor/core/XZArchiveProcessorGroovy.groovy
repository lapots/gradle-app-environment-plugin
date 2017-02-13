package com.lapots.gradle.plugins.appenv.core.compressor.core

import com.lapots.gradle.plugins.appenv.core.compressor.api.IArchiveProcessorGroovy
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream
import org.apache.commons.io.IOUtils

class XZArchiveProcessorGroovy implements IArchiveProcessorGroovy {
    // can be rewritten with metaprogramming
    def file(absolutePath) {
        def file = new File(absolutePath)
        if (!file.exists()) {
            file.createNewFile()
        }

        file
    }

    @Override
    def process(Object inputStream, Object destination) {
        def archiveStream = new XZCompressorInputStream(inputStream)
        def file = file(destination)
        IOUtils.copy(archiveStream, new FileOutputStream(file))

        archiveStream.close()
        0
    }
}
