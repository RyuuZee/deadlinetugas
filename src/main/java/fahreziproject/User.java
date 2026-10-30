package fahreziproject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class User {

    private String username;
    private String passwordHash; // password disimpan dalam bentuk hash, bukan teks asli
    private String nama;

    public User(String username, String password, String nama) {
        this.username = username;
        this.passwordHash = hash(password);
        this.nama = nama;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.passwordHash.equals(hash(password));
    }

    public String getNama() {
        return nama;
    }

    // mengubah password menjadi hash SHA-256
    private static String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hasil = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hasil) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
