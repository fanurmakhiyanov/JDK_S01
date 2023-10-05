package ru.geekbrains.lesson01;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    static int mode = 0;
    static int sizeX = 3;
    static int sizeY = 3;
    static int winLength = 3;


    JButton btnStart = new JButton("Start new game");

    SettingsWindow(GameWindow gameWindow) {

        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        setLayout(new GridLayout(10, 1));
        add(new JLabel("Выберите режим игры"));

        ButtonGroup groupButton = new ButtonGroup();
        JRadioButton pvp = new JRadioButton("Человек против человека");
        JRadioButton pve = new JRadioButton("Человек против компьютера");
        groupButton.add(pvp);
        groupButton.add(pve);
        add(pvp);
        add(pve);

        JLabel labelField = new JLabel("Выберите размеры поля");
        add(labelField);
        JLabel labelCurrentField = new JLabel("Текущий размер поля: ");
        add(labelCurrentField);
        JSlider sliderField = new JSlider(3, 10, 3);
        sliderField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelCurrentField.setText("Текущий размер поля: " + sliderField.getValue());
            }
        });
        add(sliderField);

        JLabel labelLength = new JLabel("Выберите длину для победы");
        add(labelLength);
        JLabel labelCurrentLength = new JLabel("Установленная длина: ");
        add(labelCurrentLength);
        JSlider sliderLength = new JSlider(3, 10, 3);
        sliderLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelCurrentLength.setText("Текущий размер поля: " + sliderLength.getValue());
            }
        });

        add(sliderLength);
        add(btnStart);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pve.isSelected()) {
                    mode = 1;
                }
                sizeX = sliderField.getValue();
                sizeY = sliderField.getValue();
                winLength = sliderLength.getValue();
                gameWindow.startNewGame(mode, sizeX, sizeY, winLength);
                System.out.printf("мод: %d, x: %d, y: %d, length: %d", mode, sizeX, sizeY, winLength);
                setVisible(false);
            }
        });

        setVisible(true);

    }

}