/**
 * Un símbolo tiene una palabra asociada de acuerdo a un código establecido, un entero para identificar qué tipo de símbolo es,
 * y un valor (para números). Una lista de las llaves de los símbolos que se proveen.
 */
package modelo;

public class Symbol {

    String spelling;	//Símbolo del operador
    int kind; 			//Identificador para el tipo de símbolo
    double value;		//Valor del símbolo

    Symbol(String palabra, int tipo, double valor) {
        spelling = palabra;
        kind = tipo;
        value = valor;
    }

    public static final int PLUS = 0, MINUS = 1, TIMES = 2, SLASH = 3, HAT = 4, OPEN = 5, CLOSE = 6, END = 7,
            NUMBER = 8, BAD_CHAR = 9, BAD_NUMBER = 10, KEY_OR_BAD_CHAR = 11;

    public static Symbol[] keys = {
        new Symbol("+", PLUS, 0.0),
        new Symbol("-", MINUS, 0.0),
        new Symbol("*", TIMES, 0.0),
        new Symbol("/", SLASH, 0.0),
        new Symbol("^", HAT, 0.0),
        new Symbol("(", OPEN, 0.0),
        new Symbol(")", CLOSE, 0.0),
        new Symbol("", END, 0.0)
    };
}
