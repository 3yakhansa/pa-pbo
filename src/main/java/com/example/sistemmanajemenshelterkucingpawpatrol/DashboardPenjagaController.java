package com.example.sistemmanajemenshelterkucingpawpatrol;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardPenjagaController {

    @FXML
    private Label labelWelcome;

    @FXML
    public void initialize() {
        labelWelcome.setText("Login berhasil! Selamat datang, Penjaga");
    }

    @FXML
    private void handleKelolaKucing() {
        System.out.println("Menu Kelola Data Kucing diklik");
    }

    @FXML
    private void handleKelolaAdopsi() {
        System.out.println("Menu Kelola Adopsi diklik");
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logout diklik");
    }
}