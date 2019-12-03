/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public abstract class Tree {

    public static final int ERROR = 0, ID = 1, BRACKET = 2, ADD = 3, SUB = 4, MUL = 5, DIV = 6, POW = 7;

    
    static Tree build1(int kind, Tree node) {
        if (kind != BRACKET) {
            System.out.println("Error interno: tipo de árbol incorrecto");
            System.exit(1);
        }
        return new Bracket(node);
    }

    /**
     *
     * @param kind
     * @param node1
     * @param node2
     * @return
     */
    public static Tree build2(int kind, Tree node1, Tree node2) {
        switch (kind) {
            case ADD:
                return new Add(node1, node2);
            case SUB:
                return new Sub(node1, node2);
            case MUL:
                return new Mul(node1, node2);
            case DIV:
                return new Div(node1, node2);
            case POW:
                return new Pow(node1, node2);
            default:
                System.out.println("Error interno: tipo de árbol incorrecto");
                System.exit(1);
        }
        return null;
    }


    abstract int getKind();

    abstract int getPrefix();

    abstract int getPostfix();

   

    

  
    public static class Add extends Tree {

        Tree left, right;

        Add(Tree l, Tree r) {
            left = l;
            right = r;
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

        Sub(Tree l, Tree r) {
            left = l;
            right = r;
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

        Mul(Tree l, Tree r) {
            left = l;
            right = r;
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

        Div(Tree l, Tree r) {
            left = l;
            right = r;
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

        Pow(Tree l, Tree r) {
            left = l;
            right = r;
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
