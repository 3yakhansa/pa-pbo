public class Karyawan {
    private int id;
    private String nama;
    private String posisi;
    private String kontak;

    public Karyawan(int id, String nama, String posisi, String kontak) {
        this.id = id;
        this.nama = nama;
        this.posisi = posisi;
        this.kontak = kontak;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getPosisi() { return posisi; }
    public String getKontak() { return kontak; }

    public void setNama(String nama) { this.nama = nama; }
    public void setPosisi(String posisi) { this.posisi = posisi; }
    public void setKontak(String kontak) { this.kontak = kontak; }

    public void tampilkanInfo() {
        System.out.printf("ID: %d | Nama: %s | Posisi: %s | Kontak: %s%n", id, nama, posisi, kontak);
    }
}
