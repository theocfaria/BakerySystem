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

public class MainController {
    @FXML private HBox searchContainer;
    @FXML private VBox resultContainer;
    @FXML private TextField searchField;
    @FXML private Label resultLabel;
    @FXML private HBox clientNotFoundContainer;
    @FXML private Label clientNotFoundLabel;
    @FXML private Button createClientButton;

    private Map<String, Double> data;

    @FXML
    public void initialize() throws IOException {
        data = AccountsPersistanceHandler.loadData();
    }

    private void showResult() {
        searchContainer.setLayoutY(146);
        resultContainer.setVisible(true);
    }

    public void updateResult(String key) throws IOException {
        clientNotFoundContainer.setVisible(false);
        data = AccountsPersistanceHandler.loadData();

        Double balance = data.getOrDefault(key, 0.0);
        String formattedBalance = String.format(new java.util.Locale("pt", "BR"), "%.2f", balance);

        resultLabel.setText(
                "Nome: " + key + "\n" +
                        "Total: R$ " + formattedBalance
        );
    }

    @FXML
    void searchUser() throws IOException {
        String key = searchField.getText();
        if (data.containsKey(key)) {
            showResult();
            updateResult(key);
            searchField.clear();
        } else {
            resultContainer.setVisible(false);
            searchContainer.setLayoutY(206);
            clientNotFoundContainer.setVisible(true);
        }
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

    @FXML
    void openNewClientPanel() throws IOException {
        String clientNameFromSearch = searchField.getText();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-client.fxml"));
        Parent root = fxmlLoader.load();

        NewClientController newClientController = fxmlLoader.getController();
        newClientController.initData(clientNameFromSearch);

        Stage newWindow = new Stage();
        newWindow.setTitle("Adicionar Novo Cliente");
        newWindow.setScene(new Scene(root));
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.showAndWait();

        if (newClientController.wasClientCreated()) {
            this.data = AccountsPersistanceHandler.loadData();
            searchField.setText(clientNameFromSearch);
            searchUser();
        }
    }
}