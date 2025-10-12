module com.gtsystems.bakerysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;
    requires javafx.graphics;

    opens com.gtsystems.bakerysystem to javafx.graphics, javafx.fxml;
    opens com.gtsystems.bakerysystem.screens to javafx.graphics, javafx.fxml;
    opens com.gtsystems.bakerysystem.controllers to javafx.fxml;
    exports com.gtsystems.bakerysystem;
}