package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.Main;
import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class MainController {
    @FXML private HBox searchContainer;
    @FXML private VBox resultContainer;
    @FXML private TextField searchField;
    @FXML private Label resultLabel;

    private Map<String, Double> data;

    @FXML
    public void initialize() throws IOException {
        data = AccountsPersistanceHandler.loadData();
    }

    private void showResult() {
        searchContainer.setLayoutY(146);
        resultContainer.setVisible(true);
    }

    public void updateResult(String key) {
        try {
            data = AccountsPersistanceHandler.loadData();
        } catch (IOException e) {
            System.err.println("Failed to reload data: " + e.getMessage());
        }

        Double balance = data.getOrDefault(key, 0.0);
        String formattedBalance = String.format(new java.util.Locale("pt", "BR"), "%.2f", balance);

        resultLabel.setText(
                "Nome: " + key + "\n" +
                        "Total: R$ " + formattedBalance
        );
    }

    @FXML
    void searchUser() {
        String key = searchField.getText();
        if (data.containsKey(key)) {
            showResult();
            updateResult(key);
        }
        // else { /* adicionar lógica de criar cliente */ }
    }

    @FXML
    void openNewSalePanel() throws IOException {
        String client = searchField.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-sale.fxml"));
        Parent root = fxmlLoader.load();

        NewSaleController newSaleController = fxmlLoader.getController();
        newSaleController.initData(client);

        Stage newWindow = new Stage();
        newWindow.setTitle("Adicionar Nova Compra");
        newWindow.setScene(new Scene(root));
        newWindow.initModality(Modality.WINDOW_MODAL);

        newWindow.showAndWait();

        updateResult(client);
    }

    @FXML
    void openNewPaymentPanel() throws IOException {
        String client = searchField.getText();
        Double balance = data.getOrDefault(client, 0.0);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add-payment.fxml"));
        Parent root = fxmlLoader.load();

        AddPaymentController addPaymentController = fxmlLoader.getController();
        addPaymentController.initData(client, balance);

        Stage newWindow = new Stage();
        newWindow.setTitle("Adicionar Novo Pagamento");
        newWindow.setScene(new Scene(root));
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.showAndWait();

        updateResult(client);
    }
}