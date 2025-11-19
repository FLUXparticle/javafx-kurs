package com.example.javafx.todo;

import com.example.javafx.todo.model.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;

public class ToDoController {

    private final ToDoModel model;
    private final ToDoView view;

    public ToDoController() {
        this.model = new ToDoModel();
        this.view = new ToDoView();

        initTree();
        initButtons();
    }

    private void initTree() {
        // TODO Root-TreeItem aus dem Model aufbauen und im TreeView setzen.
        // TODO TreeView konfigurieren (Root ausblenden, Single-Selection, Editable = true).
        // TODO Eine TextFieldTreeCell mit StringConverter registrieren, damit Einträge editierbar werden.
    }

    private TreeItem<ToDoItem> buildTree(ToDoItem toDoItem) {
        // TODO Rekursiv TreeItems für das komplette ToDoItem-Modell erzeugen.
        return null;
    }

    private void initButtons() {
        // TODO Button-Handler für Unteraufgabe anlegen und Löschen registrieren.
        // TODO Buttons abhängig von der Selektion aktivieren/deaktivieren.
    }

    private void handleAdd(ActionEvent event) {
        // TODO Aus der Selektion den Parent ermitteln, im Model eine neue Aufgabe anlegen
        //      und den passenden TreeItem-Knoten ergänzen + editieren.
    }

    private void handleDelete(ActionEvent event) {
        // TODO Selektierten Knoten aus TreeView und Model entfernen und danach Parent auswählen.
    }

    public Parent getRoot() {
        return view;
    }

}
