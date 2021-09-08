package src.main.kotlin.ru.sber.qa

import io.mockk.every
import io.mockk.mockkClass
import io.mockk.mockkStatic

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.Clock
import java.time.DayOfWeek
import java.time.LocalDateTime

import BaseTest
import ru.sber.qa.*

internal class HrDepartmentTest : BaseTest() {
    private val certificateRequest = mockkClass(CertificateRequest::class)
    private val certificate = mockkClass(Certificate::class)

    @BeforeEach
    fun set() {
        mockkStatic(LocalDateTime::class)
    }

    // Оброботка порядка поступления
    // Обрботка ошибок
    @Test
    fun test1(){
        val currentDay = DayOfWeek.SUNDAY

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay

        assertThrows(WeekendDayException::class.java) {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun test2(){
        val currentDay = DayOfWeek.SATURDAY

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay

        assertThrows(WeekendDayException::class.java) {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun test3(){
        val currentDay = DayOfWeek.TUESDAY
        val certificateType = CertificateType.NDFL

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        assertThrows(NotAllowReceiveRequestException::class.java) {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun test4(){
        val currentDay = DayOfWeek.THURSDAY
        val certificateType = CertificateType.NDFL

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        assertThrows(NotAllowReceiveRequestException::class.java) {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun test5(){
        val currentDay = DayOfWeek.MONDAY
        val certificateType = CertificateType.LABOUR_BOOK

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        assertThrows(NotAllowReceiveRequestException::class.java) {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun test6(){
        val currentDay = DayOfWeek.WEDNESDAY
        val certificateType = CertificateType.LABOUR_BOOK

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        assertThrows(NotAllowReceiveRequestException::class.java) {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun test7(){
        val currentDay = DayOfWeek.FRIDAY
        val certificateType = CertificateType.LABOUR_BOOK

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        assertThrows(NotAllowReceiveRequestException::class.java) {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    // Оброботка порядка поступления
    // Наличие всех нужных дней
    @Test
    fun test8(){
        val currentDay = DayOfWeek.MONDAY
        val certificateType = CertificateType.NDFL

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        assertDoesNotThrow {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun test9(){
        val currentDay = DayOfWeek.WEDNESDAY
        val certificateType = CertificateType.NDFL

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        assertDoesNotThrow {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun test10(){
        val currentDay = DayOfWeek.FRIDAY
        val certificateType = CertificateType.NDFL

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        assertDoesNotThrow {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun test11(){
        val currentDay = DayOfWeek.TUESDAY
        val certificateType = CertificateType.LABOUR_BOOK

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        assertDoesNotThrow {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    @Test
    fun test12(){
        val currentDay = DayOfWeek.THURSDAY
        val certificateType = CertificateType.LABOUR_BOOK

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        assertDoesNotThrow {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }

    // Оброботка порядка поступления
    // Добавление без ошибок
    @Test
    fun test13(){
        val currentDay = DayOfWeek.THURSDAY
        val certificateType = CertificateType.LABOUR_BOOK
        val data = 1L

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType
        every { certificateRequest.process(data) } returns certificate

        HrDepartment.receiveRequest(certificateRequest)

        assertDoesNotThrow {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }
}