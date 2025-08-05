package com.example.javafx.pferderennen.model;

public class PferderennenModel {

    private Pferd[] pferde = {
            new Pferd("Joe Law"),
            new Pferd("L. B. J."),
            new Pferd("Herr Washburn"),
            new Pferd("Frl. Karen"),
            new Pferd("Fröhlich"),
            new Pferd("Pferd Nr. 6"),
            new Pferd("Versteife nicht"),
            new Pferd("Mitternacht")
    };

    private Anzeige anzeige;

    public PferderennenModel(Anzeige anzeige) {
        this.anzeige = anzeige;
        // Pferde initialisieren
        int summeStaerken = 0;
        for (int i = 0; i < pferde.length; i++) {
            summeStaerken += pferde[i].getStaerke();
        }

        for (int i = 0; i < pferde.length; i++) {
            pferde[i].berechneQuote(summeStaerken);
        }
    }

    public void starteRennen() {
        var thread = new Thread(this::run);
        thread.start();
    }

    private void run() {
        boolean rennenBeendet = false;
        while (!rennenBeendet) {
            // Pferde bewegen
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < pferde.length; i++) {
                pferde[i].lauf();
            }

            for (int i = 0; i < pferde.length; i++) {
                if (pferde[i].getMeter() >= 1000) {
                    rennenBeendet = true;
                }
            }

            anzeige.rennenAnzeigen(pferde);
        }
    }

    public Pferd[] getPferde() {
        return pferde;
    }

}
