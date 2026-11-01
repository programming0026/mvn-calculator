# Calculadora

Calculadora de escritorio con interfaz Swing que evalúa expresiones completas, con paréntesis y precedencia de operadores.

## Características

- Suma, resta, multiplicación y división.
- Paréntesis y números negativos.
- Evaluación con dos pilas (algoritmo de Dijkstra), sin usar `eval` ni motores de scripts.
- Control de la división por cero.

## Requisitos

- JDK 21 y Maven 3.9 o superior.

## Compilar y ejecutar

```bash
mvn compile exec:java
```

## JAR ejecutable (fat jar)

```bash
mvn clean package
java -jar target/mvn-calculator-1.0.0-SNAPSHOT-launcher.jar
```

El archivo `-launcher.jar` incluye todas las dependencias.

## Estructura

```text
src/main/java/com/juniverse/calculator/
├── Main.java                  # arranque
├── Calculator.java            # ventana Swing: visor y teclado
└── ExpressionEvaluator.java   # evalúa la expresión (dos pilas), sin depender de la interfaz
```

La lógica de cálculo está separada de la ventana (principio de responsabilidad única), por lo que se prueba sin abrir ninguna interfaz gráfica.

## Pruebas

```bash
mvn test
```

## Licencia

Código de ejemplo de uso libre con fines educativos.
