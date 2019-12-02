/**
 * A symbol has a spelling, an integer tag to say what kind of symbol it is,
 * and a value (for numbers).  A list of the key symbols is provided.
 */
package MVC.modelo;

public class Symbol {

    String spelling;
    int kind;
    double value;

    Symbol(String s, int k, double v) {
        spelling = s;
        kind = k;
        value = v;
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
