package com.gtsystems.bakerysystem.controllers;

import com.google.gson.Gson; // Importar Gson
import com.google.gson.reflect.TypeToken; // Importar TypeToken
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class UserListController implements Initializable {

    @FXML
    private ListView<String> userList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Gson gson = new Gson();
        Type tipoDoMapa = new TypeToken<Map<String, Double>>() {}.getType();

        ObservableList<String> itensFormatados = FXCollections.observableArrayList();

        try (Reader leitor = new FileReader("data/accounts.json")) {

            Map<String, Double> userMap = gson.fromJson(leitor, tipoDoMapa);

            for (Map.Entry<String, Double> entry : userMap.entrySet()) {
                String chave = entry.getKey();
                Double valor = entry.getValue();

                String itemFormatado = String.format("%s: R$ %.2f", chave, valor);

                itensFormatados.add(itemFormatado);
            }

            userList.setItems(itensFormatados);
        } catch (Exception e) {
            e.printStackTrace();
            userList.getItems().add("Erro ao carregar dados do JSON.");
        }
    }
}