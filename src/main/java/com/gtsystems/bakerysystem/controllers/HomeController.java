package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Map;

public class HomeController {
    @FXML public HBox searchContainer = new HBox();
    @FXML public VBox resultContainer = new VBox();
    @FXML public TextField searchField = new TextField();
    @FXML public Label resultLabel = new Label();
    @FXML public Button searchButton = new Button();
    @FXML public Button payButton = new Button();
    @FXML public Button addSaleButton = new Button();

    public HomeController() throws IOException {
        searchContainer.setLayoutX(146);
    }

    @FXML
    void toggleVisibility(VBox element) {
        element.setVisible(!element.isVisible());
    }

    void showResult() throws IOException {
        searchContainer.setLayoutY(146);
        toggleVisibility(resultContainer);
    }

    @FXML
    void searchUser() throws IOException {
        Map<String, Double> data = AccountsPersistanceHandler.loadData();

        String key = searchField.getText();
        showResult();

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
