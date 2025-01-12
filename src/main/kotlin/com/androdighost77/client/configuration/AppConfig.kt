package com.androdighost77.client.configuration

import com.androdighost77.client.service.impl.MovieStorageClientImpl
import com.androdighost77.client.service.impl.SQSUpdateFeedSenderService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import software.amazon.awssdk.services.sqs.SqsClient

@ApplicationScoped
class AppConfig {

    @ApplicationScoped
    fun getObjectMapper() = jacksonObjectMapper()

    @ApplicationScoped
    fun getSQSUpdateFeedSenderService(
        @ConfigProperty(name = "amazon.sqs.url") sqsUrl: String,
        sqs: SqsClient,
    ) = SQSUpdateFeedSenderService(sqs, sqsUrl)

    @ApplicationScoped
    fun getMovieStorageClientImpl(
        @ConfigProperty(name = "filesystem.download.location") downloadFilesLocation: String,
        @ConfigProperty(name = "movies.server.url") serverUrl: String
    ) = MovieStorageClientImpl(downloadFilesLocation, serverUrl)
}
