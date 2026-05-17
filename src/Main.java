import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

public class Main {
    static ArrayList<Kucing> dataKucing = new ArrayList<>();
    static ArrayList<Adopsi> dataAdopsi = new ArrayList<>();
    static ArrayList<User> databaseUser = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int idCounter = 1;
    static User currentUser = null;

    public static void main(String[] args) {
        // ✅ Inisialisasi data awal
        dataKucing.add(new KucingDomestik(idCounter++, "Oren", "Domestik", 12, "Tersedia", "Orange", true));
        dataKucing.add(new KucingLangka(idCounter++, "Mochi", "Persia", 24, "Tersedia", "Putih", true));

        if (!autentikasiUser()) {
            System.out.println("Akses ditolak. Program ditutup.");
            return;
        }

        boolean programBerjalan = true;
        while (programBerjalan) {
            tampilkanMenuUtama();
            int pilihan = inputInteger("Pilih menu: ");

            switch (pilihan) {
                case 1: kelolaDataKucing(); break;
                case 2: kelolaAdopsi(); break;
                case 3:
                    System.out.println("👋 Terima kasih, " + currentUser.getUsername() + "! Program ditutup.");
                    programBerjalan = false;
                    break;
                default: System.out.println("⚠️ Pilihan tidak valid.");
            }
        }
        scanner.close();
    }

    // ================= AUTENTIKASI =================
    static boolean autentikasiUser() {
        while (true) {
            System.out.println("\n🔐 << Sistem Shelter PawPatrol >>");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Keluar");
            int authPilihan = inputInteger("Pilih: ");

            switch (authPilihan) {
                case 1: return login();
                case 2: register(); break;
                case 3: return false;
                default: System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static boolean login() {
        String u = inputString("Username: ");
        String p = inputString("Password: ");
        for (User user : databaseUser) {
            if (user.getUsername().equals(u) && user.getPassword().equals(p)) {
                currentUser = user;
                System.out.println("✅ Login berhasil! Selamat datang, " + u);
                return true;
            }
        }
        System.out.println("❌ Username atau password salah.");
        return false;
    }

    static void register() {
        String u = inputString("Buat Username: ");
        for (User user : databaseUser) {
            if (user.getUsername().equals(u)) {
                System.out.println("❌ Username sudah terpakai.");
                return;
            }
        }
        String p = inputString("Buat Password: ");
        databaseUser.add(new User(u, p));
        System.out.println("✅ Register berhasil! Silakan login.");
    }

    // ================= MENU UTAMA =================
    static void tampilkanMenuUtama() {
        System.out.println("\n🏠 << Menu Utama Shelter >> [User: " + currentUser.getUsername() + "]");
        System.out.println("1. Kelola Data Kucing");
        System.out.println("2. Kelola Adopsi");
        System.out.println("3. Logout & Keluar");
    }

    // ================= 1. KELOLA DATA KUCING =================
    static void kelolaDataKucing() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n📁 << Kelola Data Kucing >>");
            System.out.println("1. Tambah Kucing");
            System.out.println("2. Lihat Semua Kucing");
            System.out.println("3. Update Data Kucing");
            System.out.println("4. Hapus Kucing");
            System.out.println("5. Kembali ke Menu Utama");
            int sub = inputInteger("Pilih: ");

            switch (sub) {
                case 1: tambahKucing(); break;
                case 2: lihatKucing(); break;
                case 3: updateKucing(); break;
                case 4: hapusKucing(); break;
                case 5: kembali = true; break;
                default: System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void tambahKucing() {
        System.out.println("\n➕ Tambah Data Kucing");
        String nama = inputString("Nama Kucing: ");
        String warna = inputString("Warna Kucing: ");
        int usia = inputInteger("Usia (bulan): ");
        String statusDefault = "Tersedia";

        System.out.println("\n📂 Pilih Kategori Ras:");
        System.out.println("1. Domestik");
        System.out.println("2. Kucing Ras");
        int kategori = inputInteger("Pilihan: ");

        boolean vaksin = inputBoolean("Sudah Vaksin? (true/false atau ya/tidak): ");
        String rasDipilih = "";
        Kucing kucingBaru = null;

        if (kategori == 1) {
            rasDipilih = "Domestik";
            kucingBaru = new KucingDomestik(idCounter++, nama, rasDipilih, usia, statusDefault, warna, vaksin);
        } else if (kategori == 2) {
            String[] rasList = {"Persia", "Anggora", "Sphynx", "Maine Coon", "Siamese", "Ragdoll", "Bengal", "Munchkin", "Birman", "Scottish Fold"};
            System.out.println("Pilih Ras dari daftar:");
            for (int i = 0; i < rasList.length; i++) System.out.println((i+1) + ". " + rasList[i]);
            int idx = inputInteger("Nomor Ras: ");
            if (idx < 1 || idx > rasList.length) { System.out.println("❌ Nomor ras tidak valid."); return; }
            rasDipilih = rasList[idx-1];
            kucingBaru = new KucingLangka(idCounter++, nama, rasDipilih, usia, statusDefault, warna, vaksin);
        } else {
            System.out.println("❌ Kategori tidak valid."); return;
        }

        if (kucingBaru != null) {
            dataKucing.add(kucingBaru);
            System.out.println("✅ Data Kucing berhasil ditambah! (No. Kandang: " + kucingBaru.getNomorKandang() + ")");
        }
    }

    static void lihatKucing() {
        System.out.println("\n👀 Daftar Kucing:");
        if (dataKucing.isEmpty()) { System.out.println("Tidak ada data."); return; }
        for (Kucing k : dataKucing) {
            k.tampilkanInfo();
            double biaya = k.hitungBiayaPerawatan(1, true);
            System.out.printf("   💰 Est. Biaya : Rp %,d%n", (int)biaya);
            if (k instanceof PerawatanKucing) {
                ((PerawatanKucing) k).cekKondisiKesehatan();
                ((PerawatanKucing) k).berikanPerawatanKhusus();
            }
            System.out.println("--------------------");
        }
    }

    static void updateKucing() {
        lihatKucing();
        int idTarget = inputInteger("Masukkan ID kucing yang diupdate: ");
        Kucing target = dataKucing.stream().filter(k -> k.getId() == idTarget).findFirst().orElse(null);

        if (target != null) {
            System.out.println("\n✏️ Edit Data untuk: " + target.getNama());
            String n = inputStringOptional("Nama baru (Kosongkan untuk skip): ");
            if (!n.isEmpty()) target.setNama(n);

            String uStr = inputStringOptional("Usia baru (Kosongkan untuk skip): ");
            if (!uStr.isEmpty()) {
                try { target.setUsia(Integer.parseInt(uStr)); }
                catch (NumberFormatException e) { System.out.println("⚠️ Usia tidak valid, dilewati."); }
            }

            String w = inputStringOptional("Warna baru (Kosongkan untuk skip): ");
            if (!w.isEmpty()) target.setWarna(w);

            System.out.println("✅ Data berhasil diupdate.");
        } else System.out.println("❌ ID tidak ditemukan.");
    }

    static void hapusKucing() {
        lihatKucing();
        int idHapus = inputInteger("Masukkan ID kucing yang dihapus: ");
        boolean dihapus = dataKucing.removeIf(k -> k.getId() == idHapus);
        System.out.println(dihapus ? "✅ Data berhasil dihapus!" : "❌ ID tidak ditemukan.");
    }

    // ================= 2. KELOLA ADOPSI =================
    static void kelolaAdopsi() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n🤝 << Kelola Adopsi >>");
            System.out.println("1. Buat Proses Adopsi");
            System.out.println("2. Lihat Riwayat Adopsi");
            System.out.println("3. Kembali ke Menu Utama");
            int sub = inputInteger("Pilih: ");

            switch (sub) {
                case 1: buatAdopsi(); break;
                case 2: lihatAdopsi(); break;
                case 3: kembali = true; break;
                default: System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void buatAdopsi() {
        List<Kucing> tersedia = dataKucing.stream()
                .filter(k -> "Tersedia".equalsIgnoreCase(k.getStatus()))
                .collect(Collectors.toList());

        if (tersedia.isEmpty()) {
            System.out.println("⚠️ Tidak ada kucing dengan status 'Tersedia'.");
            return;
        }

        System.out.println("\n📋 Kucing Tersedia untuk Diadopsi:");
        for (Kucing k : tersedia) {
            System.out.printf("🐈 ID: %d | Nama: %s | Kandang: %d | Ras: %s | Warna: %s%n", k.getId(), k.getNama(), k.getNomorKandang(), k.getRas(), k.getWarna());
        }

        int idKucingAdopsi = inputInteger("Masukkan ID kucing yang ingin diadopsi: ");
        Kucing kucingTarget = tersedia.stream().filter(k -> k.getId() == idKucingAdopsi).findFirst().orElse(null);

        if (kucingTarget == null) {
            System.out.println("❌ ID tidak valid atau status tidak Tersedia.");
            return;
        }

        String namaAdopsi = inputString("Nama Pengadopsi: ");
        String noHp = inputTelepon("No. HP Pengadopsi: ");
        String tglAdopsi = inputTanggal("Tanggal Adopsi (YYYY-MM-DD): ");
        String tglKeluar = inputTanggal("Tanggal Keluar dari Shelter (YYYY-MM-DD): ");

        // Validasi logis: tanggal keluar tidak boleh sebelum tanggal adopsi
        if (LocalDate.parse(tglKeluar).isBefore(LocalDate.parse(tglAdopsi))) {
            System.out.println("❌ Tanggal keluar tidak boleh lebih awal dari tanggal adopsi!");
            return;
        }

        Adopsi adopsiBaru = new Adopsi(kucingTarget, namaAdopsi, noHp, tglAdopsi, tglKeluar);
        dataAdopsi.add(adopsiBaru);

        kucingTarget.setStatus("Diadopsi", "Diadopsi oleh " + namaAdopsi);
        System.out.println("✅ Proses adopsi berhasil dicatat!");
    }

    static void lihatAdopsi() {
        System.out.println("\n📜 Riwayat Adopsi:");
        if (dataAdopsi.isEmpty()) { System.out.println("Belum ada data adopsi."); return; }
        for (Adopsi a : dataAdopsi) a.tampilkanDetail();
    }

    // ================= 🛡️ ERROR HANDLING HELPER =================
    static String inputString(String pesan) {
        System.out.print(pesan);
        while (true) {
            String val = scanner.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println("⚠️ Input tidak boleh kosong!");
            System.out.print(pesan);
        }
    }

    static String inputStringOptional(String pesan) {
        System.out.print(pesan);
        return scanner.nextLine().trim();
    }

    static int inputInteger(String pesan) {
        System.out.print(pesan);
        while (true) {
            try {
                String val = scanner.nextLine().trim();
                return Integer.parseInt(val);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Harus berupa angka bulat!");
                System.out.print(pesan);
            }
        }
    }

    static boolean inputBoolean(String pesan) {
        System.out.print(pesan);
        while (true) {
            String val = scanner.nextLine().trim().toLowerCase();
            if (val.matches("true|ya|y|1")) return true;
            if (val.matches("false|tidak|n|0")) return false;
            System.out.println("⚠️ Gunakan 'true/false', 'ya/tidak', atau '1/0'.");
            System.out.print(pesan);
        }
    }

    static String inputTanggal(String pesan) {
        System.out.print(pesan);
        while (true) {
            String val = scanner.nextLine().trim();
            if (val.matches("\\d{4}-\\d{2}-\\d{2}")) {
                try {
                    LocalDate.parse(val); // Validasi tanggal valid secara kalender
                    return val;
                } catch (DateTimeParseException e) {
                    System.out.println("⚠️ Format benar, tapi tanggal tidak ada (misal: 2026-02-30).");
                }
            } else {
                System.out.println("⚠️ Format salah! Gunakan YYYY-MM-DD.");
            }
            System.out.print(pesan);
        }
    }

    static String inputTelepon(String pesan) {
        System.out.print(pesan);
        while (true) {
            String val = scanner.nextLine().trim();
            // Regex: diawali 08, digit 3-9, panjang 10-14 digit total
            if (val.matches("^08[1-9][0-9]{7,12}$")) return val;
            System.out.println("⚠️ Nomor tidak valid! Gunakan format: 08xxxxxxxxxx (10-14 digit).");
            System.out.print(pesan);
        }
    }
}