package com.juniverse.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/** Pruebas del evaluador de expresiones. */
class ExpressionEvaluatorTest {

    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    @ParameterizedTest
    @CsvSource({
        "2+3, 5",
        "10-4, 6",
        "6*7, 42",
        "9/4, 2.25",
        "2+3*4, 14",
        "(2+3)*4, 20",
        "10-2-3, 5",
        "2*(3+4)*5, 70",
        "1.5+2.5, 4",
        "' 2 + 3 ', 5"
    })
    void evaluaExpresionesConPrecedenciaYParentesis(String expression, double expected) {
        assertEquals(expected, evaluator.evaluate(expression), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
        "-5+2, -3",
        "(-2)*3, -6",
        "4*-2, -8",
        "3+(-1), 2"
    })
    void aceptaNumerosNegativos(String expression, double expected) {
        assertEquals(expected, evaluator.evaluate(expression), 1e-9);
    }

    @Test
    void dividirEntreCeroLanzaArithmeticException() {
        ArithmeticException error = assertThrows(ArithmeticException.class, () -> evaluator.evaluate("5/0"));
        assertEquals("No se puede dividir por cero", error.getMessage());
    }

    @Test
    void divisionEntreCeroDentroDeUnaExpresion() {
        assertThrows(ArithmeticException.class, () -> evaluator.evaluate("1+(2/(3-3))"));
    }
}
