package com.example.javafx.aktien.javafx;

import com.example.javafx.aktien.model.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;

public class AktienView extends VBox {
    // Tabelle
    public final TableView<RowData> table = new TableView<>();

    // Statustextfelder
    public final TextField tfIndex   = createDisabledField();
    public final TextField tfDiff    = createDisabledField();
    public final TextField tfAktien  = createDisabledField();
    public final TextField tfGeld    = createDisabledField();
    public final TextField tfGesamt  = createDisabledField();

    // Aktionselemente
    public final ComboBox<String>    cbAktie      = new ComboBox<>(FXCollections.observableArrayList(AktienGame.AKTIEN_NAMEN));
    public final RadioButton         rbKauf       = new RadioButton("Kaufen");
    public final RadioButton         rbVerkauf    = new RadioButton("Verkaufen");
    public final Slider              slider       = new Slider(0, 0, 0);
    public final TextField           tfMoney      = createDisabledField();
    public final Button              btnExecute   = new Button("Ausführen");
    public final Button              btnNextDay   = new Button("Nächster Tag");

    public AktienView() {
        super(10);
        setPadding(new Insets(10));

        // Tabelle aufbauen
        TableColumn<RowData,String> colName  = new TableColumn<>("Aktie");
        colName .setCellValueFactory(new PropertyValueFactory<>("name"));
        colName .setPrefWidth(120);

        TableColumn<RowData,Double> colPreis = new TableColumn<>("Preis");
        colPreis.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPreis.setPrefWidth(100);

        TableColumn<RowData,Integer> colOwn   = new TableColumn<>("Besitz");
        colOwn  .setCellValueFactory(new PropertyValueFactory<>("own"));
        colOwn  .setPrefWidth(80);

        TableColumn<RowData,Double> colWert  = new TableColumn<>("Wert");
        colWert .setCellValueFactory(new PropertyValueFactory<>("value"));
        colWert .setPrefWidth(100);

        TableColumn<RowData,Double> colDiff  = new TableColumn<>("Diff");
        colDiff .setCellValueFactory(new PropertyValueFactory<>("diff"));
        colDiff .setPrefWidth(100);

        table.getColumns().addAll(colName, colPreis, colOwn, colWert, colDiff);

        // Layout
        GridPane status = new GridPane();
        status.setHgap(10); status.setVgap(5);
        status.addRow(0, new Label("Index:"), tfIndex, new Label("Δ:"), tfDiff);
        status.addRow(1, new Label("Aktien:"), tfAktien, new Label("Geld:"), tfGeld);
        status.addRow(2, new Label("Gesamt:"), tfGesamt);

        HBox actions = new HBox(10, cbAktie, rbKauf, rbVerkauf, slider, tfMoney, btnExecute, btnNextDay);
        rbKauf.setSelected(true);

        // View zusammensetzen
        getChildren().addAll(table, status, actions);
    }

    private static TextField createDisabledField() {
        TextField tf = new TextField();
        tf.setEditable(false);
        tf.setPrefColumnCount(6);
        return tf;
    }

    // Datenhalter für eine Tabellenzeile
    public static class RowData {
        private final String  name;
        private final double  price;
        private final int     own;
        private final double  value;
        private final double  diff;

        public RowData(String name, double price, int own, double value, double diff) {
            this.name  = name;
            this.price = price;
            this.own   = own;
            this.value = value;
            this.diff  = diff;
        }
        public String  getName()  { return name; }
        public double  getPrice() { return price; }
        public int     getOwn()   { return own; }
        public double  getValue() { return value; }
        public double  getDiff()  { return diff; }
    }
}