package com.gtsystems.bakerysystem.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class HomeTitle extends StackPane {
    public HomeTitle() {
        Label title = new Label("SISTEMA DE CONTAS GT");
        title.setStyle(
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Times New Roman';" +
                "-fx-underline: true;"
        );

        this.getChildren().add(title);

        StackPane.setMargin(this, new Insets(10, 0, 20, 0));
    }
}
