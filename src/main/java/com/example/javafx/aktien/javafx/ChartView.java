package com.example.javafx.aktien.javafx;

import com.example.javafx.aktien.model.*;
import javafx.geometry.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ChartView extends VBox {
    final LineChart<Number, Number> chart;
    final CheckBox[] checkBoxes;
    final HBox legendBox;

    public ChartView() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Tag"); xAxis.setForceZeroInRange(false);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Preis");

        chart = new LineChart<Number, Number>(xAxis, yAxis);
        chart.setTitle("Aktienkurse");

        getChildren().add(chart);

        // Container for series visibility toggles
        legendBox = new HBox(5);
        legendBox.setPadding(new Insets(5));
        checkBoxes = new CheckBox[AktienGame.AKTIEN_NAMEN.length];
        for (int i = 0; i < AktienGame.AKTIEN_NAMEN.length; i++) {
            String name = AktienGame.AKTIEN_NAMEN[i];
            // create a checkbox to toggle this series
            CheckBox cb = new CheckBox(name);
            cb.setSelected(true);
            // add checkbox to legend container
            checkBoxes[i] = cb;
            legendBox.getChildren().add(cb);
        }
        getChildren().add(legendBox);
    }
}
