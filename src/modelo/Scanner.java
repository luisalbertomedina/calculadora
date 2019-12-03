/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.*;

public class Scanner {

    /**
     * @param program
     * @return
     */
    public Program scan(Program program) {
        program.source = prepare(program.source);
        PreToken[] toks = split(program.source);
        symbolize(program, toks);
        return program;
    }

    
    private String prepare(String text) {
        String s1 = escapes(text);
        String s2 = lines(s1);
        String s3 = tabs(s2);
        return s3;
    }

 
    private String escapes(String text) {
        return text;
    }

  
    private String lines(String text) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\r') {
                if (i < text.length() - 1 && text.charAt(i + 1) == '\n') {
                    i++;
                }
                buffer.append('\n');
            } else {
                buffer.append(c);
            }
        }
        if (buffer.length() > 0 && buffer.charAt(buffer.length() - 1) != '\n') {
            buffer.append('\n');
        }
        return buffer.toString();
    }

  
    private String tabs(String text) {
        StringBuffer buffer = new StringBuffer();
        int col = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\t') {
                int skip = 8 - col % 8;
                for (int j = 0; j < skip; j++) {
                    buffer.append(' ');
                }
                col = col + skip;
            } else {
                buffer.append(c);
                if (c == '\n') {
                    col = 0;
                } else {
                    col++;
                }
            }
        }
        return buffer.toString();
    }


    private static class PreToken {

        int start, end, kind;

        PreToken(int s, int e, int k) {
            start = s;
            end = e;
            kind = k;
        }
    }


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
