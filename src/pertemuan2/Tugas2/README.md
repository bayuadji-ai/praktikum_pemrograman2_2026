# T02_NIM - Aplikasi Pendaftaran Kegiatan

Mata Kuliah : IF21W0508 - Praktikum Pemrograman II
Tugas : Tugas 2 - Event Handling, Validasi, dan Navigasi Swing
Nama : Bayu Adji Prakoso
NIM : 243040040

## Lingkungan Pengembangan

Perangkat yang Digunakan

- Bahasa pemrograman: Java
- JDK: 25
- IDE: Visual Studio Code
- Library GUI: Java Swing
  Tidak ada library eksternal. Hanya Java Swing bawaan JDK.

## Struktur Proyek

```
T02_NIM/
├── src/
│   └── pertemuan2/
│       └── Tugas2/
│           └── Main.java
            └── README.md
```

## Cara Build & Run

1. Buka folder proyek di IDE (VS Code).
2. Pastikan JDK yang dipakai versi 11 ke atas.
3. Jalankan class `pertemuan2.Tugas2.Main` (yang memiliki method `main`).

## Fitur

- Tiga halaman dalam satu jendela memakai `CardLayout`: Formulir, Ringkasan, dan Bantuan. Tidak ada jendela duplikat.
- Tombol dan event:
  - Simpan / Tampilkan: validasi input, lalu tampilkan ringkasan jika valid.
  - Reset: mengosongkan seluruh isian dan mengembalikan fokus ke kolom Nama.
  - Kembali: kembali ke formulir tanpa menghapus isian (input dipertahankan).
  - Ke Halaman Bantuan: membuka halaman petunjuk pengisian.
  - Keluar dan tombol close window (X): keduanya melewati logika konfirmasi yang sama.
- Konfirmasi keluar\** muncul hanya jika ada perubahan yang belum disimpan. Jika dibatalkan (pilih *No\*), aplikasi tetap berjalan dan data tidak hilang.

## Aturan Validasi

Validasi berjalan berurutan dan berhenti pada kesalahan pertama. Input yang salah **tidak diproses**, dialog pesan spesifik ditampilkan, dan fokus dipindahkan ke kontrol yang salah.

| Kontrol  | Aturan                                      | Contoh pesan                          |
| -------- | ------------------------------------------- | ------------------------------------- |
| Nama     | Wajib diisi (tidak boleh kosong/spasi saja) | "Nama wajib diisi!"                   |
| Email    | Format wajar: `teks@domain.tld`             | "Format email tidak valid!"           |
| Umur     | Wajib diisi                                 | "Umur wajib diisi!"                   |
| Umur     | Harus bilangan bulat (bukan huruf/desimal)  | "Umur harus berupa bilangan bulat..." |
| Umur     | Rentang 15 sampai 100                       | "Umur harus di antara 15 sampai 100!" |
| Kegiatan | Wajib memilih selain placeholder            | "Silakan pilih kegiatan pendaftaran!" |

## Keputusan Desain

1. Deteksi perubahan memakai flag `adaPerubahan` yang diset `true` oleh `DocumentListener` (kolom teks) dan `ActionListener` (combo box).
2. Flag di-reset ke `false` setelah Simpan berhasil dan setelah Reset.
3. Konsekuensi: mengetik lalu menghapus kembali tetap dianggap "berubah". Ini disengaja demi kesederhanaan.
4. Tombol close window (X) memakai `DO_NOTHING_ON_CLOSE` agar tidak menutup aplikasi tanpa konfirmasi.
5. Ringkasan memakai font monospaced dan `String.format` agar kolom rapi.

## Skenario Pengujian

Detail hasil uji dan tangkapan layar ada di laporan. Skenario yang diuji:

1. Input valid
2. Nama kosong
3. Email salah (contoh: `budi@mail`)
4. Umur bukan angka (contoh: `abc`, `20.5`)
5. Umur di luar rentang (contoh: `14`, `101`)
6. Kegiatan belum dipilih
7. Reset
8. Kembali tanpa kehilangan data
9. Pembatalan konfirmasi keluar
10. Keluar tanpa perubahan
