package com.example.sistemmanajemenshelterkucingpawpatrol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.sistemmanajemenshelterkucingpawpatrol.util.DBConnection;

public class Login {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label welcomeText;

    @FXML
    protected void handleLogin(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            welcomeText.setStyle("-fx-text-fill: red;");
            welcomeText.setText("Username dan Password tidak boleh kosong!");
            return;
        }

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM akun WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role"); // Ini ambil data role

                welcomeText.setStyle("-fx-text-fill: green;");
                welcomeText.setText("Login Berhasil! Mengalihkan...");

                // --- pindah hal
                try {
                    String fxmlFile = "";
                    if (role.equals("Admin")) {
                        fxmlFile = "/com/example/sistemmanajemenshelterkucingpawpatrol/dashboard-admin.fxml";
                    } else if (role.equals("Penjaga")) {
                        fxmlFile = "/com/example/sistemmanajemenshelterkucingpawpatrol/dashboard-penjaga.fxml";
                    }
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                    Parent root = loader.load();

                    Stage stage = (Stage) btnLogin.getScene().getWindow();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Dashboard " + role + " - Paw Patrol Shelter");
                    stage.centerOnScreen();
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                    welcomeText.setStyle("-fx-text-fill: red;");
                    welcomeText.setText("Gagal memuat halaman dashboard!");
                }

            } else {
                welcomeText.setStyle("-fx-text-fill: red;");
                welcomeText.setText("Username atau Password salah!");
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            welcomeText.setStyle("-fx-text-fill: red;");
            welcomeText.setText("Terjadi kesalahan saat menghubungi database!");
        }
    }
}