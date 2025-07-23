package com.example.javafx.flight;

public class FlightBookerCallback extends FlightBookerBase {

    protected void bind() {
        // This value must be reinitialized so all callback will be called
        flightType.setValue(null);

        // TODO Aufgabe 5

        // It is important to set the value after the initializations of the callbacks.
        flightType.setValue(FlightType.ONE_WAY_FLIGHT);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
