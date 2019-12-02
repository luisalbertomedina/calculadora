/**
 * Class Interpretador- evalúa la expresión en el árbol
 */
package MVC.modelo;

public class Interprete {

    public Symbol[] symbols;

    public Interprete() {
    }

    public double interpret(Program program) {
        symbols = program.symbols;
        return eval(program.tree);
    }

    private double eval(Tree tree) {
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
        return symbols[t.ref].value;
    }

    private double eval(Tree.Bracket t) {
        return eval(t.expr);
    }

    private double eval(Tree.Add t) {
        return eval(t.left) + eval(t.right);
    }

    private double eval(Tree.Sub t) {
        return eval(t.left) - eval(t.right);
    }

    private double eval(Tree.Mul t) {
        return eval(t.left) * eval(t.right);
    }

    private double eval(Tree.Div t) {
        return eval(t.left) / eval(t.right);
    }

    private double eval(Tree.Pow t) {
        return Math.pow(eval(t.left), eval(t.right));
    }
}
