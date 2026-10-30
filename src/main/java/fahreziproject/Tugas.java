package fahreziproject;

import java.time.LocalDate;

public class Tugas {

    private String judul;
    private String mataKuliah;
    private LocalDate deadline;
    private String status; // Belum Dikerjakan / Sedang Dikerjakan / Selesai

    public Tugas(String judul, String mataKuliah, LocalDate deadline, String status) {
        this.judul = judul;
        this.mataKuliah = mataKuliah;
        this.deadline = deadline;
        this.status = status;
    }

    public String getJudul() { return judul; }
    public String getMataKuliah() { return mataKuliah; }
    public LocalDate getDeadline() { return deadline; }
    public String getStatus() { return status; }
}
