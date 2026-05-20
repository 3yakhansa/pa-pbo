# TODO - Periksa Kenapa Register Tidak Masuk Database

## Langkah
- [ ] Buat rencana edit berdasarkan hasil inspeksi kode (`src/Main.java`, `src/DBConnection.java`, skema `db_shelter.sql`).
- [ ] Implementasi perbaikan: tambahkan `INSERT INTO akun` saat user register.
- [ ] Pastikan penanganan duplikasi user (UNIQUE di `akun.username`) agar tidak crash.
- [ ] Ubah `register()` agar juga menambahkan user ke `databaseUser` setelah sukses insert.
- [ ] Uji alur: register -> login (tanpa restart) -> buka ulang program (user tetap ada di DB).

