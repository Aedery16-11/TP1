module com.example.tp1entrainement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tp1entrainement to javafx.fxml;
    exports com.example.tp1entrainement;
}