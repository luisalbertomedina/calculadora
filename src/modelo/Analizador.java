/* Class Analizador - parses the Calc language using a simple interpretive
recursive descent parser. */
package modelo;


import java.util.*;

public class Analizador {

    private Rule[] rules;
    private Token[] tokens;
    private Simbolo[] symbols;
    private int next, nextKind;
    private Stack nodes;

    /**
     * Initialise the grammar rules.
     */
    public Analizador() {
        Grammar grammar = new Grammar();
        rules = grammar.rules;
    }

   
    public Program parse(Program program) throws Exception {
        tokens = program.tokens;
        symbols = program.simbolos;
        next = 0;
        nextKind = symbols[tokens[next].ref].tipooperador;
        nodes = new Stack();
        parse(rules[0]);
        if (nodes.size() != 1) {
            throw new Exception("Internal error: parse produces wrong no of nodes");
            //System.out.println("Internal error: parse produces wrong no of nodes");
            //System.exit(1);
        }
        program.tree = (Tree) nodes.pop();
        return program;
    }

    /**
     * This is the interpretive recursive descent parser
     */
    private void parse(Rule rule) throws Exception {
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

    private void parse(Rule.Then r) throws Exception {
        parse(rules[r.left]);
        parse(rules[r.right]);
    }

    private void parse(Rule.Or r) throws Exception {
        if (startsWith(rules[r.left], nextKind)) {
            parse(rules[r.left]);
        } else {
            parse(rules[r.right]);
        }
    }

    private void parse(Rule.Skip r) throws Exception {
        if (nextKind == r.symbolKind) {
            next++;
            if (nextKind != Simbolo.END) {
                nextKind = symbols[tokens[next].ref].tipooperador;
            }
        } else {
            report(tokens[next].start, nextKind, r.symbolKind);
        }
    }

    private void parse(Rule.Accept r) throws Exception {
        if (nextKind == r.symbolKind) {
            nodes.add(new Tree.Id(tokens[next].ref, tokens[next].start));
            next++;
            nextKind = symbols[tokens[next].ref].tipooperador;
        } else {
            report(tokens[next].start, nextKind, r.symbolKind);
        }
    }

    private void parse(Rule.Build r) throws Exception {
        Tree node1, node2;
        if (r.size == 1) {
            node1 = (Tree) nodes.pop();
            nodes.add(Tree.build1(r.kind, node1));
        } else if (r.size == 2) {
            node2 = (Tree) nodes.pop();
            node1 = (Tree) nodes.pop();
            nodes.add(Tree.build2(r.kind, node1, node2));
        } else {
            throw new Exception("Internal error: unimplemented node size");
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

    private void report(int pos, int found, int expecting) throws Exception {
        System.out.println("error encontrado en la posicion " + pos);
        String message;
        if (found == Simbolo.BAD_CHAR) {
            message = "Error Léxico";
        } 
      /*  
        else if (found == Symbol.BAD_NUMBER) {
            message = "Numero Incompleto";
        } else if (expecting == Symbol.END) {
            message = "Expecting end of input";
        } else if (expecting == Symbol.NUMBER) {
            message = "Se espera un Numero";
        } */
        else {
            int n = -1;
            for (int i = 0; i < Simbolo.keys.length; i++) {
                if (expecting == Simbolo.keys[i].tipooperador) {
                    n = i;
                }
            }
            message = "Error Sintáctico " ;//+ Symbol.keys[n].spelling;
        }
        throw new Exception(message);
    }
}
