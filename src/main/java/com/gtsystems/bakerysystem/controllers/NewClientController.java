package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class NewClientController {

    @FXML private TextField clientNameTextField;
    @FXML private Label clientNameExists;
    @FXML private TextField clientBillTextField;
    @FXML public Button createButton;
    @FXML public Button cancelButton;
    private Map<String, Double> data;
    private boolean clientCreated = false;

    @FXML
    public void initialize() throws IOException {
        this.data = AccountsPersistanceHandler.loadData();
        clientNameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateClientName();
            }
        });
        clientNameTextField.textProperty().addListener((obs, oldText, newText) -> {
            validateClientName();
        });
        createButton.setDisable(true);
    }

    public void initData(String clientName) {
        clientNameTextField.setText(clientName);
        validateClientName();
    }

    public boolean wasClientCreated() {
        return clientCreated;
    }

    private void validateClientName() {
        if (data == null) return;
        String clientName = clientNameTextField.getText().trim();

        if (clientName.isBlank()) {
            clientNameExists.setVisible(false);
            createButton.setDisable(true);
            return;
        }

        if (data.containsKey(clientName.toLowerCase())) {
            clientNameExists.setVisible(true);
            createButton.setDisable(true);
        } else {
            clientNameExists.setVisible(false);
            createButton.setDisable(false);
        }
    }

    @FXML
    public void cancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void createClient() {
        String clientName = clientNameTextField.getText().trim();
        String bill = clientBillTextField.getText().replace(",", ".");

        try {
            Double value = Double.parseDouble(bill);
            AccountsPersistanceHandler.addSale(clientName, value);
            this.clientCreated = true;
            closeWindow();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            clientBillTextField.setStyle("-fx-border-color: red;");
        }
    }
}