package com.androdighost77.client.service

import com.androdighost77.client.model.UpdateEvent
import org.jboss.logging.Logger
import software.amazon.awssdk.services.sqs.model.Message
import java.io.File

class CallbackFun (
    private val updateEvent: UpdateEvent,
    private val message: Message,
    private val senderService: UpdateFeedSenderService,
    private val checksumCalculator: ChecksumCalculator
) {
    val log: Logger = Logger.getLogger("CallbackFun")
    fun apply(fileName: String) {
        val calculateChecksum = checksumCalculator.calculateChecksum(File(fileName))
        if (calculateChecksum == updateEvent.checksum) {
            log.infov("File {0} synced successfully", updateEvent.id)
            senderService.removeProcessedMessage(message)
        } else {
            log.errorv("Error syncing file {0}", updateEvent.id)
        }
    }
}
