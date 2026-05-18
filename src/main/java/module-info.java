module com.example.sistemmanajemenshelterkucingpawpatrol {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens com.example.sistemmanajemenshelterkucingpawpatrol to javafx.fxml;
    exports com.example.sistemmanajemenshelterkucingpawpatrol;
}