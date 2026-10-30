package fahreziproject;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername = new JTextField();
    private JPasswordField txtPassword = new JPasswordField(); // karakter disamarkan
    private JButton btnLogin = new JButton("Login");
    private JLabel lblPesan = new JLabel(" ", SwingConstants.CENTER);

    public LoginFrame() {
        setTitle("Login - Aplikasi Deadline Tugas");
        setSize(350, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.add(new JLabel("Username"));
        panel.add(txtUsername);
        panel.add(new JLabel("Password"));
        panel.add(txtPassword);
        panel.add(new JLabel(""));
        panel.add(btnLogin);
        add(panel, "Center");

        lblPesan.setForeground(Color.RED);
        add(lblPesan, "South");

        btnLogin.addActionListener(e -> login());
        getRootPane().setDefaultButton(btnLogin); // Enter = Login
    }

    private void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (App.user.login(username, password)) {
            new DashboardFrame().setVisible(true);
            dispose();
        } else {
            lblPesan.setText("Login gagal! Username atau password salah.");
            txtPassword.setText("");
        }
    }
}
