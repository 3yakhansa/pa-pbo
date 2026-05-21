package com.example.papbojavafx;

public class Adopsi {
    private static int counterAdopsi = 1;
    private final int idAdopsi;
    private Kucing kucing;
    private String namaPengadopsi;
    private String noHp;
    private String tanggalAdopsi;
    private String tanggalKeluar;

    public Adopsi(Kucing kucing, String namaPengadopsi, String noHp, String tanggalAdopsi, String tanggalKeluar) {
        this.idAdopsi = counterAdopsi++;
        this.kucing = kucing;
        this.namaPengadopsi = namaPengadopsi;
        this.noHp = noHp;
        this.tanggalAdopsi = tanggalAdopsi;
        this.tanggalKeluar = tanggalKeluar;
    }

    public void tampilkanDetail() {
        System.out.println("📋 ID com.example.papbojavafx.Adopsi: " + idAdopsi);
        System.out.println("👤 Pengadopsi: " + namaPengadopsi + " | 📱 No. HP: " + noHp);
        System.out.println("📅 Tgl com.example.papbojavafx.Adopsi: " + tanggalAdopsi + " | 🚪 Tgl Keluar: " + tanggalKeluar);
        System.out.println("🐈 com.example.papbojavafx.Kucing: " + kucing.getNama() + " (ID: " + kucing.getId() + ", Kandang: " + kucing.getNomorKandang() + ")");
        System.out.println("--------------------------------------------------");
    }

    public String getNamaKucing() {
        return kucing.getNama();
    }

    public String getNamaPengadopsi() {
        return namaPengadopsi;
    }

    public String getTanggalAdopsi() {
        return tanggalAdopsi;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }
}