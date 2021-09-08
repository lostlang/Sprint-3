package src.main.kotlin.ru.sber.qa

import BaseTest
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.mockkObject
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.sber.qa.*
import java.time.Clock
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.*

internal class HrDepartmentTest : BaseTest() {
    private val certificateRequest = mockkClass(CertificateRequest::class)

    @BeforeEach
    fun set() {
        mockkStatic(Certificate::class)
        mockkStatic(LocalDateTime::class)
    }

    @Test
    // Оброботка порядка поступления
    // Обрботка ошибок
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
}