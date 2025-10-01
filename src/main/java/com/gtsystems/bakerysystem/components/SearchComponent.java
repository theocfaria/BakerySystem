package com.gtsystems.bakerysystem.components;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Map;

public class SearchComponent {
    public VBox create() throws IOException {
        Map<String, Double> data = AccountsPersistanceHandler.loadData();

        VBox contentBox = new VBox();
        contentBox.setSpacing(10);
        contentBox.setPadding(new Insets(10));

        contentBox.setStyle(
                "-fx-background-color: #f4f4f4;" +
                        "-fx-border-color: #c0c0c0;" +
                        "-fx-border-width: 3px;" +
                        "-fx-border-radius: 5;"
        );

        contentBox.setAlignment(Pos.CENTER);

        TextField searchField = new TextField();
        searchField.setPrefColumnCount(15);
        searchField.setStyle("-fx-font-size: 22px; -fx-pref-height: 25px; -fx-font-weight: bold");

        Label searchTitle = new Label("ACESSAR CONTA");
        searchTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
        searchField.setOnAction(e -> {
            String key = searchField.getText();

            if (data.containsKey(key)) {
                resultLabel.setText("Total: R$" + data.get(key));
            } else {
                resultLabel.setText("Chave não encontrada!");
            }
        });

        contentBox.getChildren().addAll(searchTitle, searchField, resultLabel);

        HBox externalBox = new HBox();
        externalBox.setAlignment(Pos.CENTER);
        externalBox.getChildren().addAll(contentBox);

        VBox lastBox = new VBox();
        lastBox.setAlignment(Pos.CENTER);
        lastBox.getChildren().addAll(externalBox);
        return lastBox;
    }
}
