package com.example.javafx.aktien.swing;

import com.example.javafx.aktien.model.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class AktienSwing extends JFrame implements ActionListener, ChangeListener {

    private JTable table;
    private JTextField textIndex;
    private JTextField textDiff;
    private JTextField textAktien;
    private JTextField textGeld;
    private JTextField textGesamt;

    private JComboBox<String> boxAktie;
    private JRadioButton buttonKauf;
    private JRadioButton buttonVerkauf;
    private JSlider slider;
    private JTextField textMoney;
    private JButton buttonExecute;

    private JButton buttonNext;

    private AktienGame game;
    private TableModel tableModel;

    public AktienSwing() {
        super("Aktien");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        table = new JTable(1 + AktienGame.AKTIEN_NAMEN.length, 5);
        table.setEnabled(false);
        tableModel = table.getModel();
        tableModel.setValueAt("Aktie", 0, 0);
        tableModel.setValueAt("Preis", 0, 1);
        tableModel.setValueAt("Besitz", 0, 2);
        tableModel.setValueAt("Wert", 0, 3);
        tableModel.setValueAt("Differenz", 0, 4);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(table, c);
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;

        JLabel labelIndex = new JLabel("Index");
        c.gridx = 0;
        c.gridy = 1;
        add(labelIndex, c);
        textIndex = new JTextField(6);
        textIndex.setEditable(false);
        c.gridx = 1;
        c.gridy = 1;
        add(textIndex, c);

        JLabel labelDiff = new JLabel("Änderung");
        c.gridx = 2;
        c.gridy = 1;
        add(labelDiff, c);
        textDiff = new JTextField(6);
        textDiff.setEditable(false);
        c.gridx = 3;
        c.gridy = 1;
        add(textDiff, c);

        JLabel labelAktien = new JLabel("Aktien");
        c.gridx = 0;
        c.gridy = 2;
        add(labelAktien, c);
        textAktien = new JTextField(6);
        textAktien.setEditable(false);
        c.gridx = 1;
        c.gridy = 2;
        add(textAktien, c);

        JLabel labelGeld = new JLabel("Geld");
        c.gridx = 0;
        c.gridy = 3;
        add(labelGeld, c);
        textGeld = new JTextField(6);
        textGeld.setEditable(false);
        c.gridx = 1;
        c.gridy = 3;
        add(textGeld, c);

        JLabel labelGesamt = new JLabel("Gesamt");
        c.gridx = 0;
        c.gridy = 4;
        add(labelGesamt, c);
        textGesamt = new JTextField(6);
        textGesamt.setEditable(false);
        c.gridx = 1;
        c.gridy = 4;
        add(textGesamt, c);

        {
            JPanel actionPanel = new JPanel();

            boxAktie = new JComboBox<String>(AktienGame.AKTIEN_NAMEN);
            boxAktie.addActionListener(this);
            actionPanel.add(boxAktie);

            ButtonGroup buttonGroup = new ButtonGroup();
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            buttonKauf = new JRadioButton("Kaufen");
            buttonKauf.addActionListener(this);
            buttonKauf.setSelected(true);
            buttonGroup.add(buttonKauf);
            panel.add(buttonKauf);

            buttonVerkauf = new JRadioButton("Vekaufen");
            buttonVerkauf.addActionListener(this);
            buttonGroup.add(buttonVerkauf);
            panel.add(buttonVerkauf);

            actionPanel.add(panel);

            slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
            slider.setMajorTickSpacing(20);
            slider.setMinorTickSpacing(5);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.addChangeListener(this);
            actionPanel.add(slider);

            textMoney = new JTextField(6);
            textMoney.setEditable(false);
            actionPanel.add(textMoney);

            buttonExecute = new JButton("Ausführen");
            buttonExecute.addActionListener(this);
            actionPanel.add(buttonExecute);

            c.gridx = 0;
            c.gridy = 5;
            c.gridwidth = 5;
            add(actionPanel, c);
            c.gridwidth = 1;
        }

        buttonNext = new JButton("Nächster Tag");
        buttonNext.addActionListener(this);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 5;
        add(buttonNext, c);
        c.gridwidth = 1;

        pack();
        setResizable(false);

        game = new AktienGame();

        update();
    }

    public void update() {
        double sumAktienwert = 0.0;

        String[] strings = AktienGame.AKTIEN_NAMEN;
        for (int i = 0; i < strings.length; i++) {
            tableModel.setValueAt(strings[i], 1 + i, 0);

            double wert = game.getWert(i);
            tableModel.setValueAt(String.format("%.2f", wert), 1 + i, 1);

            int besitz = game.getBesitz(i);
            tableModel.setValueAt(besitz, 1 + i, 2);

            double aktienwert = wert * besitz;
            tableModel.setValueAt(String.format("%.2f", aktienwert), 1 + i, 3);
            sumAktienwert += aktienwert;

            double diff = game.getDiff(i);
            tableModel.setValueAt(String.format("%.2f", diff), 1 + i, 4);
        }

        double alterIndex = game.getAlterIndex();
        double index = game.getIndex();
        textIndex.setText(String.format("%.2f", index));
        textDiff.setText(String.format("%.2f", index - alterIndex));

        double geld = game.getGeld();
        textGeld.setText(String.format("%.2f", geld));

        textAktien.setText(String.format("%.2f", sumAktienwert));

        textGesamt.setText(String.format("%.2f", geld + sumAktienwert));

        updateSlider();
    }

    public void updateSlider() {
        int selectedIndex = boxAktie.getSelectedIndex();

        if (buttonKauf.isSelected()) {
            slider.setMaximum(game.maxBuy(selectedIndex));
        }

        if (buttonVerkauf.isSelected()) {
            int besitz = game.getBesitz(selectedIndex);
            slider.setMaximum(besitz);
        }

        updateMoney();
    }

    public void updateMoney() {
        int selectedIndex = boxAktie.getSelectedIndex();
        int anzahl = slider.getValue();
        double geld = game.getWert(selectedIndex) * anzahl;
        textMoney.setText(String.format("%.2f", geld));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        if (src == buttonNext) {
            game.nextDay();
            update();
        }

        if (src == boxAktie || src == buttonKauf || src == buttonVerkauf) {
            updateSlider();
        }

        if (src == buttonExecute) {
            int selectedIndex = boxAktie.getSelectedIndex();
            if (buttonKauf.isSelected()) {
                game.buy(selectedIndex, slider.getValue());
            }
            if (buttonVerkauf.isSelected()) {
                game.sell(selectedIndex, slider.getValue());
            }
            slider.setValue(0);
            update();
        }
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        updateMoney();
    }

    public static void main(String[] args) {
        AktienSwing frame = new AktienSwing();
        frame.setVisible(true);
    }

}
