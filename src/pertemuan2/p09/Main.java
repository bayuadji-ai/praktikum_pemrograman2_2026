package pertemuan2.p09;

import java.awt.*;
import javax.swing.*;

public class Main {
    static void tampil() {
        JFrame f = new JFrame("Form Inventori");
        CardLayout layout = new CardLayout();
        JPanel kartu = new JPanel(layout);
        
        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        JTextField nama = new JTextField(16);
        JTextField stok = new JTextField(16);
        JButton tinjau = new JButton("Tinjau");
        JButton bersihkan = new JButton("Bersihkan"); 
        
        form.add(new JLabel("Nama:"));
        form.add(nama);
        form.add(new JLabel("Stok:"));
        form.add(stok);
        form.add(new JLabel("Belum disimpan"));
        form.add(tinjau);
        form.add(new JLabel("")); 
        form.add(bersihkan); 
        
        JPanel ringkas = new JPanel(new BorderLayout());
        JLabel hasil = new JLabel();
        JButton kembali = new JButton("Kembali");
        
        ringkas.add(hasil, BorderLayout.CENTER);
        ringkas.add(kembali, BorderLayout.SOUTH);
        
        kartu.add(form, "form");
        kartu.add(ringkas, "ringkas");
        
        tinjau.addActionListener(e -> {
            try {
                int nilai = Integer.parseInt(stok.getText().trim());
                if (nama.getText().isBlank() || nilai < 0 || nilai > 1000000) {
                    throw new IllegalArgumentException();
                }
                hasil.setText(nama.getText().trim() + ": " + nilai);
                layout.show(kartu, "ringkas");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(f, "Nama wajib diisi; stok bilangan bulat antara 0 - 1000000");
            }
        });
        
        kembali.addActionListener(e -> layout.show(kartu, "form"));
        
        bersihkan.addActionListener(e -> {
            nama.setText("");     
            stok.setText("");       
            nama.requestFocus();    
        });
        
        f.add(kartu);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::tampil);
    }
}

