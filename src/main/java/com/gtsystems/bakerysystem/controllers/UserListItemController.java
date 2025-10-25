package com.gtsystems.bakerysystem.controllers;

import com.gtsystems.bakerysystem.Main;
import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class UserListItemController {

    @FXML private Label clientNameLabel;
    @FXML private Label clientBalanceLabel;
    @FXML private Button editButton;
    @FXML private Button deleteButton;

    private String clientName;
    private Double clientBalance;
    private UserListController parentController;

    public void setData(String clientName, Double clientBalance, UserListController parentController) {
        this.clientName = clientName;
        this.clientBalance = clientBalance;
        this.parentController = parentController;

        clientNameLabel.setText(clientName);
        String formattedBalance = String.format(new java.util.Locale("pt", "BR"), "%.2f", clientBalance);
        clientBalanceLabel.setText("Saldo: R$ " + formattedBalance);
    }

    @FXML
    void editUser() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("edit-user.fxml"));
            Parent root = loader.load();

            EditUserController editController = loader.getController();
            editController.initData(this.clientName, this.clientBalance);

            Stage editStage = new Stage();
            editStage.setTitle("Editar Cliente");
            editStage.setScene(new Scene(root));
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(editButton.getScene().getWindow());
            editStage.setResizable(false);

            editStage.showAndWait();

            parentController.loadUserList();

        } catch (IOException e) {
            System.err.println("Falha ao abrir a tela de edição.");
            e.printStackTrace();
        }
    }

    @FXML
    void deleteUser() {
        // 1. Criar o Alerta
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Excluir " + clientName + "?");
        alert.setContentText("Esta ação não pode ser desfeita. O saldo de " + clientBalanceLabel.getText().split(" ")[1] + " será perdido.");

        // --- INÍCIO DA ESTILIZAÇÃO ---

        // 2. Pega o DialogPane do alerta
        DialogPane dialogPane = alert.getDialogPane();

        // 3. Adiciona sua folha de estilos principal (styles.css)
        dialogPane.getStylesheets().add(
                Main.class.getResource("styles.css").toExternalForm());

        // 4. Adiciona uma classe de estilo específica para este alerta (para o cabeçalho vermelho)
        dialogPane.getStyleClass().add("delete-alert");

        // 5. Pega os botões e aplica as classes de estilo que já criamos
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        Button cancelButton = (Button) dialogPane.lookupButton(ButtonType.CANCEL);

        okButton.getStyleClass().add("dialog-button-delete"); // Nova classe (vermelha)
        cancelButton.getStyleClass().add("dialog-button-secondary"); // Classe existente (cinza)

        // --- FIM DA ESTILIZAÇÃO ---


        // 6. Mostrar o alerta e esperar a resposta
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // (Você precisa criar este método na sua persistência)
                AccountsPersistanceHandler.removeUser(clientName);

                parentController.loadUserList();

            } catch (IOException e) {
                System.err.println("Falha ao excluir o usuário.");
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erro");
                errorAlert.setHeaderText("Não foi possível excluir o cliente.");
                errorAlert.show();
            }
        }
    }
}