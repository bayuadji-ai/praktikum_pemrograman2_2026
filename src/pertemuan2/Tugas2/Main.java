package pertemuan2.Tugas2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Pattern;

public class Main {
    // Flag untuk melacak perubahan data yang belum disimpan
    private static boolean adaPerubahan = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Aplikasi Pendaftaran Kegiatan");
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // --- 1. KOMPONEN FORMULIR ---
        JTextField txtNama = new JTextField(20);
        JTextField txtEmail = new JTextField(20);
        JTextField txtUmur = new JTextField(5);
        
        String[] daftarKegiatan = {"-- Pilih Kegiatan --", "Seminar Teknologi", "Workshop Pemrograman", "Lomba UI/UX"};
        JComboBox<String> cbKegiatan = new JComboBox<>(daftarKegiatan);

        // --- TRACKING PERUBAHAN INPUT (Untuk Konfirmasi Keluar) ---
        DocumentListener docListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { adaPerubahan = true; }
            public void removeUpdate(DocumentEvent e) { adaPerubahan = true; }
            public void changedUpdate(DocumentEvent e) { adaPerubahan = true; }
        };
        txtNama.getDocument().addDocumentListener(docListener);
        txtEmail.getDocument().addDocumentListener(docListener);
        txtUmur.getDocument().addDocumentListener(docListener);
        cbKegiatan.addActionListener(e -> adaPerubahan = true);

        // --- CARD 1: HALAMAN FORMULIR ---
        JPanel panelForm = new JPanel(new BorderLayout(10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel inputGrid = new JPanel(new GridLayout(4, 2, 10, 10));
        inputGrid.add(new JLabel("Nama Lengkap:"));
        inputGrid.add(txtNama);
        inputGrid.add(new JLabel("Email:"));
        inputGrid.add(txtEmail);
        inputGrid.add(new JLabel("Umur (15-100):"));
        inputGrid.add(txtUmur);
        inputGrid.add(new JLabel("Pilihan Kegiatan:"));
        inputGrid.add(cbKegiatan);
        
        JPanel actionButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSimpan = new JButton("Simpan / Tampilkan");
        JButton btnReset = new JButton("Reset");
        JButton btnBantuan = new JButton("Ke Halaman Bantuan");
        JButton btnKeluar = new JButton("Keluar");
        actionButtons.add(btnSimpan);
        actionButtons.add(btnReset);
        actionButtons.add(btnBantuan);
        actionButtons.add(btnKeluar);
        
        panelForm.add(inputGrid, BorderLayout.CENTER);
        panelForm.add(actionButtons, BorderLayout.SOUTH);

        // --- CARD 2: HALAMAN RINGKASAN ---
        JPanel panelRingkasan = new JPanel(new BorderLayout(10, 10));
        panelRingkasan.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel lblJudulRingkasan = new JLabel("--- Ringkasan Pendaftaran ---", SwingConstants.CENTER);
        lblJudulRingkasan.setFont(new Font("Arial", Font.BOLD, 14));
        
        JTextArea txtAreaRingkasan = new JTextArea();
        txtAreaRingkasan.setEditable(false);
        txtAreaRingkasan.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        txtAreaRingkasan.setMargin(new Insets(10, 10, 10, 10)); // biar gak nempel ke tepi
        txtAreaRingkasan.setBackground(new Color(245, 245, 245));
        
        JButton btnKembaliDariRingkasan = new JButton("Kembali ke Formulir");
        JPanel panelBtnRingkasan = new JPanel(new FlowLayout());
        panelBtnRingkasan.add(btnKembaliDariRingkasan);
        
        panelRingkasan.add(lblJudulRingkasan, BorderLayout.NORTH);
        panelRingkasan.add(new JScrollPane(txtAreaRingkasan), BorderLayout.CENTER);
        panelRingkasan.add(panelBtnRingkasan, BorderLayout.SOUTH);

        // --- CARD 3: HALAMAN BANTUAN ---
        JPanel panelBantuan = new JPanel(new BorderLayout(10, 10));
        panelBantuan.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel lblJudulBantuan = new JLabel("Halaman Petunjuk & Bantuan", SwingConstants.CENTER);
        lblJudulBantuan.setFont(new Font("Arial", Font.BOLD, 14));
        
        JTextArea txtAreaBantuan = new JTextArea(
            "Petunjuk Pengisian Formulir:\n" +
            "1. Nama Lengkap wajib diisi (tidak boleh kosong).\n" +
            "2. Email harus menggunakan format yang valid (contoh: user@email.com).\n" +
            "3. Umur pendaftar harus berupa angka dalam rentang 15 sampai 100 tahun.\n" +
            "4. Anda wajib memilih salah satu kegiatan yang tersedia pada menu drop-down.\n" +
            "5. Jika menekan tombol keluar saat data formulir diubah, sistem akan meminta konfirmasi."
        );
        txtAreaBantuan.setEditable(false);
        txtAreaBantuan.setWrapStyleWord(true);
        txtAreaBantuan.setLineWrap(true);
        
        JButton btnKembaliDariBantuan = new JButton("Kembali ke Formulir");
        JPanel panelBtnBantuan = new JPanel(new FlowLayout());
        panelBtnBantuan.add(btnKembaliDariBantuan);
        
        panelBantuan.add(lblJudulBantuan, BorderLayout.NORTH);
        panelBantuan.add(new JScrollPane(txtAreaBantuan), BorderLayout.CENTER);
        panelBantuan.add(panelBtnBantuan, BorderLayout.SOUTH);

        // --- MENAMBAHKAN CARD KE MAIN PANEL ---
        mainPanel.add(panelForm, "Formulir");
        mainPanel.add(panelRingkasan, "Ringkasan");
        mainPanel.add(panelBantuan, "Bantuan");

        // ==========================================
        //         EVENT HANDLING & LOGIC
        // ==========================================

        // 1. Event Tombol Simpan / Tampilkan (Validasi Ketat & Spesifik)
        btnSimpan.addActionListener(e -> {
            String nama = txtNama.getText().trim();
            String email = txtEmail.getText().trim();
            String umurStr = txtUmur.getText().trim();
            int indexKegiatan = cbKegiatan.getSelectedIndex();

            // Validasi 1: Nama Wajib Diisi
            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Input Salah: Nama wajib diisi!", "Validasi Gagal", JOptionPane.ERROR_MESSAGE);
                txtNama.requestFocus();
                return;
            }

            // Validasi 2: Format Email Wajar
            String emailRegex = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$";
            if (!Pattern.matches(emailRegex, email)) {
                JOptionPane.showMessageDialog(frame, "Input Salah: Format email tidak valid!", "Validasi Gagal", JOptionPane.ERROR_MESSAGE);
                txtEmail.requestFocus();
                return;
            }

            // Validasi 3: Umur
            if (umurStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Input Salah: Umur wajib diisi!", "Validasi Gagal", JOptionPane.ERROR_MESSAGE);
                txtUmur.requestFocus();
                return;
            }
            if (!umurStr.matches("-?\\d+")) {
                JOptionPane.showMessageDialog(frame, "Input Salah: Umur harus berupa bilangan bulat (tanpa huruf, spasi, atau desimal)!", "Validasi Gagal", JOptionPane.ERROR_MESSAGE);
                txtUmur.requestFocus();
                return;
            }
            int umur;
            try {
                umur = Integer.parseInt(umurStr);
            } catch (NumberFormatException ex) { // angka kegedean, overflow int
                umur = -1;
            }
            if (umur < 15 || umur > 100) {
                JOptionPane.showMessageDialog(frame, "Input Salah: Umur harus di antara 15 sampai 100!", "Validasi Gagal", JOptionPane.ERROR_MESSAGE);
                txtUmur.requestFocus();
                return;
            }

            // Validasi 4: Pilihan Kegiatan Wajib
            if (indexKegiatan == 0) {
                JOptionPane.showMessageDialog(frame, "Input Salah: Silakan pilih kegiatan pendaftaran!", "Validasi Gagal", JOptionPane.ERROR_MESSAGE);
                cbKegiatan.requestFocus();
                return;
            }

            // Jika semua validasi lolos, tampilkan ke halaman Ringkasan
            String fmt = "%-16s: %s\n";
            txtAreaRingkasan.setText(
                "Pendaftaran Berhasil Terproses!\n\n" +
                String.format(fmt, "Nama Lengkap", nama) +
                String.format(fmt, "Email", email) +
                String.format(fmt, "Umur", umur + " Tahun") +
                String.format(fmt, "Kegiatan Diikuti", cbKegiatan.getSelectedItem())
            );
            adaPerubahan = false; // Reset flag karena data sudah disimpan/diproses resmi
            cardLayout.show(mainPanel, "Ringkasan");
        });

        // 2. Event Tombol Reset
        btnReset.addActionListener(e -> {
            txtNama.setText("");
            txtEmail.setText("");
            txtUmur.setText("");
            cbKegiatan.setSelectedIndex(0);
            adaPerubahan = false;
            txtNama.requestFocus();
        });

        // 3. Navigasi Antar Halaman (CardLayout)
        btnBantuan.addActionListener(e -> cardLayout.show(mainPanel, "Bantuan"));
        btnKembaliDariRingkasan.addActionListener(e -> cardLayout.show(mainPanel, "Formulir"));
        btnKembaliDariBantuan.addActionListener(e -> cardLayout.show(mainPanel, "Formulir"));

        // 4. Logika Konfirmasi Keluar Terpusat
        Runnable prosesKeluar = () -> {
            if (adaPerubahan) {
                int opsi = JOptionPane.showConfirmDialog(
                    frame, 
                    "Ada perubahan data di formulir yang belum disimpan.\nApakah Anda yakin ingin keluar?", 
                    "Konfirmasi Keluar", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE
                );
                if (opsi == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } else {
                System.exit(0);
            }
        };

        // Event Tombol Keluar & Tombol Close Window (X)
        btnKeluar.addActionListener(e -> prosesKeluar.run());
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                prosesKeluar.run();
            }
        });

        // Pengaturan Tampilan Window Utama
        frame.add(mainPanel);
        frame.setSize(450, 320);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}