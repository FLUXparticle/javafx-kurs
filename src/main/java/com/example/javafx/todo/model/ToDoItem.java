package com.example.javafx.todo.model;

import java.util.*;

public class ToDoItem {

    private String name;

    private Collection<ToDoItem> children;

    public ToDoItem(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ToDoItem> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return name;
    }

}
