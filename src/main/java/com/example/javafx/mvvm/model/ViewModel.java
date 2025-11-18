package com.example.javafx.mvvm.model;

import javafx.beans.property.*;

public class ViewModel {

    private final Model model;

    private final StringProperty input  = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("");

    public ViewModel(Model model) {
        this.model = model;
    }

    public StringProperty inputProperty()  { return input; }
    public StringProperty outputProperty() { return output; }

    /** Command/Action vom View getriggert */
    public void checkPassword() {
        String result = model.play(input.get());
        output.set(result);
    }

}
