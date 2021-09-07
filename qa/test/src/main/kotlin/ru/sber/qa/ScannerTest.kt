package src.main.kotlin.ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import kotlin.random.Random

import BaseTest
import ru.sber.qa.ScanTimeoutException
import ru.sber.qa.Scanner

internal class ScannerTest : BaseTest() {
    @BeforeEach
    fun set() {
        mockkObject(Random)
    }

    @Test
    // Тестирование ошибок
    fun test1() {
        every { Random.nextLong(5_000L, 15_000L) } returns 10_001L

        assertThrows(ScanTimeoutException::class.java)
        {
            Scanner.getScanData()
        }
    }

    @Test
    fun test2() {
        every{ Random.nextLong(5_000L, 15_000L) } returns 5_001L
        val array = Random.nextBytes(100)
        every{ Random.nextBytes(100) } returns array

        assertEquals(array, Scanner.getScanData())
    }
}