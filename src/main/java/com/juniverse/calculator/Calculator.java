package com.juniverse.calculator;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.logging.Logger;
import java.util.logging.Level;
/**
 * Calculadora con interfaz Swing que evalúa expresiones con paréntesis, precedencia de operadores y números negativos
 * (algoritmo de dos pilas de Dijkstra).
 *
 * @author linux
 */
public class Calculator extends JFrame implements ActionListener {
    private static final Logger LOGGER = AppLogger.get(Calculator.class);


    private final JTextField display;
    private final JPanel panel;
    private final String[] buttons = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", ".", "=", "+",
        "C", "(", ")"
    };
    private final JButton[] buttonObjects = new JButton[buttons.length];
    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    /** Crea la ventana con el visor y el teclado. */
    public Calculator() {
        setTitle("Calculadora");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        // Crear el display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 18));
        add(display, BorderLayout.NORTH);

        // Crear el panel de botones
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));
        for (int i = 0; i < buttons.length; i++) {
            buttonObjects[i] = new JButton(buttons[i]);
            buttonObjects[i].setFont(new Font("Arial", Font.BOLD, 18));
            buttonObjects[i].addActionListener(this);
            panel.add(buttonObjects[i]);
        }

        add(panel, BorderLayout.CENTER);
    }

    /** Atiende los botones: "=" evalúa, "C" borra y el resto se agrega al visor. */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command.charAt(0)) {
            case '=' -> {
                try {
                    display.setText(evaluate(display.getText()));
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                    display.setText("Error");
                }
            }
            case 'C' ->
                display.setText("");
            default -> {
                if ("Error".equals(display.getText())) {
                    display.setText(""); // tras un error se empieza de nuevo
                }
                display.setText(display.getText() + command);
            }
        }
    }

    /**
     * Evalúa la expresión y devuelve el resultado como texto.
     * @param expression expresión, por ejemplo "(2+3)*4"
     */
    private String evaluate(String expression) {
        return Double.toString(evaluator.evaluate(expression));
    }
}
