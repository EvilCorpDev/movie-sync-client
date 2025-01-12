package com.androdighost77.client.service

import com.androdighost77.client.model.UpdateEvent

interface MovieStorageClient {

    fun downloadFile(updateEvent: UpdateEvent, callbackFun: CallbackFun)
}
