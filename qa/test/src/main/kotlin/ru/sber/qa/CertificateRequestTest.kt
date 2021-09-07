package src.main.kotlin.ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import kotlin.random.Random

import BaseTest
import ru.sber.qa.CertificateRequest
import ru.sber.qa.CertificateType
import ru.sber.qa.Scanner

internal class CertificateRequestTest: BaseTest() {
    private val employeeNumber = 1L
    private val certificateMock = mockk<CertificateType>()
    private var certificateRequest = CertificateRequest(employeeNumber, certificateMock)
    private val array = Random.nextBytes(100)

    @BeforeEach
    fun set() {
        mockkObject(Scanner)
    }

    @Test
    fun test1() {
        every{ Scanner.getScanData() } returns array

        val hrEmployeeNumber = 5_000L
        val certificate = certificateRequest.process(hrEmployeeNumber)

        assertEquals(array, certificate.data)
        assertEquals(hrEmployeeNumber, certificate.processedBy)
        assertEquals(certificateRequest, certificate.certificateRequest)
    }

    @Test
    fun test2() {
        every{ Scanner.getScanData() } returns array

        val hrEmployeeNumber = 30L
        val certificate = certificateRequest.process(hrEmployeeNumber)

        assertEquals(array, certificate.data)
        assertEquals(hrEmployeeNumber, certificate.processedBy)
        assertEquals(certificateRequest, certificate.certificateRequest)
    }
}