package com.example.sistemmanajemenshelterkucingpawpatrol;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardAdminController {

    @FXML
    private Label menuLabel;

    @FXML
    public void initialize() {
        System.out.println("Dashboard Admin Berhasil Dimuat!");

        String menu = """
                Login berhasil! Selamat datang

                << Menu Utama Shelter >>
                1. Kelola Data Kucing
                2. Kelola Adopsi
                3. Logout & Keluar
                """;

        menuLabel.setText(menu);
    }
}