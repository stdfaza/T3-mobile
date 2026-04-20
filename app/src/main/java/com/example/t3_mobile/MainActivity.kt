package com.example.t3_mobile

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi semua View (Widget)
        val etNama = findViewById<EditText>(R.id.etNama)
        val rgKelamin = findViewById<RadioGroup>(R.id.rgKelamin)
        val cbMembaca = findViewById<CheckBox>(R.id.cbMembaca)
        val cbCoding = findViewById<CheckBox>(R.id.cbCoding)
        val cbOlahraga = findViewById<CheckBox>(R.id.cbOlahraga)
        val btnTampilkan = findViewById<Button>(R.id.btnTampilkan)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // 2. Memberikan aksi ketika tombol diklik
        btnTampilkan.setOnClickListener {

            // Ambil teks dari EditText dan hilangkan spasi berlebih
            val nama = etNama.text.toString().trim()

            // Ambil ID dari RadioButton yang sedang dipilih
            val selectedKelaminId = rgKelamin.checkedRadioButtonId

            // --- PROSES VALIDASI ---

            // Cek apakah nama kosong
            if (nama.isEmpty()) {
                etNama.error = "Nama tidak boleh kosong"
                etNama.requestFocus()
                return@setOnClickListener // Hentikan eksekusi kode di bawahnya
            }

            // Cek apakah jenis kelamin belum dipilih (-1 berarti tidak ada yang dipilih)
            if (selectedKelaminId == -1) {
                Toast.makeText(this, "Jenis kelamin harus dipilih!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // --- PENGUMPULAN DATA JIKA VALIDASI LULUS ---

            // Ambil teks dari RadioButton yang dipilih
            val rbTerpilih = findViewById<RadioButton>(selectedKelaminId)
            val kelamin = rbTerpilih.text.toString()

            // Cek CheckBox hobi mana saja yang dicentang
            val daftarHobi = mutableListOf<String>()
            if (cbMembaca.isChecked) daftarHobi.add("Membaca")
            if (cbCoding.isChecked) daftarHobi.add("Coding")
            if (cbOlahraga.isChecked) daftarHobi.add("Olahraga")

            // Gabungkan list hobi menjadi satu string dipisah koma. Jika kosong, beri strip (-)
            val hobi = if (daftarHobi.isNotEmpty()) daftarHobi.joinToString(", ") else "-"

            // Format string output agar rapi menggunakan indentasi
            val hasilOutput = """
                Nama    : $nama
                Kelamin : $kelamin
                Hobi    : $hobi
            """.trimIndent()

            // Tampilkan ke TextView dan ubah warna teks menjadi putih (karena defaultnya abu-abu untuk placeholder)
            tvHasil.text = hasilOutput
            tvHasil.setTextColor(android.graphics.Color.WHITE)
        }
    }
}