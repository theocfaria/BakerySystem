module com.gtsystems.bakerysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.gtsystems.bakerysystem to javafx.fxml;
    exports com.gtsystems.bakerysystem;
}