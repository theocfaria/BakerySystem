package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewSaleController {
    @FXML TextField input;
    @FXML Button cancelButton;
    @FXML Button confirmButton;

    private boolean validateFields() {
        return true;
    }

    @FXML
    public void confirmValue() throws IOException {
        String textoDoInput = input.getText();

        if (textoDoInput == null || textoDoInput.isBlank()) {
            System.err.println("Erro: O campo de valor não pode estar vazio.");
            return;
        }

        Double value = Double.parseDouble(textoDoInput);
        AccountsPersistanceHandler.addSale(HomeController.client, HomeController.balance + value);
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
    /*
    @FXML
    public void cancelSale() throws IOException {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
    */

}
