package com.industri.transportqa

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val fareCalculator = FareCalculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNik = findViewById<EditText>(R.id.etNik)
        val etName = findViewById<EditText>(R.id.etName)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitBooking)
        val tvStatus = findViewById<TextView>(R.id.tvBookingStatus)
        val tvDetail = findViewById<TextView>(R.id.tvPassengerDetail)
        val tvError = findViewById<TextView>(R.id.tvErrorMessage)
        val cardStatus = findViewById<View>(R.id.cardStatus)

        btnSubmit.setOnClickListener {
            val nik = etNik.text.toString().trim()
            val name = etName.text.toString().trim()

            // Validasi Input
            if (nik.isEmpty()) {
                tvError.visibility = View.VISIBLE
                tvError.text = "NIK tidak boleh kosong"
                cardStatus.visibility = View.GONE
                return@setOnClickListener
            }

            if (!fareCalculator.isValidNik(nik)) {
                tvError.visibility = View.VISIBLE
                tvError.text = "NIK harus persis 16 digit angka"
                cardStatus.visibility = View.GONE
                return@setOnClickListener
            }

            // Sukses
            tvError.visibility = View.GONE
            cardStatus.visibility = View.VISIBLE
            tvStatus.text = "Pemesanan Tiket Berhasil Diverifikasi"
            tvDetail.text = "Nama: $name\nNIK: $nik\nStatus: VAKSIN_VERIFIED"
        }
    }
}