package com.androdighost77.client.service.impl

import com.androdighost77.client.service.ChecksumCalculator
import jakarta.enterprise.context.ApplicationScoped
import org.apache.commons.codec.digest.DigestUtils
import java.io.File
import java.nio.file.Files

@ApplicationScoped
class Md5ChecksumCalculator : ChecksumCalculator {

    override fun calculateChecksum(file: File): String =
            Files.newInputStream(file.toPath()).use { DigestUtils.md5Hex(it) }
}
