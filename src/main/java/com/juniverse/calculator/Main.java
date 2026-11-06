package com.juniverse.calculator;

import javax.swing.SwingUtilities;

/**
 * Abre la calculadora.
 *
 * @author Java Universe
 */
public class Main {

    /**
     * Punto de entrada.
     * @param args no se usan
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
