/**
 * Convierte la expresión en tokens.
 * 
 * Lenguaje:
 * program = (' ' | '\n' | token)*
 * token   = number | '+' | '-' | '*' | '/' | '^' | '(' | ')'
 * number = digit+ ('.' digit+)?
 */
package Modelo;

import java.util.*;

public class Scanner {

    /**
     * 
     * Crea un array de tokens y una tabla a partir de la expresión
     *
     * @param program
     * @return
     */
    public Program scan(Program program) {
       // program.source = prepare(program.source);
        PreToken[] toks = split(program.source);
        symbolize(program, toks);
        return program;
    }

    
   

    /**
     * Agrega salto de línea al final de cada línea
     */
 

    /**
     * A pre-token is a temporary data structure for holding tokens after
     * splitting, but before symbolizing. A pre-token has start and end
     * positions in the source text, and a tentative kind tag, to be refined
     * later by symbolize().
     */
    private static class PreToken {

        int start, end, kind;

        PreToken(int s, int e, int k) {
            start = s;
            end = e;
            kind = k;
        }
    }

    /**
     * Split the prepared source text into pre-tokens. Key recognition is left
     * to the symbolize stage. (In a larger language, simple keys would be
     * recognized here, but keywords would be left to later.)
     */
    private PreToken[] split(String source) {
        Vector toks = new Vector();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case ' ':
                case '\n':
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    PreToken t = number(source, i);
                    toks.add(t);
                    i = t.end - 1;
                }
                break;
                default:
                    toks.add(new PreToken(i, i + 1, Simbolo.KEY_OR_BAD_CHAR));
                    break;
            }
        }
        toks.add(new PreToken(source.length(), source.length(), Simbolo.END));
        return (PreToken[]) toks.toArray(new PreToken[0]);
    }

    /**
     * Get a number from the source text, starting at a given position
     */
    private PreToken number(String source, int i) {
        PreToken num;
        int j = i, n = source.length();
        while (j < n && Character.isDigit(source.charAt(j))) {
            j++;
        }
        if (j < n && source.charAt(j) == '.') {
            j++;
            if (j < n && Character.isDigit(source.charAt(j))) {
                while (j < n && Character.isDigit(source.charAt(j))) {
                    j++;
                }
                num = new PreToken(i, j, Simbolo.NUMBER);
            } else {
                num = new PreToken(i, j, Simbolo.BAD_NUMBER);
            }
        } else {
            num = new PreToken(i, j, Simbolo.NUMBER);
        }
        return num;
    }

    /**
     * Convert the pre-tokens into tokens which refer to a symbol table
     */
    private void symbolize(Program program, PreToken[] toks) {
        String source = program.source;
        HashMap table = new HashMap();
        Vector syms = new Vector();
        Token[] tokens = new Token[toks.length];
        for (int i = 0; i < Simbolo.keys.length; i++) {
            syms.add(Simbolo.keys[i]);
            table.put(Simbolo.keys[i].operador, new Integer(i));
        }
        for (int i = 0; i < toks.length; i++) {
            PreToken tok = toks[i];
            String spelling;
            Integer ref;
            // s.substring() does not cope with the empty substring at the end of s
            if (tok.start == source.length()) {
                spelling = "";
            } else {
                spelling = source.substring(tok.start, tok.end);
            }

            ref = (Integer) table.get(spelling);
            if (ref == null) {
                int r = syms.size();
                if (tok.kind == Simbolo.NUMBER) {
                    double val = Double.parseDouble(spelling);
                    syms.add(new Simbolo(spelling, tok.kind, val));
                } else if (tok.kind == Simbolo.KEY_OR_BAD_CHAR) {
                    syms.add(new Simbolo(spelling, Simbolo.BAD_CHAR, 0.0));
                } else {
                    syms.add(new Simbolo(spelling, tok.kind, 0.0));
                }
                table.put(spelling, new Integer(r));
                tokens[i] = new Token(tok.start, r);
            } else {
                tokens[i] = new Token(tok.start, ref.intValue());
            }
        }
        program.simbolos = (Simbolo[]) syms.toArray(new Simbolo[0]);
        program.tokens = tokens;
    }
}
