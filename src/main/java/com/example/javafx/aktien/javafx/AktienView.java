package com.example.javafx.aktien.javafx;

import com.example.javafx.aktien.model.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
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

        // TODO Tabelle aufbauen

        // TODO GridPane

        // TODO HBox
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