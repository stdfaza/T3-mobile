package com.example.t3_mobile

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi View
        val tilNama = findViewById<TextInputLayout>(R.id.tilNama)
        val etNama = findViewById<TextInputEditText>(R.id.etNama)
        val rgKelamin = findViewById<RadioGroup>(R.id.rgKelamin)
        val cbMembaca = findViewById<CheckBox>(R.id.cbMembaca)
        val cbCoding = findViewById<CheckBox>(R.id.cbCoding)
        val cbOlahraga = findViewById<CheckBox>(R.id.cbOlahraga)
        val btnTampilkan = findViewById<Button>(R.id.btnTampilkan)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // 2. Aksi tombol diklik
        btnTampilkan.setOnClickListener {

            // Reset pesan error setiap kali tombol diklik
            tilNama.error = null

            val nama = etNama.text.toString().trim()
            val selectedKelaminId = rgKelamin.checkedRadioButtonId

            // --- PROSES VALIDASI ---

            // Validasi 1: Cek apakah nama kosong
            if (nama.isEmpty()) {
                tilNama.error = "Nama tidak boleh kosong" // Mengubah kotak jadi merah & memunculkan teks error
                etNama.requestFocus()
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validasi 2: Cek apakah jenis kelamin dipilih
            if (selectedKelaminId == -1) {
                Toast.makeText(this, "Jenis kelamin harus dipilih!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // --- PENGUMPULAN DATA JIKA VALIDASI LULUS ---

            val rbTerpilih = findViewById<RadioButton>(selectedKelaminId)
            val kelamin = rbTerpilih.text.toString()

            val daftarHobi = mutableListOf<String>()
            if (cbMembaca.isChecked) daftarHobi.add("Membaca")
            if (cbCoding.isChecked) daftarHobi.add("Coding")
            if (cbOlahraga.isChecked) daftarHobi.add("Olahraga")

            val hobi = if (daftarHobi.isNotEmpty()) daftarHobi.joinToString(", ") else "-"

            // Format string output
            val hasilOutput = """
                Nama    : $nama
                Kelamin : $kelamin
                Hobi    : $hobi
            """.trimIndent()

            // Tampilkan ke TextView
            tvHasil.text = hasilOutput
            tvHasil.setTextColor(android.graphics.Color.WHITE)
        }
    }
}