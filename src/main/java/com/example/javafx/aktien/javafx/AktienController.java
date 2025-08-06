package com.example.javafx.aktien.javafx;

import com.example.javafx.aktien.model.*;
import javafx.collections.*;
import javafx.scene.control.*;

public class AktienController {

    private final AktienView view;
    private final AktienGame model;

    private final ObservableList<AktienView.RowData> rows = FXCollections.observableArrayList();

    public AktienController(AktienView view) {
        this.view  = view;
        this.model = new AktienGame();

        // initiale Verkabelung
        view.table.setItems(rows);
        view.cbAktie.getSelectionModel().selectedIndexProperty()
            .addListener((o, oldI, newI) -> updateSlider());
        view.rbKauf.setOnAction(e -> updateSlider());
        view.rbVerkauf.setOnAction(e -> updateSlider());
        view.slider.valueProperty().addListener((o, oldV, newV) -> updateMoney());
        view.btnExecute.setOnAction(e -> executeTrade());
        view.btnNextDay.setOnAction(e -> nextDay());
        view.btnChart.setOnAction(e -> openChart());

        // Startzustand
        updateTable();
        updateSlider();
        updateMoney();
    }

    private void updateTable() {
        rows.clear();
        double sumValue = 0;
        for (int i = 0; i < AktienGame.AKTIEN_NAMEN.length; i++) {
            String name  = AktienGame.AKTIEN_NAMEN[i];
            double price = model.getWert(i);
            int    own   = model.getBesitz(i);
            double value = price * own;
            double diff  = model.getDiff(i);
            sumValue += value;
            rows.add(new AktienView.RowData(name, price, own, value, diff));
        }
        view.tfIndex .setText(String.format("%.2f", model.getIndex()));
        view.tfDiff  .setText(String.format("%.2f", model.getIndex() - model.getAlterIndex()));
        view.tfGeld  .setText(String.format("%.2f", model.getGeld()));
        view.tfAktien.setText(String.format("%.2f", sumValue));
        view.tfGesamt.setText(String.format("%.2f", model.getGeld() + sumValue));
    }

    private void updateSlider() {
        int idx = view.cbAktie.getSelectionModel().getSelectedIndex();
        if (idx < 0) return;
        if (view.rbKauf.isSelected()) {
            view.slider.setMax(model.maxBuy(idx));
        } else {
            view.slider.setMax(model.getBesitz(idx));
        }
        view.slider.setMin(0);
        view.slider.setValue(0);
    }

    private void updateMoney() {
        int    idx = view.cbAktie.getSelectionModel().getSelectedIndex();
        double amt = view.slider.getValue();
        if (idx < 0) return;
        double cost = model.getWert(idx) * amt;
        view.tfMoney.setText(String.format("%.2f", cost));
    }

    private void executeTrade() {
        int idx = view.cbAktie.getSelectionModel().getSelectedIndex();
        if (idx < 0) return;
        int amt = (int) view.slider.getValue();
        try {
            if (view.rbKauf.isSelected())   model.buy(idx, amt);
            else                            model.sell(idx, amt);
            updateTable();
            updateSlider();
            updateMoney();
        } catch (IllegalArgumentException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void nextDay() {
        model.nextDay();
        updateTable();
        updateSlider();
        updateMoney();
    }

    private void openChart() {
        ChartController chartController = new ChartController(model);
        chartController.show();
    }
}