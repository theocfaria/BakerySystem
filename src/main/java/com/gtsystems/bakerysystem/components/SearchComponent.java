package com.gtsystems.bakerysystem.components;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class SearchComponent {
    public HBox create(Stage stage) throws IOException {
        Map<String, Double> data = AccountsPersistanceHandler.loadData();

        TextField searchField = new TextField();
        Label searchTitle = new Label("ACESSAR CONTA");
        searchTitle.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        Label resultLabel = new Label();
        searchField.setOnAction(e -> {
            String key = searchField.getText();

            if (data.containsKey(key)) {
                resultLabel.setText("Total da conta: \n" + data.get(key));
            } else {
                resultLabel.setText("Chave não encontrada!");
            }
        });

        Region spacer = new Region();
        spacer.setPrefSize(
                stage.getHeight()/10,
                stage.getWidth()/10
        );

        VBox vbox = new VBox(
                searchTitle,
                spacer,
                searchField,
                resultLabel
        );

        vbox.setAlignment(Pos.CENTER);

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        leftSpacer.setPrefSize(
                stage.getHeight()/10,
                stage.getWidth()/10
        );

        rightSpacer.setPrefSize(
                stage.getHeight()/10,
                stage.getWidth()/10
        );

        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox hbox = new HBox(leftSpacer, vbox, rightSpacer);
        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }
}
