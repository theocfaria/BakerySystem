module com.gtsystems.bakerysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.gtsystems.bakerysystem to javafx.graphics, javafx.fxml;
    opens com.gtsystems.bakerysystem.screens to javafx.graphics, javafx.fxml;
    exports com.gtsystems.bakerysystem;
}