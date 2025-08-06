package com.example.javafx.aktien.javafx;

import com.example.javafx.aktien.model.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.stage.*;

public class ChartController {
    private final AktienGame model;
    private final ChartView view;

    public ChartController(AktienGame model) {
        this.model = model;
        this.view = new ChartView();

        // create a series for each stock
        for (int i = 0; i < AktienGame.AKTIEN_NAMEN.length; i++) {
            String name = AktienGame.AKTIEN_NAMEN[i];
            ObservableList<DataPoint> history = model.getPriceHistory().get(i);

            // map price history to data points
            ObservableList<XYChart.Data<Number, Number>> data = FXCollections.observableArrayList();
//                EasyBind.map(history, ChartController::transformDataPoint);

            for (DataPoint dataPoint : history) {
                data.add(transformDataPoint(dataPoint));
            }

            history.addListener((ListChangeListener<DataPoint>) change -> {
                while (change.next()) {
                    if (change.wasAdded()) {
                        int from = change.getFrom();
                        for (int i1 = 0; i1 < change.getAddedSize(); i1++) {
                            DataPoint added = change.getAddedSubList().get(i1);
                            data.add(from + i1, transformDataPoint(added));
                        }
                    }
                    if (change.wasRemoved()) {
                        int from = change.getFrom();
                        for (int i1 = 0; i1 < change.getRemovedSize(); i1++) {
                            data.remove(from);
                        }
                    }
                }
            });

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(name);
            series.setData(data);
            view.chart.getData().add(series);

            Node seriesNode = series.getNode();
            seriesNode.visibleProperty().bind(view.checkBoxes[i].selectedProperty());
        }
    }

    private static XYChart.Data<Number, Number> transformDataPoint(DataPoint dataPoint) {
        System.out.println("dataPoint = " + dataPoint);
        return new XYChart.Data<>(dataPoint.day(), dataPoint.value());
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Aktien Chart");
        stage.setScene(new Scene(view, 600, 400));
        stage.show();
    }
}
