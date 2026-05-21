package com.example.papbojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class DashboardController {

    @FXML
    private void showDataSection() {

        tableKucing.requestFocus();

    }

    @FXML
    private void showTambahSection() {

        txtNama.requestFocus();

    }

    @FXML
    private void showAdopsiSection() {

        cbKucingAdopsi.requestFocus();

    }

    // ================= TABLE KUCING =================

    @FXML
    private TableView<Kucing> tableKucing;

    @FXML
    private TableColumn<Kucing, Integer> colId;

    @FXML
    private TableColumn<Kucing, String> colNama;

    @FXML
    private TableColumn<Kucing, String> colRas;

    @FXML
    private TableColumn<Kucing, Integer> colUsia;

    @FXML
    private TableColumn<Kucing, String> colWarna;

    @FXML
    private TableColumn<Kucing, String> colStatus;

    @FXML
    private TableColumn<Kucing, Void> colAksi;

    // ================= STATISTIK =================

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblTersedia;

    @FXML
    private Label lblAdopsi;

    @FXML
    private Label lblUser;

    // ================= FORM KUCING =================

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtWarna;

    @FXML
    private TextField txtUsia;

    @FXML
    private ComboBox<String> cbJenis;

    @FXML
    private CheckBox checkVaksin;

    // ================= FORM ADOPSI =================

    @FXML
    private ComboBox<Kucing> cbKucingAdopsi;

    @FXML
    private TextField txtNamaAdopsi;

    @FXML
    private TextField txtHpAdopsi;

    @FXML
    private DatePicker dpTanggalAdopsi;

    @FXML
    private DatePicker dpTanggalKeluar;

    // ================= TABLE ADOPSI =================

    @FXML
    private TableView<Adopsi> tableAdopsi;

    @FXML
    private TableColumn<Adopsi, String> colNamaKucing;

    @FXML
    private TableColumn<Adopsi, String> colPengadopsi;

    @FXML
    private TableColumn<Adopsi, String> colTanggalAdopsi;

    @FXML
    private TableColumn<Adopsi, String> colTanggalKeluar;

    // ================= COLLECTION =================

    private static final ArrayList<Kucing> dataKucing =
            new ArrayList<>();

    private static final ArrayList<Adopsi> dataAdopsi =
            new ArrayList<>();

    private ObservableList<Kucing> observableKucing;

    private ObservableList<Adopsi> observableAdopsi;

    private int idCounter = 1;

    // ================= INITIALIZE =================

    @FXML
    public void initialize() {

        // ================= SESSION =================

        if (Session.currentUser != null) {

            lblUser.setText(
                    "Login sebagai: "
                            + Session.currentUser.getUsername()
            );
        }

        // ================= TABLE KUCING =================

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colNama.setCellValueFactory(
                new PropertyValueFactory<>("nama")
        );

        colRas.setCellValueFactory(
                new PropertyValueFactory<>("ras")
        );

        colUsia.setCellValueFactory(
                new PropertyValueFactory<>("usia")
        );

        colWarna.setCellValueFactory(
                new PropertyValueFactory<>("warna")
        );

        colStatus.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        // ================= TABLE ADOPSI =================

        colNamaKucing.setCellValueFactory(
                new PropertyValueFactory<>("namaKucing")
        );

        colPengadopsi.setCellValueFactory(
                new PropertyValueFactory<>("namaPengadopsi")
        );

        colTanggalAdopsi.setCellValueFactory(
                new PropertyValueFactory<>("tanggalAdopsi")
        );

        colTanggalKeluar.setCellValueFactory(
                new PropertyValueFactory<>("tanggalKeluar")
        );

        // ================= OBSERVABLE =================

        observableKucing =
                FXCollections.observableArrayList(
                        dataKucing
                );

        observableAdopsi =
                FXCollections.observableArrayList(
                        dataAdopsi
                );

        tableKucing.setItems(observableKucing);

        tableAdopsi.setItems(observableAdopsi);

        // ================= COMBO =================

        cbJenis.getItems().addAll(
                "Domestik",
                "Langka"
        );

        // ================= DATA AWAL =================

        if (dataKucing.isEmpty()) {

            dataKucing.add(
                    new KucingDomestik(
                            idCounter++,
                            "Oren",
                            "Domestik",
                            12,
                            "Tersedia",
                            "Orange",
                            true
                    )
            );

            dataKucing.add(
                    new KucingLangka(
                            idCounter++,
                            "Mochi",
                            "Persia",
                            24,
                            "Tersedia",
                            "Putih",
                            true
                    )
            );
        }

        observableKucing.setAll(dataKucing);

        setupActionButtons();

        refreshComboKucing();

        updateStats();
    }

    // ================= TAMBAH KUCING =================

    @FXML
    public void handleTambahKucing() {

        try {

            String nama = txtNama.getText().trim();

            String warna = txtWarna.getText().trim();

            int usia =
                    Integer.parseInt(
                            txtUsia.getText()
                    );

            String jenis =
                    cbJenis.getValue();

            boolean vaksin =
                    checkVaksin.isSelected();

            if (nama.isEmpty()
                    || warna.isEmpty()
                    || jenis == null) {

                showError(
                        "Semua field harus diisi!"
                );

                return;
            }

            Kucing kucingBaru;

            if (jenis.equals("Domestik")) {

                kucingBaru =
                        new KucingDomestik(
                                idCounter++,
                                nama,
                                "Domestik",
                                usia,
                                "Tersedia",
                                warna,
                                vaksin
                        );

            } else {

                kucingBaru =
                        new KucingLangka(
                                idCounter++,
                                nama,
                                "Persia",
                                usia,
                                "Tersedia",
                                warna,
                                vaksin
                        );
            }

            // ================= COLLECTION =================

            dataKucing.add(kucingBaru);

            observableKucing.setAll(dataKucing);

            refreshComboKucing();

            updateStats();

            clearForm();

            showInfo(
                    "✅ Kucing berhasil ditambahkan!"
            );

        } catch (Exception e) {

            showError(
                    "Input tidak valid!"
            );
        }
    }

    // ================= ADOPSI =================

    @FXML
    public void handleAdopsi() {

        try {

            Kucing kucing =
                    cbKucingAdopsi.getValue();

            String nama =
                    txtNamaAdopsi.getText();

            String hp =
                    txtHpAdopsi.getText();

            LocalDate tglAdopsi =
                    dpTanggalAdopsi.getValue();

            LocalDate tglKeluar =
                    dpTanggalKeluar.getValue();

            if (kucing == null) {

                showError(
                        "Pilih kucing terlebih dahulu!"
                );

                return;
            }

            if (tglKeluar.isBefore(tglAdopsi)) {

                showError(
                        "Tanggal keluar tidak valid!"
                );

                return;
            }

            Adopsi adopsiBaru =
                    new Adopsi(
                            kucing,
                            nama,
                            hp,
                            tglAdopsi.toString(),
                            tglKeluar.toString()
                    );

            // ================= COLLECTION =================

            dataAdopsi.add(adopsiBaru);

            observableAdopsi.setAll(dataAdopsi);

            kucing.setStatus("Diadopsi");

            observableKucing.setAll(dataKucing);

            refreshComboKucing();

            updateStats();

            clearAdopsiForm();

            showInfo(
                    "🐾 Adopsi berhasil dicatat!"
            );

        } catch (Exception e) {

            showError(
                    "Data adopsi tidak valid!"
            );
        }
    }

    // ================= REFRESH COMBO =================

    private void refreshComboKucing() {

        cbKucingAdopsi.getItems().clear();

        for (Kucing k : dataKucing) {

            if (k.getStatus()
                    .equalsIgnoreCase("Tersedia")) {

                cbKucingAdopsi.getItems().add(k);
            }
        }
    }

    // ================= STATS =================

    private void updateStats() {

        lblTotal.setText(
                String.valueOf(dataKucing.size())
        );

        long tersedia =
                dataKucing.stream()
                        .filter(k ->
                                k.getStatus()
                                        .equalsIgnoreCase("Tersedia"))
                        .count();

        long adopsi =
                dataKucing.stream()
                        .filter(k ->
                                k.getStatus()
                                        .equalsIgnoreCase("Diadopsi"))
                        .count();

        lblTersedia.setText(
                String.valueOf(tersedia)
        );

        lblAdopsi.setText(
                String.valueOf(adopsi)
        );
    }

    // ================= ACTION BUTTON =================

    private void setupActionButtons() {

        colAksi.setCellFactory(param ->
                new TableCell<>() {

                    private final Button btnEdit =
                            new Button("✏️ Edit");

                    private final Button btnDelete =
                            new Button("🗑 Hapus");

                    {

                        btnEdit.getStyleClass()
                                .add("edit-button");

                        btnDelete.getStyleClass()
                                .add("delete-button");

                        // EDIT

                        btnEdit.setOnAction(event -> {

                            Kucing kucing =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            showEditDialog(kucing);
                        });

                        // DELETE

                        btnDelete.setOnAction(event -> {

                            Kucing kucing =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            dataKucing.remove(kucing);

                            observableKucing.setAll(dataKucing);

                            refreshComboKucing();

                            updateStats();

                            showInfo(
                                    "🗑 Data berhasil dihapus!"
                            );
                        });
                    }

                    @Override
                    protected void updateItem(
                            Void item,
                            boolean empty
                    ) {

                        super.updateItem(item, empty);

                        if (empty) {

                            setGraphic(null);

                        } else {

                            HBox box =
                                    new HBox(
                                            10,
                                            btnEdit,
                                            btnDelete
                                    );

                            setGraphic(box);
                        }
                    }
                });
    }

    // ================= EDIT =================

    private void showEditDialog(Kucing kucing) {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle("Edit Kucing");

        TextField txtNama =
                new TextField(kucing.getNama());

        TextField txtUsia =
                new TextField(
                        String.valueOf(
                                kucing.getUsia()
                        )
                );

        TextField txtWarna =
                new TextField(kucing.getWarna());

        VBox content =
                new VBox(
                        15,

                        new Label("Nama"),
                        txtNama,

                        new Label("Usia"),
                        txtUsia,

                        new Label("Warna"),
                        txtWarna
                );

        content.setStyle(
                "-fx-padding:20;"
        );

        dialog.getDialogPane()
                .setContent(content);

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        ButtonType.OK,
                        ButtonType.CANCEL
                );

        dialog.showAndWait()
                .ifPresent(response -> {

                    if (response == ButtonType.OK) {

                        try {

                            kucing.setNama(
                                    txtNama.getText()
                            );

                            kucing.setUsia(
                                    Integer.parseInt(
                                            txtUsia.getText()
                                    )
                            );

                            kucing.setWarna(
                                    txtWarna.getText()
                            );

                            tableKucing.refresh();

                            showInfo(
                                    "💾 Data berhasil diupdate!"
                            );

                        } catch (Exception e) {

                            showError(
                                    "Input edit tidak valid!"
                            );
                        }
                    }
                });
    }

    // ================= LOGOUT =================
    @FXML
    private void handleLogout(ActionEvent event) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("login.fxml")
                    );

            Scene loginScene =
                    new Scene(loader.load());

            loginScene.getStylesheets().add(
                    getClass()
                            .getResource("style.css")
                            .toExternalForm()
            );

            Stage stage =
                    (Stage) ((Node) event.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(loginScene);

            stage.setTitle("Login - PawPatrol Shelter");

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }



    // ================= CLEAR =================

    private void clearForm() {

        txtNama.clear();

        txtWarna.clear();

        txtUsia.clear();

        cbJenis.setValue(null);

        checkVaksin.setSelected(false);
    }

    private void clearAdopsiForm() {

        txtNamaAdopsi.clear();

        txtHpAdopsi.clear();

        dpTanggalAdopsi.setValue(null);

        dpTanggalKeluar.setValue(null);

        cbKucingAdopsi.setValue(null);
    }

    // ================= ALERT =================

    private void showError(String msg) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);

        alert.setContentText(msg);

        alert.show();
    }

    private void showInfo(String msg) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);

        alert.setContentText(msg);

        alert.show();
    }
}
