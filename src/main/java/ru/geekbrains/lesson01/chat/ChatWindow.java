package ru.geekbrains.lesson01.chat;//package ru.geekbrains.lesson01.chat;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
//public class ChatWindow extends JFrame {
//    private static final int WINDOW_HEIGHT = 600;
//    private static final int WINDOW_WIDTH = 600;
//    private static final int WINDOW_POSX = 700;
//    private static final int WINDOW_POSY = 200;
//
//    JButton btnConnect = new JButton("Connect");
//    JButton btnExit = new JButton("Exit");
//
//    public ChatWindow() {
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocation(WINDOW_POSX, WINDOW_POSY);
//        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//        setTitle("Chat");
//        setResizable(false);
//
//        btnConnect.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        btnExit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//
//        JPanel panSet = new JPanel(new GridLayout(5, 1));
//        JTextField loginField = new JTextField("login");
//        JTextField passField = new JTextField("password");
//        JTextField ipField = new JTextField("ip");
//        JTextField portField = new JTextField("port");
//        panSet.add(loginField);
//        panSet.add(passField);
//        panSet.add(ipField);
//        panSet.add(portField);
//        panSet.add(btnConnect);
//
//        add(panSet, BorderLayout.NORTH);
//
//        JPanel panMid = new JPanel(new GridLayout(1, 2));
//        JTextArea fieldChat = new JTextArea();
////        Vector<String> messages = new Vector<>();
//        fieldChat.setEditable(false);
//        JList<String> listUsers = new JList<>();
//        String users[] = {"Dima", "Katya", "Sasha", "Vasya", "Ignat"};
//        listUsers.setListData(users);
//        panMid.add(fieldChat);
//        panMid.add(listUsers);
//
//        add(panMid);
//
//        JPanel panChat = new JPanel(new GridLayout(2, 1));
//        JTextField fieldMessage = new JTextField("Message");
//        JButton btnSend = new JButton("Send");
//        btnSend.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                fieldChat.append(fieldMessage.getText() + String.format("%n"));
//
//            }
//        });
//        panChat.add(fieldMessage);
//        panChat.add(btnSend);
//
//        add(panChat, BorderLayout.SOUTH);
//
//        setVisible(true);
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_POSX = 1000;
    private static final int WINDOW_POSY = 250;


    JButton btnConnect = new JButton("Connect");
    JButton btnSend = new JButton("Send");
    JTextField ipField = new JTextField("ip address: ");
    JTextField portField = new JTextField("port: ");
    JTextField loginField = new JTextField("login: ");
    JTextField passwordField = new JTextField("password: ");
    JTextArea messageField = new JTextArea();
    JTextField textSendField = new JTextField();
    JPanel jPanelTop = new JPanel(new GridLayout(2, 3));
    JPanel jPanelBottom = new JPanel(new BorderLayout());
    JPanel jPanelUserList = new JPanel(new FlowLayout());

    private static final String FILENAME = "LOG.TXT";

    //region constructors
    public ChatWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setAlwaysOnTop(true);
        setTitle("Chat client");
        setResizable(false);

        jPanelTop.add(ipField);
        jPanelTop.add(portField);
        jPanelTop.add(loginField);
        jPanelTop.add(passwordField);
        jPanelTop.add(btnConnect);
        add(jPanelTop, BorderLayout.NORTH);

        add(messageField);
        String[] users = {"Pavel", "Ivan", "Mariia",
                "Daria", "Oleg", "John", "Kate"};
        JList<String> userList = new JList<>(users);
        jPanelUserList.add(userList);
        add(jPanelUserList, BorderLayout.EAST);
        jPanelBottom.add(textSendField, BorderLayout.CENTER);
        jPanelBottom.add(btnSend, BorderLayout.EAST);
        add(jPanelBottom, BorderLayout.SOUTH);


        messageField.setEditable(false);

        //region buttons listeners
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageField.setText(String.valueOf(readLogTFromFile()));
            }
        });

        textSendField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //System.out.println(e.getKeyChar());
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    btnSend.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + ": " + loginField.getText().substring(7) + ": " + textSendField.getText() + "\n";
                messageField.append(result);
                writeLogToFile(result);
                textSendField.setText("");
            }
        });
        //endregion

        setVisible(true);
    }
    //endregion

    //region i/o operations
    private void writeLogToFile(String data) {
        try (FileWriter writer = new FileWriter(ChatWindow.FILENAME, true); BufferedWriter bwr = new BufferedWriter(writer)) {
            bwr.write(data);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private StringBuffer readLogTFromFile() {
        StringBuffer stringBuffer = new StringBuffer();
        try (FileReader reader = new FileReader(ChatWindow.FILENAME); BufferedReader brr = new BufferedReader(reader)) {

            String line = brr.readLine();
            if (line == null || line.isBlank()) {
                System.out.println("Log is empty.");
                return stringBuffer.append("Log is empty.\n");
            }

            while (line != null) {
                //System.out.println(line);
                stringBuffer.append(line);
                stringBuffer.append("\n");
                line = brr.readLine();
            }

            return stringBuffer;

        } catch (IOException ioe) {
            System.out.println("Log file is not found: " + FILENAME);
        }
        return stringBuffer.append("Log file is not found: " + FILENAME + "\n");
    }
    //endregion
}
