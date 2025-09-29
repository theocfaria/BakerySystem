package com.gtsystems.bakerysystem.screens;

import com.gtsystems.bakerysystem.components.SearchComponent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreen {
    public void show(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #E8E8E8;");

        Label title = new Label("SISTEMA DE CONTAS GT");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
        BorderPane.setAlignment(title, javafx.geometry.Pos.CENTER);
        BorderPane.setMargin(title, new Insets(10, 0, 20, 0));
        root.setTop(title);

        SearchComponent searchComponent = new SearchComponent();

        root.setLeft(searchComponent.create());
        BorderPane.setMargin(searchComponent.create(), new Insets(0, 10, 10, 10));
        root.setCenter(null);

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Tela principal");
        stage.setScene(scene);
        stage.show();
    }
}
