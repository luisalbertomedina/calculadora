/**
 * Un token consiste en la posición del recurso y la referencia al símbolo
 * (que contiene el string del operador Ej. "-" y el tipo E. 1). Los tipos de símbolos son constantes.
 */
package MVC.modelo;

public class Token {

    int start, ref;

    Token(int inicio, int r) {
        start = inicio;
        ref = r;
    }
}
