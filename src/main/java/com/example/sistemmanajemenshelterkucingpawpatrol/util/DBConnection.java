package com.example.sistemmanajemenshelterkucingpawpatrol.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_shelter", "root", "");
        } catch (Exception e) {
            System.out.println("Koneksi Database Gagal! Pastikan MySQL di XAMPP menyala.");
            e.printStackTrace();
            return null;
        }
    }
}