package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

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
            Map<String, Double> data = AccountsPersistanceHandler.loadData();
            Double value = Double.parseDouble(cleanText);

            if (currentClient == null || currentClient.trim().isEmpty()) {
                return;
            }

            Double currentValue = data.getOrDefault(currentClient, 0.0);

            Double newValue = currentValue + value;

            AccountsPersistanceHandler.addSale(currentClient, newValue);
            closeWindow();

        } catch (NumberFormatException e) {
            input.setStyle("-fx-border-color: red;");
        } catch (Exception e) {
            e.printStackTrace();
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