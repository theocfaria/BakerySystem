package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Map;

public class HomeController {
    @FXML public TextField searchField = new TextField();
    @FXML public Label resultLabel = new Label();
    @FXML public Button searchButton = new Button();
    @FXML public Button payButton = new Button();
    @FXML public Button addSaleButton = new Button();

    public HomeController() throws IOException {}

    @FXML
    void toggleVisibility(Control element) {
        element.setVisible(!element.isVisible());
    }

    @FXML
    void searchUser() throws IOException {
        Map<String, Double> data = AccountsPersistanceHandler.loadData();

        String key = searchField.getText();

        if (data.containsKey(key)) {
            resultLabel.setText(
                    "Nome: " + key + "\n" +
                    "Total: R$" + data.get(key)
                    );
        }
        // else {
        //  colocar lógica para chamar a função para criar cliente novo
        // }
    }

    @FXML
    void payBill() throws IOException {
        String key = searchField.getText();
        AccountsPersistanceHandler.addSale(key, 0.0);
        Map<String, Double> data = AccountsPersistanceHandler.loadData();
        resultLabel.setText(
                "Nome: " + key + "\n" +
                        "Total: R$ " + data.get(key)
        );
    }
}
