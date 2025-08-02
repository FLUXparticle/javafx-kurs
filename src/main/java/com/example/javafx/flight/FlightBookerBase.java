package com.example.javafx.flight;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.time.*;
import java.time.format.*;

public class FlightBookerBase extends Application {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    protected static final String STYLE_NORMAL = "";

    protected static final String STYLE_ERROR = "-fx-background-color: lightcoral";

    protected static String dateToString(LocalDate date) {
        return date.format(FORMAT);
    }

    protected static LocalDate stringToDate(String string) {
        try {
            return LocalDate.from(FORMAT.parse(string));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    protected enum FlightType {

        ONE_WAY_FLIGHT("one-way flight"),
        RETURN_FLIGHT("return flight");

        private final String display;

        FlightType(String display) {
            this.display = display;
        }

        @Override
        public String toString() {
            return display;
        }

    }

    protected ComboBox<FlightType> flightType;

    protected TextField startDate;

    protected TextField returnDate;

    protected Button book;

    public final void start(Stage stage) {
        flightType = new ComboBox<>();
        flightType.getItems().addAll(FlightType.values());
        flightType.setValue(FlightType.ONE_WAY_FLIGHT);
        startDate = new TextField(dateToString(LocalDate.now()));
        returnDate = new TextField(dateToString(LocalDate.now()));
        book = new Button("Book");
        book.setOnAction(this::validate);

        bind();

        VBox root = new VBox(10, flightType, startDate, returnDate, book);
        root.setPadding(new Insets(10));

        stage.setScene(new Scene(root));
        stage.setTitle("Flight Booker");
        stage.show();
    }

    protected void bind() {
        // empty
    }

    private void validate(ActionEvent event) {
        // TODO Aufgabe 4
    }

}
