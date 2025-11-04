package com.example.javafx.cocktails;

import com.example.javafx.cocktails.model.*;
import com.tobiasdiez.easybind.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.cell.*;
import javafx.scene.paint.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import static java.util.Comparator.*;

public class CocktailController {

    private static final String USER_ID = getUserID();

    private static String getUserID() {
        String userID = System.getenv("COMPUTERNAME");
        if (userID == null) {
            userID = "1";
        }
        return userID;
    }

    private final CocktailModel model;
    private final CocktailView view;

    private final ObservableList<Ingredient> alleZutaten = FXCollections.observableArrayList();
    private final ObservableList<Ingredient> kuehlschrank = FXCollections.observableArrayList();
    private final ObservableList<RezeptOhneZutaten> rezepte = FXCollections.observableArrayList();
    private final ObservableList<AnweisungText> anweisungen = FXCollections.observableArrayList();

    public CocktailController() {
        this.model = new CocktailModel();
        this.view = new CocktailView();

        view.listAlle.setItems(alleZutaten);
        view.listKuehlschrank.setItems(kuehlschrank);
        view.listRezepte.setItems(rezepte);
        view.listAnweisungen.setItems(anweisungen);

        var filteredZutaten = EasyBind.map(view.txtSuche.textProperty(), text -> {
            var search = text.toLowerCase();
            return alleZutaten.filtered(zutat -> zutat.name().toLowerCase().contains(search));
        });
        view.listAlle.itemsProperty().bind(filteredZutaten);

        updateIngredients();
        updateFridge();
        updateRezepte();

        view.btnAdd.setOnAction(event -> {
            List<Ingredient> selected = view.listAlle.getSelectionModel().getSelectedItems();
            updateFridge(selected, true);
        });

        view.btnRemove.setOnAction(event -> {
            List<Ingredient> selected = view.listKuehlschrank.getSelectionModel().getSelectedItems();
            updateFridge(selected, false);
        });

        view.listRezepte.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                updateRecipe();
            }
        });

        EasyObservableList<Integer> fridgeIDs = EasyBind.map(kuehlschrank, Ingredient::id);

        view.listAnweisungen.setCellFactory(lv -> {
            var cell = new TextFieldListCell<AnweisungText>();
            Paint oldFill = cell.getTextFill();

            EasyBinding<Paint> mappedColor = EasyBind.map(cell.itemProperty(), (AnweisungText item) -> {
                return (item == null) ? oldFill : fridgeIDs.contains(item.zutatId()) ? Color.GREEN : Color.RED;
            });

            cell.textFillProperty().bind(mappedColor);

            return cell;
        });
    }

    private void updateFridge(List<Ingredient> selected, boolean inFridge) {
        List<Ingredient> copy = new ArrayList<>(selected);
        BackgroundService.runInBackground(() -> {
            for (Ingredient z : copy) {
                try {
                    model.updateFridge(USER_ID, z.id(), inFridge);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            Platform.runLater(this::updateFridge);
            Platform.runLater(this::updateRezepte);
        });
    }

    private void updateIngredients() {
        updateInBackground(alleZutaten, this::loadIngredients);
    }

    private List<Ingredient> loadIngredients() throws IOException {
        List<Ingredient> zutaten = model.getAllZutaten();
        zutaten.sort(comparing(Ingredient::name));
        return zutaten;
    }

    private void updateFridge() {
        updateInBackground(kuehlschrank, this::loadFridge);
    }

    private List<Ingredient> loadFridge() throws IOException {
        return model.getFridge(USER_ID);
    }

    private void updateRezepte() {
        updateInBackground(rezepte, this::loadRezepte);
    }

    private List<RezeptOhneZutaten> loadRezepte() throws IOException {
        return model.getContainsRecipes(USER_ID);
    }

    private void updateRecipe() {
        updateInBackground(anweisungen, this::loadRecipe);
    }

    private List<AnweisungText> loadRecipe() throws IOException {
        RezeptOhneZutaten selectedItem = view.listRezepte.getSelectionModel().getSelectedItem();
        return model.getAnweisungenFor(selectedItem.id());
    }

    private <T> void updateInBackground(ObservableList<T> list, Callable<List<T>> supplier) {
        BackgroundService.runInBackground(() -> {
            try {
                List<T> result = supplier.call();
                Platform.runLater(() -> {
                    list.setAll(result);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Parent getRoot() {
        return view;
    }

    public void shutdown() {
        BackgroundService.shutdown();
    }

}
