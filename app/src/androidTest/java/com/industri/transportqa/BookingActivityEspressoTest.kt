package com.industri.transportqa

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookingActivityEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun isiFormPemesanan_danTekanPesan_harusMenampilkanKonfirmasiTiket() {
        // 1. Ketik NIK
        onView(withId(R.id.etNik))
            .perform(typeText("3209123456780001"), closeSoftKeyboard())

        // 2. Ketik Nama
        onView(withId(R.id.etName))
            .perform(typeText("Ahmad Dahlan"), closeSoftKeyboard())

        // 3. Tekan Tombol Pesan Tiket
        onView(withId(R.id.btnSubmitBooking))
            .perform(click())

        // 4. Verifikasi status konfirmasi sukses muncul
        onView(withId(R.id.tvBookingStatus))
            .check(matches(isDisplayed()))
            .check(matches(withText("Pemesanan Tiket Berhasil Diverifikasi")))
    }

    // ==========================================
    // TUGAS MANDIRI 2: NEGATIVE FLOW UI AUTOMATION
    // ==========================================

    @Test
    fun kosongkanNik_danTekanPesan_harusMenampilkanPesanError() {
        // 1. Kosongkan NIK, langsung isi Nama saja
        onView(withId(R.id.etName))
            .perform(typeText("Ahmad Dahlan"), closeSoftKeyboard())

        // 2. Tekan Tombol Pesan
        onView(withId(R.id.btnSubmitBooking))
            .perform(click())

        // 3. Verifikasi pesan error 'NIK tidak boleh kosong' muncul di layar
        onView(withId(R.id.tvErrorMessage))
            .check(matches(isDisplayed()))
            .check(matches(withText("NIK tidak boleh kosong")))
    }
}