package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class NewSaleController {
    @FXML private TextField input;
    @FXML private Button cancelButton;
    @FXML private Button confirmButton;

    private String currentClient;

    public void initData(String client) {
        this.currentClient = client;
    }

    @FXML
    public void confirmSale() throws IOException {
        // ----- DEBUG -----
        // Vamos verificar se o nome do cliente realmente chegou aqui
        System.out.println("[DEBUG] Tentando adicionar venda para: " + currentClient);
        // ----- FIM DEBUG -----

        String cleanText = input.getText().replace(",", ".");

        // Use um bloco catch mais amplo para pegar o NullPointerException
        try {
            Map<String, Double> data = AccountsPersistanceHandler.loadData();
            Double value = Double.parseDouble(cleanText);

            if (currentClient == null || currentClient.trim().isEmpty()) {
                System.err.println("Erro: O nome do cliente está nulo ou vazio.");
                // Você pode querer mostrar um Alert aqui
                return;
            }

            // --- ESTA É A MUDANÇA PRINCIPAL ---
            // Se o cliente não existir no mapa, getOrDefault retorna 0.0
            Double currentValue = data.getOrDefault(currentClient, 0.0);

            Double newValue = currentValue + value;
            // ------------------------------------

            AccountsPersistanceHandler.addSale(currentClient, newValue);

            System.out.println("[DEBUG] Venda adicionada com sucesso. Novo saldo: " + newValue);

            closeWindow();

        } catch (NumberFormatException e) {
            System.err.println("Erro: Formato de número inválido.");
            input.setStyle("-fx-border-color: red;");
        } catch (Exception e) {
            // Captura qualquer outro erro (como o NullPointerException ou erros de I/O)
            System.err.println("Erro inesperado ao confirmar a venda:");
            e.printStackTrace();
        }
    }

    @FXML
    public void cancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}