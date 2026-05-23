package com.example.papbojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboardController {

    // ===================== HEADER =====================
    @FXML private Label lblAdminName;

    // ===================== TABLE =====================
    @FXML private TableView<User>            tableStaff;
    @FXML private TableColumn<User, String>  colNama;
    @FXML private TableColumn<User, String>  colUsername;
    @FXML private TableColumn<User, String>  colPassword;
    @FXML private TableColumn<User, Void>    colAksi;

    // ===================== FORM =====================
    @FXML private TextField txtNama;
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label     lblFormTitle;
    @FXML private Button    btnSimpan;

    // ===================== STATS =====================
    @FXML private Label lblTotalStaff;

    // ===================== STATE =====================
    private ObservableList<User> observableStaff;
    private User editTarget = null; // null = mode tambah, non-null = mode edit

    // ===================== INIT =====================

    @FXML
    public void initialize() {
        if (Session.currentUser != null) {
            lblAdminName.setText("👑 " + Session.currentUser.getNama());
        }

        // Setup kolom
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        // Isi observable dari LoginController.users (exclude admin)
        observableStaff = FXCollections.observableArrayList();
        refreshTable();

        tableStaff.setItems(observableStaff);
        setupActionButtons();
        updateStats();
    }

    // Ambil data staff (semua kecuali admin)
    private void refreshTable() {
        observableStaff.clear();
        for (User u : LoginController.users) {
            if (!u.getUsername().equals("admin")) {
                observableStaff.add(u);
            }
        }
        updateStats();
    }

    private void updateStats() {
        lblTotalStaff.setText(String.valueOf(observableStaff.size()));
    }

    // ===================== TAMBAH / EDIT =====================

    @FXML
    public void handleSimpan() {
        String nama     = txtNama.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        // Validasi kosong
        if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showError("❌ Semua field harus diisi!");
            return;
        }

        if (editTarget == null) {
            // MODE TAMBAH — cek duplikat username
            for (User u : LoginController.users) {
                if (u.getUsername().equalsIgnoreCase(username)) {
                    showError("❌ Username sudah dipakai!");
                    return;
                }
            }
            User baru = new User(nama, username, password);
            LoginController.users.add(baru);
            showInfo("✅ Staff berhasil ditambahkan!");
        } else {
            // MODE EDIT — cek duplikat username (boleh sama dengan diri sendiri)
            for (User u : LoginController.users) {
                if (!u.equals(editTarget) && u.getUsername().equalsIgnoreCase(username)) {
                    showError("❌ Username sudah dipakai!");
                    return;
                }
            }
            editTarget.setNama(nama);
            editTarget.setUsername(username);
            editTarget.setPassword(password);
            editTarget = null;
            showInfo("💾 Data staff berhasil diperbarui!");
        }

        clearForm();
        refreshTable();
    }

    @FXML
    public void handleBatalEdit() {
        editTarget = null;
        clearForm();
        lblFormTitle.setText("➕ Tambah Staff");
        btnSimpan.setText("✅ Simpan Staff");
    }

    // ===================== ACTION BUTTONS =====================

    private void setupActionButtons() {
        colAksi.setCellFactory(param -> new TableCell<>() {

            private final Button btnEdit   = new Button("✏️ Edit");
            private final Button btnDelete = new Button("🗑 Hapus");

            {
                btnEdit.getStyleClass().add("edit-button");
                btnDelete.getStyleClass().add("delete-button");

                btnEdit.setOnAction(e -> {
                    User staff = getTableView().getItems().get(getIndex());
                    loadForEdit(staff);
                });

                btnDelete.setOnAction(e -> {
                    User staff = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                    confirm.setHeaderText(null);
                    confirm.setContentText("Hapus staff \"" + staff.getNama() + "\"?");
                    confirm.showAndWait().ifPresent(res -> {
                        if (res == ButtonType.OK) {
                            LoginController.users.remove(staff);
                            if (editTarget == staff) { editTarget = null; clearForm(); }
                            refreshTable();
                            showInfo("🗑 Staff berhasil dihapus!");
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(10, btnEdit, btnDelete);
                    setGraphic(box);
                }
            }
        });
    }

    private void loadForEdit(User staff) {
        editTarget = staff;
        txtNama.setText(staff.getNama());
        txtUsername.setText(staff.getUsername());
        txtPassword.setText(staff.getPassword());
        lblFormTitle.setText("✏️ Edit Staff: " + staff.getNama());
        btnSimpan.setText("💾 Update Staff");
    }

    // ===================== LOGOUT =====================

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===================== HELPERS =====================

    private void clearForm() {
        txtNama.clear();
        txtUsername.clear();
        txtPassword.clear();
        lblFormTitle.setText("➕ Tambah Staff");
        btnSimpan.setText("✅ Simpan Staff");
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }

    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }
}