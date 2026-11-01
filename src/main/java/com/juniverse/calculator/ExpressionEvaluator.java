package com.juniverse.calculator;

import java.util.Stack;

/**
 * Evalúa expresiones aritméticas con paréntesis, precedencia de operadores y números negativos, con el algoritmo de
 * dos pilas de Dijkstra (una de valores y otra de operadores). No depende de la interfaz gráfica.
 */
public class ExpressionEvaluator {

    /**
     * Evalúa la expresión con dos pilas (valores y operadores).
     * @param expression expresión con + - * / y paréntesis
     * @return resultado
     * @throws ArithmeticException si hay una división por cero
     */
    public double evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') {
                continue;
            }
            if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.'
                    || (tokens[i] == '-' && (i == 0 || tokens[i - 1] == '(' || isOperator(tokens[i - 1])))) {
                StringBuilder sbuf = new StringBuilder();
                if (tokens[i] == '-' && (i == 0 || tokens[i - 1] == '(' || isOperator(tokens[i - 1]))) {
                    sbuf.append('-');
                    i++;
                }
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                    sbuf.append(tokens[i++]);
                }
                values.push(Double.valueOf(sbuf.toString()));
                i--;
            } else if (tokens[i] == '(') {
                ops.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
            } else if (isOperator(tokens[i])) {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(tokens[i]);
            }
        }

        while (!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    /** Indica si el carácter es uno de + - * /. */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /** Indica si {@code op2} (en la pila) tiene igual o mayor precedencia que {@code op1} y debe aplicarse primero. */
    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        return !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'));
    }

    /**
     * Aplica un operador.
     * @param op operador
     * @param b segundo operando (sale primero de la pila)
     * @param a primer operando
     * @return resultado
     */
    private static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case '*' -> {
                return a * b;
            }
            case '/' -> {
                if (b == 0) {
                    throw new ArithmeticException("No se puede dividir por cero");
                }
                return a / b;
            }
        }
        return 0;
    }
}
