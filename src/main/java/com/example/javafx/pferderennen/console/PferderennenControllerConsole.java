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
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        for (int i = 0; i < pferde.length; i++) {
            System.out.printf("%20s ", pferde[i].getName());
            for (int m = 0; m < pferde[i].getMeter() / 20; m++) {
                System.out.print('.');
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        PferderennenControllerConsole controller = new PferderennenControllerConsole();

        // Pferde anzeigen
        for (Pferd pferd : controller.model.getPferde()) {
            System.out.println(pferd);
        }

        // Auf ENTER warten
        Scanner sc = new Scanner(System.in);
        System.out.print("Los?");
        sc.nextLine();

        // Rennen starten
        controller.model.starteRennen();
    }

}
