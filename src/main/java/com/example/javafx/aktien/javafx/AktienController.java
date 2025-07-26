package com.example.javafx.aktien.javafx;

import com.example.javafx.aktien.model.*;
import javafx.collections.*;

public class AktienController {

    private final AktienView view;
    private final AktienGame model;

    private final ObservableList<AktienView.RowData> rows = FXCollections.observableArrayList();

    public AktienController(AktienView view) {
        this.view  = view;
        this.model = new AktienGame();

        // TODO initiale Verkabelung

        // Startzustand
        updateTable();
        updateSlider();
        updateMoney();
    }

    private void updateTable() {
    }

    private void updateSlider() {
    }

    private void updateMoney() {
    }

    private void executeTrade() {
    }

    private void nextDay() {
        model.nextDay();
        updateTable();
        updateSlider();
        updateMoney();
    }

}
