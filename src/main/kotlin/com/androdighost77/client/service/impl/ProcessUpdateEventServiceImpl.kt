package com.androdighost77.client.service.impl

import com.androdighost77.client.model.UpdateEvent
import com.androdighost77.client.service.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.config.inject.ConfigProperty
import software.amazon.awssdk.services.sqs.model.Message

@ApplicationScoped
class ProcessUpdateEventServiceImpl @Inject constructor(
    private val objectMapper: ObjectMapper,
    private val senderService: UpdateFeedSenderService,
    private val movieStorageClient: MovieStorageClient,
    private val checksumCalculator: ChecksumCalculator,
    @ConfigProperty(name = "movies.server.username") private val username: String,
) : ProcessUpdateEventService {

    override fun process(message: Message) {
        val updateEvent: UpdateEvent = objectMapper.readValue(message.body())
        if (username == updateEvent.username) {
            val callback = CallbackFun(updateEvent, message, senderService, checksumCalculator)
            movieStorageClient.downloadFile(updateEvent, callback)
        }
    }
}
