package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.Main;
import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    @FXML private Button userListButton;

    private Map<String, Double> data;
    private String currentClient;

    @FXML
    public void initialize() throws IOException {
        data = AccountsPersistanceHandler.loadData();
        resultContainer.setVisible(false);
        resultContainer.setManaged(false); // Não ocupa espaço
        clientNotFoundContainer.setVisible(false);
        clientNotFoundContainer.setManaged(false); // Não ocupa espaço
    }

    private void showResult() {
        searchContainer.setLayoutY(146);
        resultContainer.setVisible(true);
    }

    public void updateResult(String key) throws IOException {
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

        resultContainer.setVisible(false);
        resultContainer.setManaged(false);
        clientNotFoundContainer.setVisible(false);
        clientNotFoundContainer.setManaged(false);

        if (data.containsKey(key)) {
            this.currentClient = key;
            resultContainer.setVisible(true);
            resultContainer.setManaged(true);
            updateResult(key);
            searchField.clear();
        } else {
            this.currentClient = null;
            clientNotFoundContainer.setVisible(true);
            clientNotFoundContainer.setManaged(true);
        }
    }

    @FXML
    void openNewSalePanel() throws IOException {
        String client = this.currentClient;

        if (client == null || client.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Lançar Venda");
            alert.setHeaderText("Nenhum cliente selecionado");
            alert.setContentText("Por favor, pesquise por um cliente válido antes de adicionar uma venda.");
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-sale.fxml"));
        Parent root = fxmlLoader.load();

        NewSaleController newSaleController = fxmlLoader.getController();
        newSaleController.initData(client);

        Stage newWindow = new Stage();
        newWindow.setTitle("Adicionar Compra para: " + client);
        newWindow.setScene(new Scene(root));
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.setResizable(false);
        newWindow.showAndWait();
        updateResult(client);
    }

    @FXML
    void openNewPaymentPanel() throws IOException {
        String client = this.currentClient; // Usa o cliente "lembrado"

        if (client == null || client.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Lançar Pagamento");
            alert.setHeaderText("Nenhum cliente selecionado");
            alert.setContentText("Por favor, pesquise por um cliente válido antes de adicionar um pagamento.");
            alert.showAndWait();
            return;
        }

        Double balance = data.getOrDefault(client, 0.0);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add-payment.fxml"));
        Parent root = fxmlLoader.load();

        AddPaymentController addPaymentController = fxmlLoader.getController();
        addPaymentController.initData(client, balance);

        Stage newWindow = new Stage();
        newWindow.setTitle("Adicionar Pagamento de: " + client);
        newWindow.setScene(new Scene(root));
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.setResizable(false);
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
        newWindow.setResizable(false);
        newWindow.showAndWait();

        if (newClientController.wasClientCreated()) {
            this.data = AccountsPersistanceHandler.loadData();
            searchField.setText(clientNameFromSearch);
            searchUser();
        }
    }
    @FXML
    private void openUserListPanel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("user-list.fxml"));
            Parent root = loader.load();

            Node source = (Node) event.getSource();
            Stage stagePrincipal = (Stage) source.getScene().getWindow();

            double larguraAtual = stagePrincipal.getScene().getWidth();
            double alturaAtual = stagePrincipal.getScene().getHeight();

            Scene novaCena = new Scene(root, larguraAtual, alturaAtual);

            stagePrincipal.setScene(novaCena);
        } catch (IOException e) {
            System.err.println("Falha ao carregar a tela de lista.");
            e.printStackTrace();
        }
    }
}