package com.gtsystems.bakerysystem;

import com.gtsystems.bakerysystem.screens.HomeScreen;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.show(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}

