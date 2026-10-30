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

## Licencia

Código de ejemplo de uso libre con fines educativos.
