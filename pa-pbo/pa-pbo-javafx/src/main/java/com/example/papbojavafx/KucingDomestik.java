package com.example.papbojavafx;

public class KucingDomestik extends Kucing implements PerawatanKucing {
    private boolean sudahVaksinasi;

    public KucingDomestik(int id, String nama, String ras, int usia, String status, String warna, boolean sudahVaksinasi) {
        super(id, nama, ras, usia, status, warna);
        this.sudahVaksinasi = sudahVaksinasi;
    }

    public boolean isSudahVaksinasi() { return sudahVaksinasi; }
    public void setSudahVaksinasi(boolean v) { this.sudahVaksinasi = v; }

    @Override
    public double hitungBiayaPerawatan(int bulan) {
        double cost = bulan * 75_000;
        return sudahVaksinasi ? cost * 0.9 : cost;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   📍 Vaksinasi: " + (sudahVaksinasi ? "Sudah Lengkap ✅" : "Belum/Perlu Update ⚠️"));
    }

    public void cekStatusVaksin() {
        System.out.println("💉 Status Vaksin " + getNama() + ": " + (sudahVaksinasi ? "Aman" : "Perlu Penjadwalan"));
    }

    @Override public void berikanPerawatanKhusus() {
        System.out.println("🧼 [Perawatan Domestik] " + getNama() + " diberi makanan premium dan grooming rutin.");
    }
    @Override public void cekKondisiKesehatan() {
        System.out.println("🩺 [Cek Kesehatan Domestik] " + getNama() + " - Suhu normal. Status vaksin: " + (sudahVaksinasi ? "Lengkap" : "Perlu update"));
    }
}
