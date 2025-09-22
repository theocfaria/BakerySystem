package com.gtsystems.bakerysystem.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class SearchBarComponent {
    public HBox create() {
        TextField searchField = new TextField();
        searchField.setPromptText("Pesquisar");

        Button searchButton = new Button("Buscar");

        VBox vbox = new VBox(10, searchField, searchButton);
        vbox.setAlignment(Pos.CENTER);

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox wrapper = new HBox(leftSpacer, vbox, rightSpacer);
        wrapper.setPrefWidth(150);

        return wrapper;
    }
}
