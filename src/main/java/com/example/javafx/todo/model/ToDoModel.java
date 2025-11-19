package com.example.javafx.todo.model;

public class ToDoModel {

    private final ToDoItem rootItem;

    public ToDoModel() {
        rootItem = new ToDoItem("=== Alle Aufgaben ===");

        ToDoItem haushalt = addChildTask(rootItem, "Haushalt");
        addChildTask(haushalt, "Einkaufen");
        addChildTask(haushalt, "Bad putzen");

        ToDoItem arbeit = addChildTask(rootItem, "Arbeit");
        addChildTask(arbeit, "Präsentation fertigstellen");
    }

    public ToDoItem getRootItem() {
        return rootItem;
    }

    public ToDoItem addNewTask(ToDoItem parent) {
        ToDoItem todoItem = addChildTask(parent, "Neue Aufgabe");
        print();
        return todoItem;
    }

    private ToDoItem addChildTask(ToDoItem parent, String name) {
        ToDoItem todoItem = new ToDoItem(name);
        parent.getChildren().add(todoItem);
        return todoItem;
    }

    public void removeChildTask(ToDoItem parent, ToDoItem item) {
        parent.getChildren().remove(item);
        print();
    }

    public void print() {
        print("", rootItem);
    }

    private void print(String prefix, ToDoItem item) {
        System.out.println(prefix + item);
        String childPrefix = prefix + "  ";
        for (ToDoItem child : item.getChildren()) {
            print(childPrefix, child);
        }
    }

}
