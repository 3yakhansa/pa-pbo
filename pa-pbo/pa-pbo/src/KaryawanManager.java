import java.util.ArrayList;
import java.util.Scanner;

public class KaryawanManager {
    private final ArrayList<Karyawan> dataKaryawan = new ArrayList<>();
    private int nextId = 1;

    public void initSampleData() {
        dataKaryawan.add(new Karyawan(nextId++, "Budi", "Manajer", "081234567890"));
        dataKaryawan.add(new Karyawan(nextId++, "Siti", "Staf Penjaga", "081298765432"));
    }

    public void tambahKaryawan(Scanner scanner) {
        System.out.println("\n➕ Tambah Karyawan");
        String nama = inputString(scanner, "Nama Karyawan: ");
        String posisi = inputString(scanner, "Posisi: ");
        String kontak = inputString(scanner, "Kontak (telp/email): ");
        Karyawan k = new Karyawan(nextId++, nama, posisi, kontak);
        dataKaryawan.add(k);
        System.out.println("✅ Karyawan berhasil ditambahkan (ID: " + k.getId() + ")");
    }

    public void lihatKaryawan() {
        System.out.println("\n📋 Daftar Karyawan:");
        if (dataKaryawan.isEmpty()) {
            System.out.println("Belum ada karyawan.");
            return;
        }
        for (Karyawan k : dataKaryawan) {
            k.tampilkanInfo();
        }
    }

    public void updateKaryawan(Scanner scanner) {
        lihatKaryawan();
        int id = inputInteger(scanner, "Masukkan ID karyawan yang diupdate: ");
        Karyawan target = dataKaryawan.stream().filter(k -> k.getId() == id).findFirst().orElse(null);
        if (target == null) {
            System.out.println("❌ ID tidak ditemukan.");
            return;
        }
        System.out.println("\n✏️ Edit Karyawan: " + target.getNama());
        String n = inputStringOptional(scanner, "Nama baru (Kosongkan untuk skip): ");
        if (!n.isEmpty()) target.setNama(n);
        String p = inputStringOptional(scanner, "Posisi baru (Kosongkan untuk skip): ");
        if (!p.isEmpty()) target.setPosisi(p);
        String c = inputStringOptional(scanner, "Kontak baru (Kosongkan untuk skip): ");
        if (!c.isEmpty()) target.setKontak(c);
        System.out.println("✅ Data karyawan berhasil diupdate.");
    }

    public void hapusKaryawan(Scanner scanner) {
        lihatKaryawan();
        int id = inputInteger(scanner, "Masukkan ID karyawan yang dihapus: ");
        boolean removed = dataKaryawan.removeIf(k -> k.getId() == id);
        System.out.println(removed ? "✅ Karyawan berhasil dihapus." : "❌ ID tidak ditemukan.");
    }

    private String inputString(Scanner scanner, String pesan) {
        System.out.print(pesan);
        while (true) {
            String val = scanner.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println("⚠️ Input tidak boleh kosong!");
            System.out.print(pesan);
        }
    }

    private String inputStringOptional(Scanner scanner, String pesan) {
        System.out.print(pesan);
        return scanner.nextLine().trim();
    }

    private int inputInteger(Scanner scanner, String pesan) {
        System.out.print(pesan);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Harus berupa angka bulat!");
                System.out.print(pesan);
            }
        }
    }
}
