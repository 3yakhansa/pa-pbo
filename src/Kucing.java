public abstract class Kucing {
    private static int counterKandang = 100;
    protected int id;
    protected final int nomorKandang;
    protected String nama;
    protected String ras;
    protected int usia;
    protected String status;
    protected String warna;

    public Kucing(int id, String nama, String ras, int usia, String status, String warna) {
        this.id = id;
        this.nama = nama;
        this.ras = ras;
        setUsia(usia);
        this.status = status;
        this.warna = warna;
        this.nomorKandang = counterKandang++;
    }

    public int getId() { return id; }
    public int getNomorKandang() { return nomorKandang; }
    public String getNama() { return nama; }
    public String getRas() { return ras; }
    public int getUsia() { return usia; }
    public String getStatus() { return status; }
    public String getWarna() { return warna; }

    public void setNama(String nama) { this.nama = nama; }
    public void setRas(String ras) { this.ras = ras; }
    public void setUsia(int usia) {
        if (usia > 0) this.usia = usia;
        else { System.out.println("⚠️ Usia tidak valid. Diatur ke 1 bulan."); this.usia = 1; }
    }
    public void setStatus(String status) { this.status = status; }
    public void setStatus(String status, String keterangan) {
        this.status = status;
        System.out.println("📝 [Catatan Update] " + nama + ": " + keterangan);
    }
    public void setWarna(String warna) { this.warna = warna; }

    public abstract double hitungBiayaPerawatan(int bulan);
    public double hitungBiayaPerawatan(int bulan, boolean includeExtraCare) {
        double cost = hitungBiayaPerawatan(bulan);
        if (includeExtraCare) cost += 50_000;
        return cost;
    }

    protected String getDetailInternal() {
        return "Detail Internal: K-" + id + " | Kandang: " + nomorKandang + " - " + nama + " (" + ras + ")";
    }

    public void tampilkanInfo() {
        System.out.printf("🐈 ID: %d | Kandang: %d | Nama: %-10s | Ras: %-12s | Usia: %2d bln | Warna: %-10s | Status: %s%n",
                id, nomorKandang, nama, ras, usia, warna, status);
    }

    @Override
    public String toString() {
        return String.format("Kucing{id=%d, kandang=%d, nama='%s', ras='%s', warna='%s', status='%s'}", id, nomorKandang, nama, ras, warna, status);
    }
}