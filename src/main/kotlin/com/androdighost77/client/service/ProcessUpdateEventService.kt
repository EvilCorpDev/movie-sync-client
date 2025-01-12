package com.androdighost77.client.service

import com.androdighost77.client.model.UpdateEvent
import software.amazon.awssdk.services.sqs.model.Message

interface ProcessUpdateEventService {

    fun process(message: Message)
}
