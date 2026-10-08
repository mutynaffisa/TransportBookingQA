package com.industri.transportqa

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FareCalculatorTest {

    private lateinit var calculator: FareCalculator

    @Before
    fun setUp() {
        calculator = FareCalculator()
    }

    @Test
    fun `isValidNik dengan 16 digit angka harus bernilai true`() {
        val validNik = "3209123456780001"
        assertTrue(calculator.isValidNik(validNik))
    }

    @Test
    fun `isValidNik dengan kurang dari 16 digit harus bernilai false`() {
        val shortNik = "32091234"
        assertFalse(calculator.isValidNik(shortNik))
    }

    @Test
    fun `isValidNik mengandung huruf harus bernilai false`() {
        val invalidNik = "320912345678ABCD"
        assertFalse(calculator.isValidNik(invalidNik))
    }

    @Test
    fun `calculateTotalFare untuk lansia umur 65 tahun mendapat diskon 20 persen`() {
        val total = calculator.calculateTotalFare(500000.0, passengerAge = 65, baggageWeightKg = 15)
        assertEquals(400000.0, total, 0.0)
    }

    @Test
    fun `calculateTotalFare dengan kelebihan bagasi 25 kg dikenakan denda bagasi`() {
        val total = calculator.calculateTotalFare(500000.0, passengerAge = 30, baggageWeightKg = 25)
        assertEquals(625000.0, total, 0.0)
    }

    // ==========================================
    // TUGAS MANDIRI 1: EDGE CASES UNIT TESTING
    // ==========================================

    @Test(expected = IllegalArgumentException::class)
    fun `calculateTotalFare dengan tarif dasar negatif harus melempar IllegalArgumentException`() {
        calculator.calculateTotalFare(-10000.0, passengerAge = 25, baggageWeightKg = 10)
    }

    @Test
    fun `calculateTotalFare untuk balita usia 2 tahun selalu bernilai 0 diskon 100 persen`() {
        val total = calculator.calculateTotalFare(500000.0, passengerAge = 2, baggageWeightKg = 10)
        assertEquals(0.0, total, 0.0)
    }
}