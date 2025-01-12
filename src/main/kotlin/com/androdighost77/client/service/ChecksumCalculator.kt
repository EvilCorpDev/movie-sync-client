package com.androdighost77.client.service

import java.io.File

interface ChecksumCalculator {
    fun calculateChecksum(file: File): String
}
