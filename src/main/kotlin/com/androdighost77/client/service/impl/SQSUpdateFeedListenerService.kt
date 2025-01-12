package com.androdighost77.client.service.impl

import com.androdighost77.client.service.ProcessUpdateEventService
import com.androdighost77.client.service.UpdateFeedListenerService
import io.quarkus.scheduler.Scheduled
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.config.inject.ConfigProperty
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.Message

@ApplicationScoped
class SQSUpdateFeedListenerService @Inject constructor(
    private val sqsClient: SqsClient,
    @ConfigProperty(name = "amazon.sqs.url") private val queueUrl: String,
    private val processor: ProcessUpdateEventService,
) : UpdateFeedListenerService {

    @Scheduled(every = "{sqs.consumer.interval}")
    override fun listenUpdateFeed() {
        var shouldRetrieveMessages: Boolean
        do {
            val messages = sqsClient.receiveMessage { msg ->
                msg.maxNumberOfMessages(10).queueUrl(queueUrl)
            }.messages()
            shouldRetrieveMessages = messages.size > 0

            messages.forEach { message: Message -> processor.process(message) }
        } while (shouldRetrieveMessages)
    }
}
