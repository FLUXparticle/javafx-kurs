package com.example.javafx.cocktails;

import com.example.javafx.cocktails.model.*;
import com.tobiasdiez.easybind.*;
import javafx.application.*;
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

    private final AsyncListController<Ingredient> alleZutaten;
    private final AsyncListController<Ingredient> kuehlschrank;
    private final AsyncListController<RezeptOhneZutaten> rezepte;
    private final AsyncListController<AnweisungText> anweisungen;

    public CocktailController(CocktailModel model, CocktailView view) {
        this.model = model;
        this.view = view;

        alleZutaten = new AsyncListController<>(view.listAlle, this::loadIngredients);
        kuehlschrank = new AsyncListController<>(view.listKuehlschrank, this::loadFridge);
        rezepte = new AsyncListController<>(view.listRezepte, this::loadRezepte);
        anweisungen = new AsyncListController<>(view.listAnweisungen, this::loadRecipe);
    }

    public void bind() {
        var filteredZutaten = EasyBind.map(view.txtSuche.textProperty(), text -> {
            var search = text.toLowerCase();
            return alleZutaten.getList().filtered(zutat -> zutat.name().toLowerCase().contains(search));
        });
        view.listAlle.getListView().itemsProperty().bind(filteredZutaten);

        updateIngredients();
        updateFridge();
        updateRezepte();

        view.btnAdd.setOnAction(event -> {
            List<Ingredient> selected = view.listAlle.getListView().getSelectionModel().getSelectedItems();
            updateFridge(selected, true);
        });

        view.btnRemove.setOnAction(event -> {
            List<Ingredient> selected = view.listKuehlschrank.getListView().getSelectionModel().getSelectedItems();
            updateFridge(selected, false);
        });

        view.listRezepte.getListView().getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                updateRecipe();
            }
        });

        EasyObservableList<Integer> fridgeIDs = EasyBind.map(kuehlschrank.getList(), Ingredient::id);

        view.listAnweisungen.getListView().setCellFactory(lv -> {
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
        RezeptOhneZutaten selectedItem = view.listRezepte.getListView().getSelectionModel().getSelectedItem();
        return model.getAnweisungenFor(selectedItem.id());
    }

    private <T> void updateInBackground(AsyncListController<T> list, Callable<List<T>> supplier) {
//        list.setItemsAsync(supplier);
        list.update();
    }

}
