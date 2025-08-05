package com.example.javafx.pferderennen.console;

import com.example.javafx.pferderennen.model.*;

import java.util.*;

public class PferderennenControllerConsole implements Anzeige {

    private PferderennenModel model;

    public PferderennenControllerConsole() {
        model = new PferderennenModel(this);
    }

    @Override
    public void rennenAnzeigen(Pferd[] pferde) {
        // aktuellen Fortschritt der Pferde anzeigen
    }

    public static void main(String[] args) {
        PferderennenControllerConsole controller = new PferderennenControllerConsole();

        // Pferde anzeigen
        for (Pferd pferd : controller.model.getPferde()) {
            // ...
        }

        // Auf ENTER warten
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        // Rennen starten
        controller.model.starteRennen();
    }

}
