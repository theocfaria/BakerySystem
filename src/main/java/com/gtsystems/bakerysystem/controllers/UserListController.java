package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserListController {

    @FXML
    private VBox clientsContainer;

    @FXML
    public void initialize() throws IOException {
        Map<String, Double> data = AccountsPersistanceHandler.loadData();
        for (Map.Entry<String, Double> cliente : data.entrySet()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("client-data.fxml"));
                HBox clientItemNode = loader.load();

                ClientDataController itemController = loader.getController();

                itemController.setData(cliente);

                clientsContainer.getChildren().add(clientItemNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}