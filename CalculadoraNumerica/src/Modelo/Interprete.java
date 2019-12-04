/**
 * Clase Interpretador, evalúa la expresión en el árbol
 */
package Modelo;

public class Interprete {

    public Simbolo[] simbolos;

    public Interprete() {
    }

    public double interpret(Program program) {
        simbolos = program.simbolos;
        return evaluate(program.tree);
    }

    private double evaluate(Tree tree) {
        switch (tree.getKind()) {
            case Tree.ID:
                return eval((Tree.Id) tree);
            case Tree.BRACKET:
                return eval((Tree.Bracket) tree);
            case Tree.ADD:
                return eval((Tree.Add) tree);
            case Tree.SUB:
                return eval((Tree.Sub) tree);
            case Tree.MUL:
                return eval((Tree.Mul) tree);
            case Tree.DIV:
                return eval((Tree.Div) tree);
            case Tree.POW:
                return eval((Tree.Pow) tree);
            default:
                System.out.println("Error interno: tipo de nodo de árbol desconocido");
                System.exit(1);
                return 0;
        }
    }

    private double eval(Tree.Id t) {
        return simbolos[t.ref].valor;
    }

    private double eval(Tree.Bracket t) {
        return evaluate(t.expresion);
    }

    private double eval(Tree.Add t) {
        return evaluate(t.left) + evaluate(t.right);
    }

    private double eval(Tree.Sub t) {
        return evaluate(t.left) - evaluate(t.right);
    }

    private double eval(Tree.Mul t) {
        return evaluate(t.left) * evaluate(t.right);
    }

    private double eval(Tree.Div t) {
        return evaluate(t.left) / evaluate(t.right);
    }

    private double eval(Tree.Pow t) {
        return Math.pow(evaluate(t.left), evaluate(t.right));
    }
}
