package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class EditUserController {

    @FXML private TextField clientNameTextField;
    @FXML private TextField clientBillTextField;
    @FXML private Label clientNameExistsLabel; // Novo Label de erro
    @FXML private Button cancelButton;
    @FXML private Button saveButton;

    private String originalClientName; // Guarda o nome original

    public void initData(String clientName, Double balance) {
        this.originalClientName = clientName; // Salva o nome original

        clientNameTextField.setText(clientName);

        // Formata o saldo para exibição (usando ponto)
        String formattedBalance = String.format(new java.util.Locale("en", "US"), "%.2f", balance);
        clientBillTextField.setText(formattedBalance);
    }

    @FXML
    void saveChanges() {
        // Reseta o erro
        clientNameExistsLabel.setVisible(false);

        // 1. Pega os novos dados
        String newBalanceStr = clientBillTextField.getText().replace(",", ".");
        String newClientName = clientNameTextField.getText().trim();

        // 2. Validação do nome
        if (newClientName.isEmpty()) {
            showAlert("Erro", "Nome inválido", "O nome do cliente não pode estar vazio.");
            return;
        }

        try {
            // 3. Validação do saldo
            double newBalance = Double.parseDouble(newBalanceStr);
            Map<String, Double> data = AccountsPersistanceHandler.loadData();

            // 4. VERIFICAÇÃO DE DUPLICIDADE (A mais importante)
            // Se o nome mudou E o novo nome já existe...
            if (!originalClientName.equals(newClientName) && data.containsKey(newClientName)) {
                clientNameExistsLabel.setVisible(true); // Mostra o erro
                return; // Para a execução
            }

            // 5. Salvar os dados (usando o novo método de persistência)
            AccountsPersistanceHandler.updateUser(originalClientName, newClientName, newBalance);

            // 6. Fechar a janela
            closeWindow();

        } catch (NumberFormatException e) {
            showAlert("Erro de Formato", "Valor inválido.", "Por favor, insira um número válido (ex: 123.45).");
        } catch (IOException e) {
            System.err.println("Falha ao salvar alterações.");
            e.printStackTrace();
            showAlert("Erro de I/O", "Falha ao salvar", "Não foi possível salvar os dados no arquivo.");
        }
    }

    @FXML
    void cancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}