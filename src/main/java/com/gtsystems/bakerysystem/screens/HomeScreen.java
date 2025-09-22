package com.gtsystems.bakerysystem.screens;

import com.gtsystems.bakerysystem.components.SearchBarComponent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomeScreen {
    public void show(Stage stage) {
        BorderPane root = new BorderPane();

        Label title = new Label("SISTEMA DE CONTAS GT");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
        root.setTop(title);
        BorderPane.setAlignment(title, javafx.geometry.Pos.CENTER);

        SearchBarComponent searchBarComponent = new SearchBarComponent();
        root.setLeft(searchBarComponent.create());

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Tela principal");
        stage.setScene(scene);
        stage.show();
    }
}
