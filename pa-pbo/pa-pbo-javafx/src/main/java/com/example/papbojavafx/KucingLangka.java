package com.example.papbojavafx;

public class KucingLangka extends Kucing implements PerawatanKucing {
    private boolean sudahVaksinasi;

    public KucingLangka(int id, String nama, String ras, int usia, String status, String warna, boolean sudahVaksinasi) {
        super(id, nama, ras, usia, status, warna);
        this.sudahVaksinasi = sudahVaksinasi;
    }

    public boolean isSudahVaksinasi() { return sudahVaksinasi; }
    public void setSudahVaksinasi(boolean v) { this.sudahVaksinasi = v; }

    @Override
    public double hitungBiayaPerawatan(int bulan) {
        return bulan * 75_000 * 1.5;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   💉 Vaksinasi: " + (sudahVaksinasi ? "Sudah Lengkap ✅" : "Belum/Perlu Update ⚠️"));
    }

    public void tampilkanDetailKhusus() {
        System.out.println("📂 [Protected Diakses] " + super.getDetailInternal());
        System.out.println("   💉 Status Vaksin: " + (sudahVaksinasi ? "Lengkap" : "Perlu update"));
    }

    @Override public void berikanPerawatanKhusus() {
        System.out.println("🌟 [Perawatan Langka] " + getNama() + " mendapat makanan import dan perawatan kulit khusus.");
    }
    @Override public void cekKondisiKesehatan() {
        System.out.println("🩺 [Cek Kesehatan Langka] " + getNama() + " - Kondisi prima. Vaksinasi: " + (sudahVaksinasi ? "Lengkap" : "Perlu update"));
    }
}
