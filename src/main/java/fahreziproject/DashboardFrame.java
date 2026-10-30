package fahreziproject;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DashboardFrame extends JFrame {

    private JLabel lblBelum = new JLabel("0", SwingConstants.CENTER);
    private JLabel lblSedang = new JLabel("0", SwingConstants.CENTER);
    private JLabel lblSelesai = new JLabel("0", SwingConstants.CENTER);
    private DefaultTableModel model = new DefaultTableModel(
            new String[]{"Judul Tugas", "Mata Kuliah", "Deadline", "Sisa Hari", "Status"}, 0);

    public DashboardFrame() {
        setTitle("Dashboard - Aplikasi Deadline Tugas");
        setSize(900, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // bagian atas: sapaan + tombol logout
        JPanel atas = new JPanel(new BorderLayout());
        atas.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        JLabel lblJudul = new JLabel("Selamat datang, " + App.user.getNama());
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 16));
        JButton btnLogout = new JButton("Logout");
        atas.add(lblJudul, BorderLayout.WEST);
        atas.add(btnLogout, BorderLayout.EAST);
        add(atas, BorderLayout.NORTH);

        // bagian tengah: ringkasan jumlah tugas + tabel deadline
        JPanel ringkasan = new JPanel(new GridLayout(2, 3));
        ringkasan.setBorder(BorderFactory.createTitledBorder("Ringkasan Tugas"));
        ringkasan.add(new JLabel("Belum Dikerjakan", SwingConstants.CENTER));
        ringkasan.add(new JLabel("Sedang Dikerjakan", SwingConstants.CENTER));
        ringkasan.add(new JLabel("Selesai", SwingConstants.CENTER));
        ringkasan.add(lblBelum);
        ringkasan.add(lblSedang);
        ringkasan.add(lblSelesai);
        Font besar = new Font("SansSerif", Font.BOLD, 24);
        lblBelum.setFont(besar);
        lblSedang.setFont(besar);
        lblSelesai.setFont(besar);

        JTable tabel = new JTable(model);
        tabel.setDefaultEditor(Object.class, null); // tabel tidak bisa diedit
        tabel.setRowHeight(24);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(220);
        tabel.getColumnModel().getColumn(1).setPreferredWidth(220);
        JScrollPane scroll = new JScrollPane(tabel);
        scroll.setBorder(BorderFactory.createTitledBorder("Tugas dengan Deadline Terdekat"));

        JPanel tengah = new JPanel(new BorderLayout(10, 10));
        tengah.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        tengah.add(ringkasan, BorderLayout.NORTH);
        tengah.add(scroll, BorderLayout.CENTER);
        add(tengah, BorderLayout.CENTER);

        btnLogout.addActionListener(e -> logout());

        muatData();
    }

    // mengisi ringkasan dan tabel dari daftar tugas
    // (panggil lagi method ini setiap kali data tugas berubah)
    public void muatData() {
        int belum = 0, sedang = 0, selesai = 0;
        for (Tugas t : App.daftarTugas) {
            if (t.getStatus().equals("Belum Dikerjakan")) belum++;
            else if (t.getStatus().equals("Sedang Dikerjakan")) sedang++;
            else if (t.getStatus().equals("Selesai")) selesai++;
        }
        lblBelum.setText(String.valueOf(belum));
        lblSedang.setText(String.valueOf(sedang));
        lblSelesai.setText(String.valueOf(selesai));

        // ambil tugas yang belum selesai, urutkan dari deadline terdekat
        ArrayList<Tugas> daftar = new ArrayList<>();
        for (Tugas t : App.daftarTugas) {
            if (!t.getStatus().equals("Selesai")) daftar.add(t);
        }
        daftar.sort(Comparator.comparing(Tugas::getDeadline));

        model.setRowCount(0);
        for (Tugas t : daftar) {
            long sisa = ChronoUnit.DAYS.between(LocalDate.now(), t.getDeadline());
            model.addRow(new Object[]{
                t.getJudul(), t.getMataKuliah(), t.getDeadline(), sisa + " hari", t.getStatus()
            });
        }
    }

    private void logout() {
        int pilih = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Logout",
                JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
            new LoginFrame().setVisible(true);
            dispose();
        }
    }
}
