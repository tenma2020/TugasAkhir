package org.example;

import javax.swing.*;


public class entryWindow extends JFrame {
    private JPanel panel1;
    private JTextField textField;
    private JButton setujuButton;
    private JPanel inputEntry;
    private JTextArea entryNotFoundTextArea;


    public entryWindow() {
        entryNotFoundTextArea.setVisible(false);
        setujuButton.addActionListener(e -> {

            while (textField.getText().isEmpty()) {
                if (!textField.getText().isEmpty()) {
                    entryNotFoundTextArea.setVisible(true);
                } else {
                    Main.timeHour = new int[Integer.parseInt(textField.getText())];
                    Main.material = new int[Integer.parseInt(textField.getText())];
                    Main.profit = new long[Integer.parseInt(textField.getText())];
                    System.out.println(textField.getText());
                }

            }

        });
    }
    public static void main(String[] args) {
        entryWindow entry = new entryWindow();
        entry.setContentPane(entry.panel1);
        entry.setVisible(true);
        entry.setTitle("Profit Counter");
        entry.setSize(275, 150);
        entry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }


}
