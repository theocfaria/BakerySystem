package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewSaleController {
    @FXML private TextField input;
    @FXML private Button cancelButton;
    @FXML private Button confirmButton;

    private String currentClient;

    public void initData(String client) {
        this.currentClient = client;
    }

    @FXML
    public void confirmSale() throws IOException {
        String cleanText = input.getText().replace(",", ".");
        try {
            Double value = Double.parseDouble(cleanText);
            AccountsPersistanceHandler.addSale(currentClient, value);
            closeWindow();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format.");
            input.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    public void cancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}