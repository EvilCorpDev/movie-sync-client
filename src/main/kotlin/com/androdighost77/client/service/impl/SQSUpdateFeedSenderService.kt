package com.androdighost77.client.service.impl

import com.androdighost77.client.service.UpdateFeedSenderService
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.Message

class SQSUpdateFeedSenderService constructor(
    private val sqsClient: SqsClient,
    private val queueUrl: String
) : UpdateFeedSenderService {
    override fun removeProcessedMessage(message: Message) {
        sqsClient.deleteMessage { it.queueUrl(queueUrl).receiptHandle(message.receiptHandle()) }
    }
}
