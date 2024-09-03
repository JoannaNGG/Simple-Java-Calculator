/*
 * Author: Joanna NG
 * 
 * Idea from:
 * https://www.youtube.com/watch?v=dfhmTyRTCSQ&t=412s
 * with tweaks.
 */

// Import toolkits for graphics, UI, and GUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField text; // Field to hold what is typed
    JButton[] nButtons = new JButton[10]; // To hold 0 - 9
    JButton[] fButtons = new JButton[9]; // For the functions

    // Declaring functions
    JButton add, sub, mult, div;
    JButton dec, eq, del, clr, neg;
    JPanel panel;

    Font myFont = new Font("Monospaced", Font.BOLD, 40); // Increased font size

    double num1 = 0, num2 = 0, result = 0; // For the calculation
    char operator; // Holds the current operator

    Calculator() {
        // Instantiate the frame with a larger size
        frame = new JFrame("My Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800); // Increased size of the frame
        frame.setLayout(null);

        // Instantiate the text field with a larger size
        text = new JTextField();
        text.setBounds(50, 25, 500, 75); // Increased width and height
        text.setFont(myFont);
        text.setEditable(false); // Disable typing freely inside the text field

        // Instantiate function buttons
        add = new JButton("+");
        sub = new JButton("-");
        mult = new JButton("*");
        div = new JButton("/");
        dec = new JButton(".");
        eq = new JButton("=");
        del = new JButton("Del");
        clr = new JButton("Clr");
        neg = new JButton("(-)");

        fButtons[0] = add;
        fButtons[1] = sub;
        fButtons[2] = mult;
        fButtons[3] = div;
        fButtons[4] = dec;
        fButtons[5] = eq;
        fButtons[6] = del;
        fButtons[7] = clr;
        fButtons[8] = neg;

        for (int i = 0; i < 9; i++) {
            fButtons[i].addActionListener(this);
            fButtons[i].setFont(myFont);
            fButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            nButtons[i] = new JButton(String.valueOf(i));
            nButtons[i].addActionListener(this);
            nButtons[i].setFont(myFont);
            nButtons[i].setFocusable(false);
        }

        // Adjust the size and positioning of the buttons
        neg.setBounds(50, 600, 150, 75); // Larger button
        del.setBounds(225, 600, 150, 75); // Larger button
        clr.setBounds(400, 600, 150, 75); // Larger button

        panel = new JPanel();
        panel.setBounds(50, 125, 500, 450); // Larger panel size
        panel.setLayout(new GridLayout(4, 4, 15, 15)); // Increased gaps
        panel.setBackground(Color.PINK);

        panel.add(nButtons[1]);
        panel.add(nButtons[2]);
        panel.add(nButtons[3]);
        panel.add(add);

        panel.add(nButtons[4]);
        panel.add(nButtons[5]);
        panel.add(nButtons[6]);
        panel.add(sub);

        panel.add(nButtons[7]);
        panel.add(nButtons[8]);
        panel.add(nButtons[9]);
        panel.add(mult);
        panel.add(dec);

        panel.add(nButtons[0]);
        panel.add(eq);
        panel.add(div);

        frame.add(panel);
        frame.add(neg);
        frame.add(del);
        frame.add(clr);
        frame.add(text);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == nButtons[i]) {
                text.setText(text.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == dec) {
            if (!text.getText().contains(".")) {
                text.setText(text.getText().concat("."));
            }
        }

        if (e.getSource() == add) {
            num1 = Double.parseDouble(text.getText());
            operator = '+';
            text.setText(text.getText().concat(" + "));
        }

        if (e.getSource() == sub) {
            num1 = Double.parseDouble(text.getText());
            operator = '-';
            text.setText(text.getText().concat(" - "));
        }

        if (e.getSource() == mult) {
            num1 = Double.parseDouble(text.getText());
            operator = '*';
            text.setText(text.getText().concat(" * "));
        }

        if (e.getSource() == div) {
            num1 = Double.parseDouble(text.getText());
            operator = '/';
            text.setText(text.getText().concat(" / "));
        }

        if (e.getSource() == eq) {
            String expression = text.getText();
            String[] parts = expression.split("[" + operator + "]");
            if (parts.length == 2) {
                num2 = Double.parseDouble(parts[1].trim());

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                }
                text.setText(String.valueOf(result));
                num1 = result; // Continue calculating with the previous result
            }
        }

        if (e.getSource() == clr) {
            text.setText("");
        }

        if (e.getSource() == del) {
            String s = text.getText();
            if (!s.isEmpty()) {
                text.setText(s.substring(0, s.length() - 1));
            }
        }

        if (e.getSource() == neg) {
            if (!text.getText().isEmpty()) {
                double temp = Double.parseDouble(text.getText());
                temp *= -1;
                text.setText(String.valueOf(temp));
            }
        }
    }
}
