package com.gtsystems.bakerysystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TestController {
    @FXML
    private Button button;

    @FXML
    void onButtonAction () {
        System.out.println("TestController onButtonAction");
    }
}
