package com.example.javafx.pferderennen.model;

public class PferderennenModel {

    private Pferd[] pferde = {};

    private Anzeige anzeige;

    public PferderennenModel(Anzeige anzeige) {
        this.anzeige = anzeige;
        // Pferde initialisieren
    }

    public void starteRennen() {
        var thread = new Thread(this::run);
        thread.start();
    }

    private void run() {
        boolean rennenBeendet = false;
        while (!rennenBeendet) {
            // Pferde bewegen
            anzeige.rennenAnzeigen(pferde);
        }
    }

    public Pferd[] getPferde() {
        return pferde;
    }

}
