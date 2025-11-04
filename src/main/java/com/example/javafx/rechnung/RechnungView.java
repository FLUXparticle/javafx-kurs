package com.example.javafx.rechnung;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class RechnungView extends BorderPane {

    final MenuItem adressenMenuItem;
    final MenuItem rechnungMenuItem;
    final Label statusLabel;

    public RechnungView() {
        // Menüleiste
        MenuBar menuBar = new MenuBar();
        Menu dateiMenu = new Menu("Datei");

        adressenMenuItem = new MenuItem("Adressen");
        rechnungMenuItem = new MenuItem("Rechnung");

        dateiMenu.getItems().addAll(adressenMenuItem, rechnungMenuItem);
        menuBar.getMenus().add(dateiMenu);

        setTop(menuBar);

        // Zentrum: Logo aus Shapes
        StackPane logoPane = buildLogoPane();
        setCenter(logoPane);

        statusLabel = new Label();
        statusLabel.setPadding(new Insets(5));
        setBottom(statusLabel);
    }

    private static StackPane buildLogoPane() {
        StackPane logoPane = new StackPane();
        logoPane.setPadding(new Insets(40));

        Circle circle = new Circle(40, Color.LIGHTBLUE);
        Rectangle square = new Rectangle(40, 40);
        square.setFill(Color.LIGHTGRAY);
        square.setRotate(45);

        Line line = new Line(-30, 0, 30, 0);
        line.setStroke(Color.DARKGRAY);
        line.setStrokeWidth(3);

        logoPane.getChildren().addAll(circle, square, line);

        return logoPane;
    }

}
