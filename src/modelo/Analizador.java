/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.util.*;

public class Analizador {

    private Rule[] rules;
    private Token[] tokens;
    private Simbolo[] symbols;
    private int next, nextKind;
    private Stack nodes;

    
    public Analizador() {
        Grammar grammar = new Grammar();
        rules = grammar.rules;
    }

   
    public Program analizar(Program program) throws Exception {
        tokens = program.tokens;
        symbols = program.simbolos;
        next = 0;
        nextKind = symbols[tokens[next].ref].tipooperador;
        nodes = new Stack();
        analizar(rules[0]);
        if (nodes.size() != 1) {
            throw new Exception("error en el nodo");
            
        }
        program.tree = (Tree) nodes.pop();
        return program;
    }


    private void analizar(Rule rule) throws Exception {
        switch (rule.getKind()) {
            case Rule.THEN:
                analizar((Rule.Then) rule);
                break;
            case Rule.OR:
                analizar((Rule.Or) rule);
                break;
            case Rule.EMPTY:
                break;
            case Rule.SKIP:
                analizar((Rule.Skip) rule);
                break;
            case Rule.ACCEPT:
                analizar((Rule.Accept) rule);
                break;
            case Rule.BUILD:
                analizar((Rule.Build) rule);
                break;
        }
    }

    private void analizar(Rule.Then r) throws Exception {
        analizar(rules[r.left]);
        analizar(rules[r.right]);
    }

    private void analizar(Rule.Or r) throws Exception {
        if (startsWith(rules[r.left], nextKind)) {
            analizar(rules[r.left]);
        } else {
            analizar(rules[r.right]);
        }
    }

    private void analizar(Rule.Skip r) throws Exception {
        if (nextKind == r.symbolKind) {
            next++;
            if (nextKind != Simbolo.END) {
                nextKind = symbols[tokens[next].ref].tipooperador;
            }
        } else {
            mensaje(tokens[next].start, nextKind, r.symbolKind);
        }
    }

    private void analizar(Rule.Accept r) throws Exception {
        if (nextKind == r.symbolKind) {
            nodes.add(new Tree.Id(tokens[next].ref, tokens[next].start));
            next++;
            nextKind = symbols[tokens[next].ref].tipooperador;
        } else {
            mensaje(tokens[next].start, nextKind, r.symbolKind);
        }
    }

    private void analizar(Rule.Build r) throws Exception {
        Tree node1, node2;
        if (r.size == 1) {
            node1 = (Tree) nodes.pop();
            nodes.add(Tree.build1(r.kind, node1));
        } else if (r.size == 2) {
            node2 = (Tree) nodes.pop();
            node1 = (Tree) nodes.pop();
            nodes.add(Tree.build2(r.kind, node1, node2));
        } else {
            throw new Exception("error en el tamaño del nodo");
        }
    }

    private boolean startsWith(Rule r, int symbolKind) {
        int ruleKind = r.getKind();
        boolean result;
        switch (ruleKind) {
            case Rule.THEN: {
                Rule.Then rt = (Rule.Then) r;
                result = startsWith(rules[rt.left], symbolKind);
            }
            break;
            case Rule.SKIP: {
                Rule.Skip rk = (Rule.Skip) r;
                result = rk.symbolKind == symbolKind;
            }
            break;
            case Rule.ACCEPT: {
                Rule.Accept rs = (Rule.Accept) r;
                result = rs.symbolKind == symbolKind;
            }
            break;
            default:
                result = false;
                break;
        }
        return result;
    }

    private void mensaje(int pos, int found, int expecting) throws Exception {
        System.out.println("error encontrado en la posicion " + pos);
        String message;
        if (found == Simbolo.BAD_CHAR) {
            message = "Error Léxico";
        } 
   
        else {
            int n = -1;
            for (int i = 0; i < Simbolo.keys.length; i++) {
                if (expecting == Simbolo.keys[i].tipooperador) {
                    n = i;
                }
            }
            message = "Error Sintáctico " ;
        }
        throw new Exception(message);
    }
}
