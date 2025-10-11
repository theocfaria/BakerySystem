package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.Main;
import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class ApplicationController {
    @FXML public HBox searchContainer = new HBox();
    @FXML public VBox resultContainer = new VBox();
    @FXML public TextField searchField = new TextField();
    @FXML public Label resultLabel = new Label();
    @FXML public Button searchButton = new Button();
    @FXML public Button payButton = new Button();
    @FXML public Button addSaleButton = new Button();
    @FXML public TextField input;
    @FXML Button cancelButton;
    @FXML Button confirmButton;
    public static String client;

    public ApplicationController() throws IOException {
        searchContainer.setLayoutX(146);
    }

    void showResult() throws IOException {
        searchContainer.setLayoutY(146);
        resultContainer.setVisible(true);
    }

    void updateResult(String key) throws IOException {
        Map<String, Double> data = AccountsPersistanceHandler.loadData();
        String balance = data.get(key).toString();
        resultLabel.setText(
                "Nome: " + key + "\n" +
                        "Total: R$" + balance.replace(".", ",")
        );
    }

    @FXML
    void searchUser() throws IOException {
        Map<String, Double> data = AccountsPersistanceHandler.loadData();

        String key = searchField.getText();

        if (data.containsKey(key)) {
            showResult();
            updateResult(key);
        }
        // else {
        //  colocar lógica para chamar a função para criar cliente novo
        // }
    }

    @FXML
    void payBill() throws IOException {
        String key = searchField.getText();
        AccountsPersistanceHandler.addSale(key, 0.0);
        updateResult(key);
    }

    @FXML
    void openNewSalePanel() throws IOException {
        client = searchField.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-sale.fxml"));
        Parent root = fxmlLoader.load();

        Stage newWindow = new Stage();
        newWindow.setTitle("Adicionar Nova Compra");
        newWindow.setScene(new Scene(root));

        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.showAndWait();
        updateResult(client);
    }

    @FXML
    public void confirmValue() throws IOException {
        String currentClient = ApplicationController.client;
        Map<String, Double> data = AccountsPersistanceHandler.loadData();
        String inputText = input.getText();
        if(inputText.contains(",")) inputText.replace(",", ".");

        if (inputText.isBlank()) {
            System.err.println("Erro: O campo de valor não pode estar vazio.");
            return;
        }

        Double currentBalance = data.get(currentClient);

        Double value = Double.parseDouble(inputText);
        AccountsPersistanceHandler.addSale(currentClient, currentBalance + value);

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
