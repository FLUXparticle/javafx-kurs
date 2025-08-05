package com.example.javafx.pferderennen.swing;

import com.example.javafx.pferderennen.model.*;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PferderennenViewSwing extends JFrame {

    final JPanel rennbahn = new JPanel();
    final JButton[] pferde;
    final JButton startButton = new JButton("Lauf!");

    public PferderennenViewSwing(PferderennenModel model) {
        setTitle("Pferderennen");
        setSize(620, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        rennbahn.setPreferredSize(new Dimension(580, 340));
        rennbahn.setBackground(Color.GRAY);
        rennbahn.setLayout(null);

        Pferd[] modelPferde = model.getPferde();

        pferde = new JButton[modelPferde.length];
        for (int i = 0; i < pferde.length; i++) {
            pferde[i] = new JButton(modelPferde[i].getName());
            rennbahn.add(pferde[i]);
        }

        add(rennbahn);
        add(startButton);
    }

}
