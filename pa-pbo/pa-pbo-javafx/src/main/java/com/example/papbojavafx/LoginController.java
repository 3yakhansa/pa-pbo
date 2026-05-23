package com.example.papbojavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoginController {

    @FXML private VBox       loginPane;
    @FXML private VBox       registerPane;
    @FXML private Button     btnLoginTab;
    @FXML private Button     btnRegisterTab;
    @FXML private Label      lblMessage;
    @FXML private TextField  txtLoginUser;
    @FXML private PasswordField txtLoginPass;
    @FXML private TextField  txtRegisterUser;
    @FXML private PasswordField txtRegisterPass;
    @FXML private PasswordField txtRegisterConfirm;

    // ===================== DATA USERS =====================
    // Diakses oleh AdminDashboardController untuk CRUD staff
    public static ArrayList<User> users = new ArrayList<>();

    static {
        // Akun admin (bukan staff)
        users.add(new User("Administrator", "admin",    "admin123"));
        // Akun staff
        users.add(new User("Penjaga Satu",  "penjaga1", "penjaga111"));
        users.add(new User("User Satu",     "user1",    "user123"));
    }

    // ===================== TAB =====================

    @FXML public void showLoginForm() {
        loginPane.setVisible(true);
        loginPane.setManaged(true);
        registerPane.setVisible(false);
        registerPane.setManaged(false);
        btnLoginTab.getStyleClass().setAll("tab-active");
        btnRegisterTab.getStyleClass().setAll("tab-button");
        lblMessage.setText("");
    }

    @FXML public void showRegisterForm() {
        registerPane.setVisible(true);
        registerPane.setManaged(true);
        loginPane.setVisible(false);
        loginPane.setManaged(false);
        btnRegisterTab.getStyleClass().setAll("tab-active");
        btnLoginTab.getStyleClass().setAll("tab-button");
        lblMessage.setText("");
    }

    // ===================== LOGIN =====================

    @FXML public void handleLogin() {
        String username = txtLoginUser.getText().trim();
        String password = txtLoginPass.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("❌ Username dan password tidak boleh kosong.");
            return;
        }

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                Session.currentUser = u;

                // Cek apakah admin
                if (username.equals("admin")) {
                    Session.isAdmin = true;
                    showSuccess("✅ Selamat datang, Admin!");
                    openScene("admin_dashboard.fxml");
                } else {
                    Session.isAdmin = false;
                    showSuccess("✅ Selamat datang, " + username + "!");
                    openScene("dashboard.fxml");
                }
                return;
            }
        }
        showError("❌ Username atau password salah.");
    }

    // ===================== REGISTER =====================

    @FXML public void handleRegister() {
        String username = txtRegisterUser.getText().trim();
        String password = txtRegisterPass.getText();
        String confirm  = txtRegisterConfirm.getText();

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            showError("❌ Semua field harus diisi.");
            return;
        }
        if (!password.equals(confirm)) {
            showError("❌ Password dan konfirmasi tidak cocok.");
            return;
        }
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                showError("❌ Username sudah dipakai.");
                return;
            }
        }

        users.add(new User(username, username, password));
        showSuccess("✅ Register berhasil! Silakan login.");
        txtRegisterUser.clear();
        txtRegisterPass.clear();
        txtRegisterConfirm.clear();
        showLoginForm();
    }

    // ===================== HELPERS =====================

    private void openScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            Stage stage = (Stage) txtLoginUser.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showError(String msg) {
        lblMessage.setStyle("-fx-text-fill:#ef4444;");
        lblMessage.setText(msg);
    }

    private void showSuccess(String msg) {
        lblMessage.setStyle("-fx-text-fill:#4ade80;");
        lblMessage.setText(msg);
    }
}