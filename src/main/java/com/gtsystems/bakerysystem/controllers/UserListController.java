package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.Main;
import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class UserListController {

    @FXML
    private VBox userListContainer;

    @FXML
    public void initialize() {
        loadUserList();
    }

    public void loadUserList() {
        userListContainer.getChildren().clear();

        try {
            Map<String, Double> data = AccountsPersistanceHandler.loadData();

            data.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(Main.class.getResource("user-list-item.fxml"));
                            HBox userItemNode = loader.load();

                            UserListItemController itemController = loader.getController();
                            itemController.setData(entry.getKey(), entry.getValue(), this);

                            userListContainer.getChildren().add(userItemNode);
                        } catch (IOException e) {
                            System.err.println("Falha ao carregar item da lista de usuário: " + entry.getKey());
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            System.err.println("Falha ao carregar dados dos clientes.");
            e.printStackTrace();
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        try {
            // 1. Carrega o FXML da tela principal
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("home.fxml")); // Certifique-se que este é o nome do FXML principal
            Parent root = loader.load();

            // 2. Pega o Stage (Janela) atual a partir do botão que foi clicado
            Node source = (Node) event.getSource();
            Stage stagePrincipal = (Stage) source.getScene().getWindow();

            // 3. Pega as dimensões atuais da janela (Exatamente como você fez no MainController)
            double larguraAtual = stagePrincipal.getScene().getWidth();
            double alturaAtual = stagePrincipal.getScene().getHeight();

            // 4. Cria uma NOVA cena com o FXML principal e as dimensões antigas
            Scene novaCena = new Scene(root, larguraAtual, alturaAtual);

            // 5. Define a nova cena no Stage
            stagePrincipal.setScene(novaCena);

        } catch (IOException e) {
            System.err.println("Falha ao voltar para a tela principal.");
            e.printStackTrace();
        }
    }
}