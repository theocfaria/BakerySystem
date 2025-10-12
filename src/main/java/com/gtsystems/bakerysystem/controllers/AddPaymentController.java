package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPaymentController {
    @FXML private TextField input;
    @FXML private Button cancelButton;
    @FXML private Button confirmButton;

    private String currentClient;
    private Double currentBalance;

    public void initData(String client, Double balance) {
        this.currentClient = client;
        this.currentBalance = balance;
    }

    @FXML
    public void confirmPayment() throws IOException {
        String cleanText = input.getText().replace(",", ".");
        try {
            Double value = Double.parseDouble(cleanText);
            if (value > 0 && value <= currentBalance) {
                AccountsPersistanceHandler.addSale(currentClient, currentBalance - value);
                closeWindow();
            } else {
                input.setStyle("-fx-text-fill: red; -fx-border-color: red;");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format.");
            input.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    public void setAll() {
        String formattedBalance = String.format(new java.util.Locale("pt", "BR"), "%.2f", currentBalance);
        input.setText(formattedBalance);
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