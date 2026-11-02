package Tugas1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FormPendaftaranKegiatan extends JFrame {
    private final JTextField namaField = new JTextField(22);
    private final JTextField nimField = new JTextField(22);
    private final JTextField emailField = new JTextField(22);
    private final JTextField teleponField = new JTextField(22);

    private final JComboBox<String> kegiatanCombo = new JComboBox<>(
            new String[]{"Seminar Teknologi", "Workshop Pemrograman", "Kompetisi UI/UX", "Pelatihan Data"});
    private final JRadioButton mahasiswaRadio = new JRadioButton("Mahasiswa", true);
    private final JRadioButton umumRadio = new JRadioButton("Umum");
    private final JCheckBox konsumsiCheck = new JCheckBox("Paket konsumsi");
    private final JCheckBox sertifikatCheck = new JCheckBox("Sertifikat cetak");
    private final JTextArea catatanArea = new JTextArea(4, 22);

    public FormPendaftaranKegiatan() {
        super("Formulir Pendaftaran Kegiatan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(520, 560));

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));

        JLabel titleLabel = new JLabel("FORMULIR PENDAFTARAN KEGIATAN");
        titleLabel.setFont(titleLabel.getFont().deriveFont(java.awt.Font.BOLD, 18f));
        JLabel subtitleLabel = new JLabel("Silakan isi data peserta dan pilihan kegiatan.");
        JPanel headingPanel = new JPanel(new BorderLayout(0, 4));
        headingPanel.add(titleLabel, BorderLayout.NORTH);
        headingPanel.add(subtitleLabel, BorderLayout.SOUTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 4, 6, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addFormRow(formPanel, gbc, 0, "Nama lengkap", namaField);
        addFormRow(formPanel, gbc, 1, "NIM", nimField);
        addFormRow(formPanel, gbc, 2, "Email", emailField);
        addFormRow(formPanel, gbc, 3, "Nomor telepon", teleponField);
        addFormRow(formPanel, gbc, 4, "Kegiatan", kegiatanCombo);

        ButtonGroup jenisPesertaGroup = new ButtonGroup();
        jenisPesertaGroup.add(mahasiswaRadio);
        jenisPesertaGroup.add(umumRadio);
        JPanel jenisPesertaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        jenisPesertaPanel.add(mahasiswaRadio);
        jenisPesertaPanel.add(umumRadio);
        addFormRow(formPanel, gbc, 5, "Jenis peserta", jenisPesertaPanel);

        JPanel opsiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        opsiPanel.add(konsumsiCheck);
        opsiPanel.add(sertifikatCheck);
        addFormRow(formPanel, gbc, 6, "Pilihan tambahan", opsiPanel);

        catatanArea.setLineWrap(true);
        catatanArea.setWrapStyleWord(true);
        JScrollPane catatanScroll = new JScrollPane(catatanArea);
        addFormRow(formPanel, gbc, 7, "Catatan", catatanScroll);

        JPanel identityPanel = new JPanel(new BorderLayout());
        identityPanel.setBorder(BorderFactory.createTitledBorder("Identitas dan Pilihan Kegiatan"));
        identityPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        JButton resetButton = new JButton("Reset");
        JButton daftarButton = new JButton("Daftar");
        buttonPanel.add(resetButton);
        buttonPanel.add(daftarButton);

        resetButton.addActionListener(e -> resetForm());
daftarButton.addActionListener(e -> tampilkanRingkasan());

        JPanel centerPanel = new JPanel(new BorderLayout(0, 8));
        centerPanel.add(identityPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.add(headingPanel, BorderLayout.NORTH);
        contentPanel.add(centerPanel, BorderLayout.CENTER);
        setContentPane(contentPanel);

        setFocusTraversalPolicy(new java.awt.ContainerOrderFocusTraversalPolicy());
        setFocusTraversalPolicyProvider(true);

        pack();
        setLocationRelativeTo(null);
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row,
                            String labelText, java.awt.Component component) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel label = new JLabel(labelText + ":");
        label.setLabelFor(component);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(component, gbc);
    }

    private void resetForm() {
        namaField.setText("");
        nimField.setText("");
        emailField.setText("");
        teleponField.setText("");
        kegiatanCombo.setSelectedIndex(0);
        mahasiswaRadio.setSelected(true);
        konsumsiCheck.setSelected(false);
        sertifikatCheck.setSelected(false);
        catatanArea.setText("");
        namaField.requestFocusInWindow();
    }

    private void tampilkanRingkasan() {
        String nama = namaField.getText().trim();
        String nim = nimField.getText().trim();

        if (nama.isEmpty() || nim.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nama lengkap dan NIM wajib diisi.",
                    "Data belum lengkap",
                    JOptionPane.WARNING_MESSAGE
            );
            if (nama.isEmpty()) {
                namaField.requestFocusInWindow();
            } else {
                nimField.requestFocusInWindow();
            }
            return;
        }

        String jenisPeserta = mahasiswaRadio.isSelected() ? "Mahasiswa" : "Umum";
        StringBuilder tambahan = new StringBuilder();
        if (konsumsiCheck.isSelected()) {
            tambahan.append("Paket konsumsi");
        }
        if (sertifikatCheck.isSelected()) {
            if (tambahan.length() > 0) {
                tambahan.append(", ");
            }
            tambahan.append("Sertifikat cetak");
        }
        if (tambahan.length() == 0) {
            tambahan.append("Tidak ada");
        }

        String ringkasan = "Pendaftaran berhasil diperiksa.\n\n"
                + "Nama: " + nama + "\n"
                + "NIM: " + nim + "\n"
                + "Email: " + emailField.getText().trim() + "\n"
                + "Nomor telepon: " + teleponField.getText().trim() + "\n"
                + "Kegiatan: " + kegiatanCombo.getSelectedItem() + "\n"
                + "Jenis peserta: " + jenisPeserta + "\n"
                + "Pilihan tambahan: " + tambahan + "\n"
                + "Catatan: " + catatanArea.getText().trim();

        JOptionPane.showMessageDialog(this, ringkasan, "Ringkasan Pendaftaran",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new FormPendaftaranKegiatan().setVisible(true);
        });
    }
}