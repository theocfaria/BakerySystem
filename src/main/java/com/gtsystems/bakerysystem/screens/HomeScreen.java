package com.gtsystems.bakerysystem.screens;

import com.gtsystems.bakerysystem.components.SearchComponent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreen {
    public void show(Stage stage) throws IOException {
        BorderPane root = new BorderPane();

        Label title = new Label("SISTEMA DE CONTAS GT");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
        root.setTop(title);
        BorderPane.setAlignment(title, javafx.geometry.Pos.CENTER);

        SearchComponent searchComponent = new SearchComponent();
        root.setLeft(searchComponent.create(stage));

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Tela principal");
        stage.setScene(scene);
        stage.show();
    }
}
