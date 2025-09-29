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
    public HBox create() throws IOException {
        Map<String, Double> data = AccountsPersistanceHandler.loadData();

        VBox contentBox = new VBox();
        contentBox.setPrefWidth(250);
        contentBox.setSpacing(10);
        contentBox.setPadding(new Insets(10));
        contentBox.setStyle(
                "-fx-background-color: #f4f4f4;" +
                        "-fx-border-color: #c0c0c0;" +
                        "-fx-border-width: 3px;" +
                        "-fx-border-radius: 5;"
        );

        TextField searchField = new TextField();
        searchField.setStyle("-fx-font-size: 22px; -fx-pref-height: 25px; -fx-font-weight: bold");

        Label searchTitle = new Label("ACESSAR CONTA");
        searchTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        contentBox.getChildren().addAll(searchTitle, searchField);

        HBox externalBox = new HBox();

        Label resultLabel = new Label();
        searchField.setOnAction(e -> {
            String key = searchField.getText();

            if (data.containsKey(key)) {
                resultLabel.setText("Total da conta: \n" + data.get(key));
            } else {
                resultLabel.setText("Chave não encontrada!");
            }
        });

        externalBox.getChildren().addAll(contentBox,resultLabel);

        externalBox.setAlignment(Pos.CENTER);
        externalBox.setSpacing(10);

        return externalBox;
    }
}
