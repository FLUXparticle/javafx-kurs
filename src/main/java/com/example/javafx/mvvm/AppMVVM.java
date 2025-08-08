package com.example.javafx.mvvm;

import com.example.javafx.mvvm.model.*;
import javafx.application.*;
import javafx.stage.*;

public class AppMVVM extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        ViewModel viewModel = new ViewModel(model);
        View view = new View(viewModel);
        view.buildStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
