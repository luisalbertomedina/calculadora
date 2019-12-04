/* Clase analizador - usa un intérprete para saber el resultado y si se acepta la cadena */
package Modelo;


import java.util.*;

public class Analizador {

    private Rule[] rules;
    private Token[] tokens;
    private Simbolo[] symbols;
    private int next, nextKind;
    private Stack nodes;

    /* Inicializa reglas gramaticales.*/
    public Analizador() {
        Grammar grammar = new Grammar();
        rules = grammar.rules;
    }

   
    public Program analizar (Program program) throws Exception {
        tokens = program.tokens;
        symbols = program.simbolos;
        next = 0;
        nextKind = symbols[tokens[next].ref].tipooperador;
        nodes = new Stack();
        analizar(rules[0]);
        if (nodes.size() != 1) {
            throw new Exception("Internal error: parse produces wrong no of nodes");
        }
        program.tree = (Tree) nodes.pop();
        return program;
    }

    
    private void analizar (Rule rule) throws Exception {
        switch (rule.getKind()) {
            case Rule.THEN:
                parse((Rule.Then) rule);
                break;
            case Rule.OR:
                parse((Rule.Or) rule);
                break;
            case Rule.EMPTY:
                break;
            case Rule.SKIP:
                parse((Rule.Skip) rule);
                break;
            case Rule.ACCEPT:
                parse((Rule.Accept) rule);
                break;
            case Rule.BUILD:
                parse((Rule.Build) rule);
                break;
        }
    }

    private void parse(Rule.Then rule) throws Exception {
        analizar(rules[rule.left]);
        analizar(rules[rule.right]);
    }

    private void parse(Rule.Or rule) throws Exception {
        if (startsWith(rules[rule.left], nextKind)) {
            analizar(rules[rule.left]);
        } else {
            analizar(rules[rule.right]);
        }
    }

    private void parse(Rule.Skip rule) throws Exception {
        if (nextKind == rule.symbolKind) {
            next++;
            if (nextKind != Simbolo.END) {
                nextKind = symbols[tokens[next].ref].tipooperador;
            }
        } else {
            report(tokens[next].start, nextKind, rule.symbolKind);
        }
    }

    private void parse(Rule.Accept rule) throws Exception {
        if (nextKind == rule.symbolKind) {
            nodes.add(new Tree.Id(tokens[next].ref, tokens[next].start));
            next++;
            nextKind = symbols[tokens[next].ref].tipooperador;
        } else {
            report(tokens[next].start, nextKind, rule.symbolKind);
        }
    }

    private void parse(Rule.Build rule) throws Exception {
        Tree firstNode, secondNode;
        if (rule.size == 1) {
            firstNode = (Tree) nodes.pop();
            nodes.add(Tree.firstBuild(rule.kind, firstNode));
        } else if (rule.size == 2) {
            secondNode = (Tree) nodes.pop();
            firstNode = (Tree) nodes.pop();
            nodes.add(Tree.secondBuild(rule.kind, firstNode, secondNode));
        } else {
            throw new Exception("Error interno: se produjo mal número de nodos");
        }
    }

    private boolean startsWith(Rule rule, int symbolKind) {
        int ruleKind = rule.getKind();
        boolean result;
        switch (ruleKind) {
            case Rule.THEN: {
                Rule.Then ruleThen = (Rule.Then) rule;
                result = startsWith(rules[ruleThen.left], symbolKind);
            }
            break;
            case Rule.SKIP: {
                Rule.Skip ruleSkip = (Rule.Skip) rule;
                result = ruleSkip.symbolKind == symbolKind;
            }
            break;
            case Rule.ACCEPT: {
                Rule.Accept ruleAccept = (Rule.Accept) rule;
                result = ruleAccept.symbolKind == symbolKind;
            }
            break;
            default:
                result = false;
                break;
        }
        return result;
    }

    private void report(int pos, int found, int expecting) throws Exception {
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
