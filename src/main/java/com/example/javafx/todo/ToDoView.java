package com.example.javafx.todo;

import com.example.javafx.todo.model.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ToDoView extends BorderPane {

    public final TreeView<ToDoItem> treeView = new TreeView<>();
    public final Button btnAdd = new Button("Unteraufgabe");
    public final Button btnDelete = new Button("Löschen");

    public ToDoView() {
        setPadding(new Insets(15));

        Label headline = new Label("ToDo-Liste");
        BorderPane.setAlignment(headline, Pos.CENTER_LEFT);
        setTop(headline);

        treeView.setPrefWidth(450);
        treeView.setPrefHeight(320);
        setCenter(treeView);

        HBox buttons = new HBox(10, btnAdd, btnDelete);
        buttons.setAlignment(Pos.CENTER_LEFT);
        buttons.setPadding(new Insets(10, 0, 0, 0));
        setBottom(buttons);
    }

}
