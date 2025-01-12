package com.androdighost77.client.service

import software.amazon.awssdk.services.sqs.model.Message

interface UpdateFeedSenderService {

    fun removeProcessedMessage(message: Message)
}
