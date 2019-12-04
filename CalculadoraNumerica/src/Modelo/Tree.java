
package Modelo;

public abstract class Tree {

    public static final int ERROR = 0, ID = 1, BRACKET = 2, ADD = 3, SUB = 4, MUL = 5, DIV = 6, POW = 7;

    
    static Tree firstBuild(int kind, Tree node) {
        if (kind != BRACKET) {
            System.out.println("Error interno: tipo de árbol incorrecto");
            System.exit(1);
        }
        return new Bracket(node);
    }

    
    public static Tree secondBuild(int kind, Tree firstNode, Tree secondNode) {
        switch (kind) {
            case ADD:
                return new Add(firstNode, secondNode);
            case SUB:
                return new Sub(firstNode, secondNode);
            case MUL:
                return new Mul(firstNode, secondNode);
            case DIV:
                return new Div(firstNode, secondNode);
            case POW:
                return new Pow(firstNode, secondNode);
            default:
                System.out.println("Error interno: tipo de árbol incorrecto");
                System.exit(1);
        }
        return null;
    }

    
    abstract int getKind();

    abstract int getPrefix();

    abstract int getPostfix();

  

    
    public static class Id extends Tree {

        int ref, start;

        Id(int ref, int start) {
            this.ref = ref;
            this.start = start;
        }

        @Override
        int getKind() {
            return ID;
        }

        @Override
        int getPrefix() {
            return 0;
        }

        @Override
        int getPostfix() {
            return 0;
        }
    }

    
    public static class Bracket extends Tree {

        Tree expresion;

        Bracket(Tree expresion) {
            this.expresion = expresion;
        }

        @Override
        int getKind() {
            return BRACKET;
        }

        @Override
        int getPrefix() {
            return 1;
        }

        @Override
        int getPostfix() {
            return 1;
        }
    }

    
    public static class Add extends Tree {

        Tree left, right;

        Add(Tree left, Tree right) {
            this.left = left;
            this.right = right;
        }

        @Override
        int getKind() {
            return ADD;
        }

        @Override
        int getPrefix() {
            return 0;
        }

        @Override
        int getPostfix() {
            return 0;
        }
    }

    public static class Sub extends Tree {

        Tree left, right;

        Sub(Tree left, Tree right) {
            this.left = left;
            this.right = right;
        }

        @Override
        int getKind() {
            return SUB;
        }

        @Override
        int getPrefix() {
            return 0;
        }

        @Override
        int getPostfix() {
            return 0;
        }
    }

    public static class Mul extends Tree {

        Tree left, right;

        Mul(Tree left, Tree right) {
            this.left = left;
           this.right = right;
        }

        @Override
        int getKind() {
            return MUL;
        }

        @Override
        int getPrefix() {
            return 0;
        }

        @Override
        int getPostfix() {
            return 0;
        }
    }

    public static class Div extends Tree {

        Tree left, right;

        Div(Tree left, Tree right) {
            this.left = left;
           this.right = right;
        }

        @Override
        int getKind() {
            return DIV;
        }

        @Override
        int getPrefix() {
            return 0;
        }

        @Override
        int getPostfix() {
            return 0;
        }
    }

    public static class Pow extends Tree {

        Tree left, right;

        Pow(Tree left, Tree right) {
            this.left = left;
           this.right = right;
        }

        @Override
        int getKind() {
            return POW;
        }

        @Override
        int getPrefix() {
            return 0;
        }

        @Override
        int getPostfix() {
            return 0;
        }
    }
}
