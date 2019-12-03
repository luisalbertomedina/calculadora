
package modelo;


public class Simbolo {
    String operador;            //Símbolo del operador
    int tipooperador; 		//Identificador para el tipo de símbolo
    double valor;		//Valor del símbolo

    Simbolo(String oper, int tipooper, double val) {
        this.operador = oper;
        this.tipooperador = tipooper;
        this.valor = val;
    }

    public static final int PLUS = 0, MINUS = 1, TIMES = 2, SLASH = 3, HAT = 4, OPEN = 5, CLOSE = 6, END = 7,
            NUMBER = 8, BAD_CHAR = 9, BAD_NUMBER = 10, KEY_OR_BAD_CHAR = 11;

    public static Simbolo[] keys = {
        new Simbolo("+", PLUS, 0.0),
        new Simbolo("-", MINUS, 0.0),
        new Simbolo("*", TIMES, 0.0),
        new Simbolo("/", SLASH, 0.0),
        new Simbolo("^", HAT, 0.0),
        new Simbolo("(", OPEN, 0.0),
        new Simbolo(")", CLOSE, 0.0),
        new Simbolo("", END, 0.0)
    };
}
