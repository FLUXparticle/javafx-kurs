package com.example.javafx.cocktails;

import com.example.javafx.cocktails.model.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class CocktailView {

    final ListView<Ingredient> listAlle = new ListView<>();
    final ListView<Ingredient> listKuehlschrank = new ListView<>();
    final ListView<RezeptOhneZutaten> listRezepte = new ListView<>();
    final ListView<AnweisungText> listAnweisungen = new ListView<>();

    final TextField txtSuche = new TextField();

    final Button btnAdd = new Button(">");
    final Button btnRemove = new Button("<");

    public void build(Stage stage) {
        txtSuche.setPromptText("Suche");
        listAlle.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox zutatenBox = new VBox(10, txtSuche, listAlle);
        VBox.setVgrow(listAlle, Priority.ALWAYS);

        VBox buttons = new VBox(10, btnAdd, btnRemove);
        buttons.setAlignment(Pos.CENTER);

        listKuehlschrank.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        VBox kuehlschrankBox = new VBox(listKuehlschrank);
        VBox.setVgrow(listKuehlschrank, Priority.ALWAYS);

        VBox rezeptBox = new VBox(listRezepte);
        VBox.setVgrow(listRezepte, Priority.ALWAYS);

        VBox anweisungenBox = new VBox(listAnweisungen);
        VBox.setVgrow(listAnweisungen, Priority.ALWAYS);

        HBox root = new HBox(10, zutatenBox, buttons, kuehlschrankBox, rezeptBox, anweisungenBox);
        root.setPadding(new Insets(10));
        root.setPrefSize(960, 600);

        stage.setScene(new Scene(root));
        stage.setTitle("Cocktails");
        stage.show();
    }

}
