package fahreziproject;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {

    // data aplikasi (sementara disimpan di memori)
    static User user = new User("admin", "123", "Muhammad Fahrezi");
    static ArrayList<Tugas> daftarTugas = new ArrayList<>();

    public static void main(String[] args) {
        LocalDate hariIni = LocalDate.now();
        daftarTugas.add(new Tugas("Laporan Akhir Activity 1", "Pemrograman Berorientasi Objek", hariIni.plusDays(1), "Sedang Dikerjakan"));
        daftarTugas.add(new Tugas("Makalah Wawasan Kebangsaan", "Kewarganegaraan", hariIni.plusDays(3), "Belum Dikerjakan"));
        daftarTugas.add(new Tugas("Rangkuman Bab 4", "Sistem Basis Data", hariIni.plusDays(5), "Belum Dikerjakan"));
        daftarTugas.add(new Tugas("Desain ERD Aplikasi", "Rekayasa Perangkat Lunak", hariIni.plusDays(7), "Sedang Dikerjakan"));
        daftarTugas.add(new Tugas("Resume Jurnal", "Metodologi Penelitian", hariIni.minusDays(2), "Selesai"));

        new LoginFrame().setVisible(true);
    }
}
