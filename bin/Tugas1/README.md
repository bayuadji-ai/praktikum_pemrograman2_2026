Tugas 1 - Dasar GUI Swing dan Layout

Deskripsi
Program ini merupakan aplikasi formulir pendaftaran kegiatan yang dibuat menggunakan Java Swing. Aplikasi ini dibuat untuk memenuhi tugas Praktikum Pemrograman II tentang penggunaan komponen GUI dan layout pada Java.

Komponen yang Digunakan
- Beberapa komponen yang digunakan dalam aplikasi ini yaitu:
- JFrame sebagai jendela utama aplikasi.
- JPanel untuk mengelompokkan bagian formulir.
- JLabel dan JTextField untuk menampilkan label dan mengisi data peserta.
- JComboBox untuk memilih kegiatan.
- JRadioButton dan ButtonGroup untuk memilih jenis peserta.
- JCheckBox untuk memilih tambahan.
- JTextArea untuk mengisi catatan.
- JButton untuk tombol Daftar dan Reset.
- Aplikasi menggunakan BorderLayout, GridBagLayout, dan FlowLayout agar komponen tersusun dengan rapi dan dapat menyesuaikan ukuran jendela.

Fitur
- Input nama lengkap, NIM, email, dan nomor telepon.
- Memilih kegiatan melalui combo box.
- Memilih jenis peserta (Mahasiswa atau Umum) dengan radio button yang saling eksklusif.
- Memilih opsi tambahan melalui checkbox.
- Mengisi catatan.
- Tombol Reset untuk mengosongkan formulir.
- Tombol Daftar untuk memeriksa data wajib dan menampilkan ringkasan. Data belum disimpan ke database atau file.
- Layout menggunakan BorderLayout, GridBagLayout, dan FlowLayout, tanpa absolute positioning.
- GUI dijalankan melalui Event Dispatch Thread menggunakan SwingUtilities.invokeLater.

Perangkat yang Digunakan
- Bahasa pemrograman: Java
- JDK: 25
- IDE: Visual Studio Code
- Library GUI: Java Swing

Struktur proyek
C:\Users\BAYU\Documents\Document\pp2_2026\
└── src\
    └── Tugas1\
        ├── FormPendaftaranKegiatan.java
        └── README.md

Cara menjalankan lewat IDE
1. Buat/open project Java di IDE.
2. Masukkan FormPendaftaranKegiatan.java ke folder source.
3. Pastikan nama file sama dengan nama class: FormPendaftaranKegiatan.java.
4. Jalankan method main.

Rencana pengujian
1. Jalankan aplikasi dan ambil screenshot tampilan awal.
2. Perbesar jendela; periksa bahwa label dan input tetap terbaca.
3. Perkecil jendela; periksa apakah komponen tetap terlihat dan dapat digunakan.
4. Pilih Mahasiswa lalu Umum; pastikan hanya satu radio button yang aktif.
5. Tekan Tab untuk memeriksa urutan fokus input.
6. Klik Daftar dengan nama atau NIM kosong; pastikan peringatan muncul.
7. Isi nama dan NIM, lalu klik Daftar; pastikan ringkasan tampil.
8. Klik Reset; pastikan formulir kembali ke nilai awal.