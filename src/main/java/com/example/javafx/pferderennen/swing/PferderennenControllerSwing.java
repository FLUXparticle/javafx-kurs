package com.example.javafx.pferderennen.swing;

import com.example.javafx.pferderennen.model.*;

public class PferderennenControllerSwing implements Anzeige {

    private final PferderennenModel model;

    private final PferderennenViewSwing view;

    public PferderennenControllerSwing() {
        model = new PferderennenModel(this);
        view = new PferderennenViewSwing(model);

        view.startButton.addActionListener(event -> model.starteRennen());
    }

    @Override
    public void rennenAnzeigen(Pferd[] pferde) {
        for (int i = 0; i < pferde.length; i++) {
            view.pferde[i].setBounds(pferde[i].getMeter() / 2, i * 40, 80, 30);
        }
    }

    public static void main(String[] args) {
        PferderennenControllerSwing controller = new PferderennenControllerSwing();
        controller.view.setVisible(true);
    }

}
