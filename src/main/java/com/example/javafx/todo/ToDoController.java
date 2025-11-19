package com.example.javafx.todo;

import com.example.javafx.todo.model.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.util.*;

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
        ToDoItem rootItem = model.getRootItem();
        TreeItem<ToDoItem> treeItem = buildTree(rootItem);
        view.treeView.setRoot(treeItem);

        view.treeView.setShowRoot(false);
        view.treeView.setEditable(true);
        view.treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        view.treeView.setCellFactory(tv -> {
            StringConverter<ToDoItem> stringConverter = new StringConverter<>() {
                @Override
                public String toString(ToDoItem obj) {
                    return obj == null ? "" : obj.getName();
                }

                @Override
                public ToDoItem fromString(String name) {
                    return new ToDoItem(name);
                }
            };

            return new TextFieldTreeCell<>(stringConverter) {
                @Override
                public void commitEdit(ToDoItem newItem) {
                    ToDoItem oldItem = getItem();
                    if (oldItem != null && newItem != null) {
                        oldItem.setName(newItem.getName());
                    }
                    super.commitEdit(oldItem);
                }
            };
        });
    }

    private TreeItem<ToDoItem> buildTree(ToDoItem toDoItem) {
        TreeItem<ToDoItem> treeItem = new TreeItem<>(toDoItem);

        for (ToDoItem child : toDoItem.getChildren()) {
            TreeItem<ToDoItem> childTree = buildTree(child);
            treeItem.getChildren().add(childTree);
        }

        return treeItem;
    }

    private void initButtons() {
        view.btnAdd.setOnAction(this::handleAdd);
        view.btnDelete.setOnAction(this::handleDelete);

        view.treeView.getSelectionModel().selectedItemProperty().addListener((o, oldItem, newItem) -> {
            boolean hasSelection = newItem != null;
            view.btnAdd.setDisable(!hasSelection);
            view.btnDelete.setDisable(!hasSelection);
        });

        view.btnAdd.setDisable(true);
        view.btnDelete.setDisable(true);
    }

    private void handleAdd(ActionEvent event) {
        TreeItem<ToDoItem> selected = view.treeView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ToDoItem parentValue = selected.getValue();
            ToDoItem newValue = model.addNewTask(parentValue);

            TreeItem<ToDoItem> newItem = buildTree(newValue);
            selected.getChildren().add(newItem);

            view.treeView.getSelectionModel().select(newItem);
            view.treeView.edit(newItem);
        }
    }

    private void handleDelete(ActionEvent event) {
        TreeItem<ToDoItem> selectedItem = view.treeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            ToDoItem selectedValue = selectedItem.getValue();

            TreeItem<ToDoItem> parentItem = selectedItem.getParent();
            ToDoItem parentValue = parentItem.getValue();

            model.removeChildTask(parentValue, selectedValue);
            parentItem.getChildren().remove(selectedItem);

            view.treeView.getSelectionModel().select(parentItem);
        }
    }

    public Parent getRoot() {
        return view;
    }

}
