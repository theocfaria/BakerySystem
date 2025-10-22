package com.gtsystems.bakerysystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Map;

public class ClientDataController {
    @FXML private Label clientNameLabel;
    @FXML private Label clientBalanceLabel;

    public void setData(Map.Entry<String, Double> cliente) {
        clientNameLabel.setText(cliente.getKey());
        clientBalanceLabel.setText("R$ " + cliente.getValue());
    }
}
