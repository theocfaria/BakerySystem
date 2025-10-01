package com.gtsystems.bakerysystem.screens;

import com.gtsystems.bakerysystem.components.HomeTitle;
import com.gtsystems.bakerysystem.components.SearchComponent;
import javafx.geometry.Insets;
import javafx.scene.Node;
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

        HomeTitle title = new HomeTitle();
        root.setTop(title);

        SearchComponent searchComponent = new SearchComponent();
        root.setCenter(searchComponent.create());
        
        BorderPane.setMargin(searchComponent.create(), new Insets(0, 10, 10, 10));

        Scene scene = new Scene(root, 1100, 800);
        stage.setTitle("Tela principal");
        stage.setScene(scene);
        stage.show();
    }
}
