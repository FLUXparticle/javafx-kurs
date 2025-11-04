package com.example.javafx.cocktails;

import com.example.javafx.cocktails.model.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CocktailView extends HBox {

    final ListView<Ingredient> listAlle = new ListView<>();
    final ListView<Ingredient> listKuehlschrank = new ListView<>();
    final ListView<RezeptOhneZutaten> listRezepte = new ListView<>();
    final ListView<AnweisungText> listAnweisungen = new ListView<>();

    final TextField txtSuche = new TextField();

    final Button btnAdd = new Button(">");
    final Button btnRemove = new Button("<");

    public CocktailView() {
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setPrefSize(960, 600);

        txtSuche.setPromptText("Suche");
        listAlle.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox zutatenBox = new VBox(10, txtSuche, listAlle);
        VBox.setVgrow(listAlle, Priority.ALWAYS);
        this.getChildren().add(zutatenBox);

        VBox buttons = new VBox(10, btnAdd, btnRemove);
        buttons.setAlignment(Pos.CENTER);
        this.getChildren().add(buttons);

        listKuehlschrank.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        VBox kuehlschrankBox = new VBox(listKuehlschrank);
        VBox.setVgrow(listKuehlschrank, Priority.ALWAYS);
        this.getChildren().add(kuehlschrankBox);

        VBox rezeptBox = new VBox(listRezepte);
        VBox.setVgrow(listRezepte, Priority.ALWAYS);
        this.getChildren().add(rezeptBox);

        VBox anweisungenBox = new VBox(listAnweisungen);
        VBox.setVgrow(listAnweisungen, Priority.ALWAYS);
        this.getChildren().add(anweisungenBox);
    }

}
