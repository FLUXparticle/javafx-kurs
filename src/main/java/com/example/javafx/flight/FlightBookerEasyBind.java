package com.example.javafx.flight;

import com.tobiasdiez.easybind.*;
import javafx.beans.property.*;

public class FlightBookerEasyBind extends FlightBookerBase {

    protected void bind() {
        // TODO Aufgabe 6
    }

    private static EasyBinding<String> errorBinding(StringProperty stringProperty) {
        return EasyBind.map(stringProperty, s -> (stringToDate(s) != null) ? STYLE_NORMAL : STYLE_ERROR);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
