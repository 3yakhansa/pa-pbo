package com.example.papbojavafx;

public class User {
    private String nama;
    private String username;
    private String password;

    // Constructor lengkap
    public User(String nama, String username, String password) {
        this.nama     = nama;
        this.username = username;
        this.password = password;
    }

    // Constructor ringkas (backward-compatible, nama = username)
    public User(String username, String password) {
        this(username, username, password);
    }

    public String getNama()     { return nama; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setNama(String nama)         { this.nama = nama; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}