package ru.geekbrains.lesson01.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Server extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 250;
    private static boolean isServerWorking;
    private final JButton btnStart = new JButton("Start server");
    private final JButton btnStop = new JButton("Stop server");
    private final JTextArea log = new JTextArea("Server is stopped");

    public Server() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setAlwaysOnTop(true);
        setTitle("Chat server");
        setResizable(false);

        add(log, BorderLayout.SOUTH);
        JPanel jPanel = new JPanel(new GridLayout(1,2));
        jPanel.add(btnStart);
        jPanel.add(btnStop);
        add(jPanel);

        log.setEditable(false);


        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    log.setText("Server is already running.");
                } else {
                    log.setText("Server is started.");;
                }
                isServerWorking = true;
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isServerWorking) {
                    log.setText("Server is already stopped.");
                } else {
                    log.setText("Server stopped.");
                }
                isServerWorking = false;
            }
        });

        setVisible(true);

    }
}


