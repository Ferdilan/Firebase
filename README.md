# 📇 Firebase Biodata App

Aplikasi Android sederhana berbasis Java yang memungkinkan pengguna menambahkan, melihat, dan mengelola data biodata (nama, umur, jenis kelamin) menggunakan **Firebase Realtime Database**.

---

## ✨ Fitur Utama

- 📥 Tambah data biodata (nama, umur, jenis kelamin)
- 📃 Tampilkan seluruh biodata dalam `ListView`
- 👆 Klik item untuk melihat detail data di halaman kedua
- ☁️ Terintegrasi langsung dengan **Firebase Realtime Database**
- 🔄 Data tersimpan dan disinkronkan secara real-time

---

## 🛠️ Teknologi yang Digunakan

- 🧠 Bahasa: Java
- ⚙️ Android SDK: API 27+
- ☁️ Firebase Realtime Database
- 🎨 Komponen UI: `EditText`, `Spinner`, `ListView`, `Toast`

---

## 🧰 Struktur Folder
├── MainActivity.java # Halaman utama untuk input & list data
├── MainActivity2.java # Halaman detail biodata
├── Biodata.java # Model class biodata
├── listview_biodata.java # Custom Adapter ListView
├── activity_main.xml # UI untuk input dan list
├── activity_main2.xml # UI untuk detail tampilan
└── Firebase/ # Terhubung dengan Firebase Realtime DB


---

## 🚀 Cara Menjalankan

1. Clone repositori ini:
   ```bash
   git clone https://github.com/username/firebase-biodata-app.git
2. Buka di Android Studio.
3. Tambahkan file google-services.json ke folder app/
(Didapat dari Firebase Console setelah membuat project.)
4. Sinkronkan Gradle dan jalankan di emulator atau perangkat nyata.

🔐 Struktur Data Firebase
Biodata: {
  "-NdE18EXQb213...": {
    "id": "-NdE18EXQb213...",
    "nama": "Ferdilan",
    "umur": "20",
    "jenisKelamin": "Laki-laki"
  }
}

📌 Catatan Tambahan
Pastikan perangkat terhubung ke internet.

Pastikan Firebase telah diaktifkan di project dan .json sudah tepat.

Firebase Realtime Database harus menggunakan mode test saat awal development.

👨‍💻 Kontributor
🧠 Ferdilan Ramadhani — Mahasiswa Teknik Telekomunikasi yang cinta riset dan pengembangan

🤝 Dibantu oleh ChatGPT sebagai navigator cerdas ✨

🗝️ Filosofi Proyek
"Data bukan sekadar angka, tetapi kisah.
Dengan Firebase, kisah-kisah itu bisa disimpan, dibagikan, dan dikenang."

📬 Lisensi
Proyek ini bersifat open source dan bebas digunakan untuk pembelajaran.
