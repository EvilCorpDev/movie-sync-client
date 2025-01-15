package com.androdighost77.client.service.impl

import com.androdighost77.client.model.UpdateEvent
import com.androdighost77.client.service.CallbackFun
import com.androdighost77.client.service.MovieStorageClient
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels
import java.nio.file.Files.exists
import kotlin.io.path.Path

class MovieStorageClientImpl (
    private val downloadFilesLocation: String,
    private val serverUrl: String
) : MovieStorageClient {
    override fun downloadFile(updateEvent: UpdateEvent, callbackFun: CallbackFun) {
        val fileName = "${downloadFilesLocation}/${updateEvent.type.folder}/${updateEvent.fileName}"
        val exists = exists(Path(fileName))
        if (!exists) {
            URL("${serverUrl}/files/${updateEvent.id}").openStream()
                .use {
                    Channels.newChannel(it).use { rbc ->
                        FileOutputStream(fileName).use { fos ->
                            fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
                            callbackFun.apply(fileName)
                        }
                    }
                }
        }
    }
}
