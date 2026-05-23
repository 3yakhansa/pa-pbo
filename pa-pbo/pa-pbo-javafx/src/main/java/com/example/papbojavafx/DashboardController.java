package com.example.papbojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class DashboardController {

    // ================= NAVIGATION =================

    @FXML private Button btnNavData;
    @FXML private Button btnNavTambah;
    @FXML private Button btnNavAdopsi;
    @FXML private Button btnNavRiwayat;

    @FXML private VBox sectionData;
    @FXML private VBox sectionTambah;
    @FXML private VBox sectionEdit;
    @FXML private VBox sectionAdopsi;
    @FXML private VBox sectionRiwayat;

    @FXML public void showDataSection()    { setActiveSection(sectionData,    btnNavData); }
    @FXML public void showTambahSection()  { setActiveSection(sectionTambah,  btnNavTambah); }
    @FXML public void showAdopsiSection()  { setActiveSection(sectionAdopsi,  btnNavAdopsi); }
    @FXML public void showRiwayatSection() { setActiveSection(sectionRiwayat, btnNavRiwayat); }

    private void setActiveSection(VBox activeSection, Button activeBtn) {
        // sectionEdit tidak punya tombol nav — sembunyikan juga saat nav lain diklik
        VBox[]    sections = {sectionData, sectionTambah, sectionEdit, sectionAdopsi, sectionRiwayat};
        Button[]  buttons  = {btnNavData,  btnNavTambah,  null,        btnNavAdopsi,  btnNavRiwayat};

        for (int i = 0; i < sections.length; i++) {
            boolean isActive = sections[i] == activeSection;
            sections[i].setVisible(isActive);
            sections[i].setManaged(isActive);
            if (buttons[i] != null)
                buttons[i].getStyleClass().setAll(isActive ? "nav-button-active" : "nav-button");
        }
    }

    // ================= TABLE KUCING =================

    @FXML private TableView<Kucing>            tableKucing;
    @FXML private TableColumn<Kucing, Integer> colId;
    @FXML private TableColumn<Kucing, String>  colNama;
    @FXML private TableColumn<Kucing, String>  colRas;
    @FXML private TableColumn<Kucing, String>  colJenis;
    @FXML private TableColumn<Kucing, Integer> colUsia;
    @FXML private TableColumn<Kucing, String>  colWarna;
    @FXML private TableColumn<Kucing, String>  colVaksin;
    @FXML private TableColumn<Kucing, String>  colBiaya;
    @FXML private TableColumn<Kucing, String>  colStatus;
    @FXML private TableColumn<Kucing, Void>    colAksi;

    // ================= STATISTIK =================

    @FXML private Label lblTotal;
    @FXML private Label lblTersedia;
    @FXML private Label lblAdopsi;
    @FXML private Label lblUser;

    // ================= FORM TAMBAH =================

    @FXML private TextField        txtNama;
    @FXML private TextField        txtWarna;
    @FXML private TextField        txtUsia;
    @FXML private ComboBox<String> cbJenis;
    @FXML private VBox             vboxRasLangka;
    @FXML private ComboBox<String> cbRasLangka;
    @FXML private ComboBox<String> cbVaksin;

    // ================= FORM EDIT =================

    @FXML private TextField        txtEditNama;
    @FXML private TextField        txtEditWarna;
    @FXML private TextField        txtEditUsia;
    @FXML private ComboBox<String> cbEditJenis;
    @FXML private VBox             vboxEditRasLangka;
    @FXML private ComboBox<String> cbEditRasLangka;
    @FXML private ComboBox<String> cbEditVaksin;

    private Kucing editTarget = null;

    // ================= FORM ADOPSI =================

    @FXML private ComboBox<Kucing> cbKucingAdopsi;
    @FXML private TextField        txtNamaAdopsi;
    @FXML private TextField        txtHpAdopsi;
    @FXML private DatePicker       dpTanggalAdopsi;
    @FXML private DatePicker       dpTanggalKeluar;

    // ================= TABLE ADOPSI =================

    @FXML private TableView<Adopsi>           tableAdopsi;
    @FXML private TableColumn<Adopsi, String> colNamaKucing;
    @FXML private TableColumn<Adopsi, String> colPengadopsi;
    @FXML private TableColumn<Adopsi, String> colTanggalAdopsi;
    @FXML private TableColumn<Adopsi, String> colTanggalKeluar;

    // ================= COLLECTION =================

    private static final ArrayList<Kucing> dataKucing = new ArrayList<>();
    private static final ArrayList<Adopsi> dataAdopsi = new ArrayList<>();
    private ObservableList<Kucing> observableKucing;
    private ObservableList<Adopsi> observableAdopsi;
    private int idCounter = 1;

    private static final String[] RAS_LANGKA = {
            "Persia", "Maine Coon", "Scottish Fold", "Bengal",
            "Ragdoll", "Sphynx", "Birman", "Siamese",
            "Norwegian Forest", "Abyssinian"
    };

    // ================= INITIALIZE =================

    @FXML
    public void initialize() {

        if (Session.currentUser != null)
            lblUser.setText("Login sebagai: " + Session.currentUser.getUsername());

        // --- kolom tabel kucing ---
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colRas.setCellValueFactory(new PropertyValueFactory<>("ras"));
        colUsia.setCellValueFactory(new PropertyValueFactory<>("usia"));
        colWarna.setCellValueFactory(new PropertyValueFactory<>("warna"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colJenis.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null); setGraphic(null);
                } else {
                    Kucing k = (Kucing) getTableRow().getItem();
                    boolean langka = k instanceof KucingLangka;
                    Label badge = new Label(langka ? "🌟 Langka" : "🏠 Domestik");
                    badge.getStyleClass().add(langka ? "badge-langka" : "badge-domestik");
                    setGraphic(badge); setText(null);
                }
            }
        });

        colVaksin.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null); setGraphic(null);
                } else {
                    Kucing k = (Kucing) getTableRow().getItem();
                    boolean v = (k instanceof KucingDomestik kd) ? kd.isSudahVaksinasi()
                            : (k instanceof KucingLangka kl)   ? kl.isSudahVaksinasi() : false;
                    Label badge = new Label(v ? "✅ Ya" : "❌ Tidak");
                    badge.getStyleClass().add(v ? "badge-vaksin" : "badge-novaksin");
                    setGraphic(badge); setText(null);
                }
            }
        });

        colBiaya.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    setText(String.format("Rp%,.0f", getTableRow().getItem().hitungBiayaPerawatan(1)));
                }
            }
        });

        // --- kolom tabel adopsi ---
        colNamaKucing.setCellValueFactory(new PropertyValueFactory<>("namaKucing"));
        colPengadopsi.setCellValueFactory(new PropertyValueFactory<>("namaPengadopsi"));
        colTanggalAdopsi.setCellValueFactory(new PropertyValueFactory<>("tanggalAdopsi"));
        colTanggalKeluar.setCellValueFactory(new PropertyValueFactory<>("tanggalKeluar"));

        observableKucing = FXCollections.observableArrayList(dataKucing);
        observableAdopsi = FXCollections.observableArrayList(dataAdopsi);
        tableKucing.setItems(observableKucing);
        tableAdopsi.setItems(observableAdopsi);

        // --- setup form tambah ---
        cbJenis.getItems().addAll("Domestik", "Langka");
        cbVaksin.getItems().addAll("Sudah Vaksin", "Belum Vaksin");
        cbRasLangka.getItems().addAll(RAS_LANGKA);
        cbJenis.valueProperty().addListener((obs, o, newVal) -> {
            boolean langka = "Langka".equals(newVal);
            vboxRasLangka.setVisible(langka);
            vboxRasLangka.setManaged(langka);
            if (!langka) cbRasLangka.setValue(null);
        });

        // --- setup form edit ---
        cbEditJenis.getItems().addAll("Domestik", "Langka");
        cbEditVaksin.getItems().addAll("Sudah Vaksin", "Belum Vaksin");
        cbEditRasLangka.getItems().addAll(RAS_LANGKA);
        cbEditJenis.valueProperty().addListener((obs, o, newVal) -> {
            boolean langka = "Langka".equals(newVal);
            vboxEditRasLangka.setVisible(langka);
            vboxEditRasLangka.setManaged(langka);
            if (!langka) cbEditRasLangka.setValue(null);
        });

        // --- data awal ---
        if (dataKucing.isEmpty()) {
            dataKucing.add(new KucingDomestik(idCounter++, "Oren",  "Domestik", 12, "Tersedia", "Orange", true));
            dataKucing.add(new KucingLangka(  idCounter++, "Mochi", "Persia",   24, "Tersedia", "Putih",  true));
        }

        observableKucing.setAll(dataKucing);
        setupActionButtons();
        refreshComboKucing();
        updateStats();
        showDataSection();
    }

    // ================= TAMBAH KUCING =================

    @FXML
    public void handleTambahKucing() {
        try {
            String nama      = txtNama.getText().trim();
            String warna     = txtWarna.getText().trim();
            int    usia      = Integer.parseInt(txtUsia.getText().trim());
            String jenis     = cbJenis.getValue();
            String vaksinVal = cbVaksin.getValue();

            if (nama.isEmpty() || warna.isEmpty() || jenis == null || vaksinVal == null) {
                showError("Semua field harus diisi!"); return;
            }

            boolean vaksin = "Sudah Vaksin".equals(vaksinVal);
            Kucing baru;

            if ("Domestik".equals(jenis)) {
                baru = new KucingDomestik(idCounter++, nama, "Domestik", usia, "Tersedia", warna, vaksin);
            } else {
                String ras = cbRasLangka.getValue();
                if (ras == null) { showError("Pilih ras kucing langka terlebih dahulu!"); return; }
                baru = new KucingLangka(idCounter++, nama, ras, usia, "Tersedia", warna, vaksin);
            }

            dataKucing.add(baru);
            observableKucing.setAll(dataKucing);
            clearFormTambah();
            refreshComboKucing();
            updateStats();
            showInfo("✅ Kucing berhasil ditambahkan!");
            showDataSection();

        } catch (NumberFormatException e) {
            showError("Usia harus berupa angka!");
        }
    }

    // ================= EDIT KUCING =================

    private void loadEditForm(Kucing k) {
        editTarget = k;

        txtEditNama.setText(k.getNama());
        txtEditWarna.setText(k.getWarna());
        txtEditUsia.setText(String.valueOf(k.getUsia()));

        boolean langka = k instanceof KucingLangka;
        cbEditJenis.setValue(langka ? "Langka" : "Domestik");

        if (langka) {
            cbEditRasLangka.setValue(k.getRas());
            vboxEditRasLangka.setVisible(true);
            vboxEditRasLangka.setManaged(true);
        } else {
            cbEditRasLangka.setValue(null);
            vboxEditRasLangka.setVisible(false);
            vboxEditRasLangka.setManaged(false);
        }

        boolean vaksin = (k instanceof KucingDomestik kd) ? kd.isSudahVaksinasi()
                : (k instanceof KucingLangka kl)   ? kl.isSudahVaksinasi() : false;
        cbEditVaksin.setValue(vaksin ? "Sudah Vaksin" : "Belum Vaksin");

        // Tampilkan section edit, sembunyikan semua lainnya
        VBox[] all = {sectionData, sectionTambah, sectionEdit, sectionAdopsi, sectionRiwayat};
        for (VBox s : all) { s.setVisible(s == sectionEdit); s.setManaged(s == sectionEdit); }
        // Tidak ada tombol nav aktif saat edit
        for (Button b : new Button[]{btnNavData, btnNavTambah, btnNavAdopsi, btnNavRiwayat})
            b.getStyleClass().setAll("nav-button");
    }

    @FXML
    public void handleSimpanEdit() {
        if (editTarget == null) return;
        try {
            String nama      = txtEditNama.getText().trim();
            String warna     = txtEditWarna.getText().trim();
            int    usia      = Integer.parseInt(txtEditUsia.getText().trim());
            String jenis     = cbEditJenis.getValue();
            String vaksinVal = cbEditVaksin.getValue();

            if (nama.isEmpty() || warna.isEmpty() || jenis == null || vaksinVal == null) {
                showError("Semua field harus diisi!"); return;
            }

            boolean vaksin = "Sudah Vaksin".equals(vaksinVal);

            // Jika jenis berubah: ganti objek di list
            boolean targetLangka = editTarget instanceof KucingLangka;
            boolean wantLangka   = "Langka".equals(jenis);

            if (targetLangka != wantLangka) {
                // Harus ganti tipe — buat objek baru dengan id & status yang sama
                String ras = wantLangka ? cbEditRasLangka.getValue() : "Domestik";
                if (wantLangka && ras == null) {
                    showError("Pilih ras kucing langka terlebih dahulu!"); return;
                }
                Kucing baru = wantLangka
                        ? new KucingLangka(editTarget.getId(), nama, ras, usia, editTarget.getStatus(), warna, vaksin)
                        : new KucingDomestik(editTarget.getId(), nama, "Domestik", usia, editTarget.getStatus(), warna, vaksin);
                int idx = dataKucing.indexOf(editTarget);
                dataKucing.set(idx, baru);
            } else {
                // Tipe sama — update field
                editTarget.setNama(nama);
                editTarget.setWarna(warna);
                editTarget.setUsia(usia);

                if (wantLangka) {
                    String ras = cbEditRasLangka.getValue();
                    if (ras == null) { showError("Pilih ras kucing langka terlebih dahulu!"); return; }
                    editTarget.setRas(ras);
                    ((KucingLangka) editTarget).setSudahVaksinasi(vaksin);
                } else {
                    ((KucingDomestik) editTarget).setSudahVaksinasi(vaksin);
                }
            }

            editTarget = null;
            observableKucing.setAll(dataKucing);
            refreshComboKucing();
            updateStats();
            showInfo("💾 Data kucing berhasil diperbarui!");
            showDataSection();

        } catch (NumberFormatException e) {
            showError("Usia harus berupa angka!");
        }
    }

    @FXML
    public void handleBatalEdit() {
        editTarget = null;
        showDataSection();
    }

    // ================= ADOPSI =================

    @FXML
    public void handleAdopsi() {
        try {
            Kucing kucing = cbKucingAdopsi.getValue();
            String nama   = txtNamaAdopsi.getText().trim();
            String hp     = txtHpAdopsi.getText().trim();
            LocalDate tglAdop = dpTanggalAdopsi.getValue();
            LocalDate tglKel  = dpTanggalKeluar.getValue();

            if (kucing == null || nama.isEmpty() || hp.isEmpty() || tglAdop == null || tglKel == null) {
                showError("Semua field adopsi harus diisi!"); return;
            }
            if (tglKel.isBefore(tglAdop)) {
                showError("Tanggal keluar tidak boleh sebelum tanggal adopsi!"); return;
            }

            kucing.setStatus("Diadopsi");
            dataAdopsi.add(new Adopsi(kucing, nama, hp, tglAdop.toString(), tglKel.toString()));
            observableAdopsi.setAll(dataAdopsi);
            observableKucing.setAll(dataKucing);
            clearAdopsiForm();
            refreshComboKucing();
            updateStats();
            showInfo("🐾 Adopsi berhasil dicatat!");
            showRiwayatSection();

        } catch (Exception e) {
            showError("Data adopsi tidak valid!");
        }
    }

    // ================= REFRESH / STATS =================

    private void refreshComboKucing() {
        cbKucingAdopsi.getItems().clear();
        for (Kucing k : dataKucing)
            if (k.getStatus().equalsIgnoreCase("Tersedia"))
                cbKucingAdopsi.getItems().add(k);
    }

    private void updateStats() {
        lblTotal.setText(String.valueOf(dataKucing.size()));
        lblTersedia.setText(String.valueOf(dataKucing.stream()
                .filter(k -> k.getStatus().equalsIgnoreCase("Tersedia")).count()));
        lblAdopsi.setText(String.valueOf(dataKucing.stream()
                .filter(k -> k.getStatus().equalsIgnoreCase("Diadopsi")).count()));
    }

    // ================= ACTION BUTTONS =================

    private void setupActionButtons() {
        colAksi.setCellFactory(param -> new TableCell<>() {
            private final Button btnEdit   = new Button("✏️ Edit");
            private final Button btnDelete = new Button("🗑 Hapus");
            {
                btnEdit.getStyleClass().add("edit-button");
                btnDelete.getStyleClass().add("delete-button");

                btnEdit.setOnAction(e -> {
                    Kucing k = getTableView().getItems().get(getIndex());
                    loadEditForm(k);
                });

                btnDelete.setOnAction(e -> {
                    Kucing k = getTableView().getItems().get(getIndex());
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setHeaderText(null);
                    a.setContentText("Hapus kucing \"" + k.getNama() + "\"?");
                    a.showAndWait().ifPresent(res -> {
                        if (res == ButtonType.OK) {
                            dataKucing.remove(k);
                            observableKucing.setAll(dataKucing);
                            refreshComboKucing();
                            updateStats();
                            showInfo("🗑 Data berhasil dihapus!");
                        }
                    });
                });
            }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : new HBox(10, btnEdit, btnDelete));
            }
        });
    }

    // ================= LOGOUT =================

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Session.currentUser = null;
            Session.isAdmin     = false;
            FXMLLoader loader   = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene         = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login - PawPatrol Shelter");
        } catch (Exception e) { e.printStackTrace(); }
    }

    // ================= CLEAR =================

    private void clearFormTambah() {
        txtNama.clear(); txtWarna.clear(); txtUsia.clear();
        cbJenis.setValue(null); cbRasLangka.setValue(null); cbVaksin.setValue(null);
        vboxRasLangka.setVisible(false); vboxRasLangka.setManaged(false);
    }

    private void clearAdopsiForm() {
        txtNamaAdopsi.clear(); txtHpAdopsi.clear();
        dpTanggalAdopsi.setValue(null); dpTanggalKeluar.setValue(null);
        cbKucingAdopsi.setValue(null);
    }

    // ================= ALERT =================

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(null); a.setContentText(msg); a.show();
    }

    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null); a.setContentText(msg); a.show();
    }
}