/**
 * Provide a number of record classes for storing BNF grammar rules.  Each has
 * an integer kind for use in switches (implemented as a method because Java does
 * not override constants).
 */
package Modelo;

public abstract class Rule {

    public static final int EMPTY = 0, SKIP = 1, ACCEPT = 2, BUILD = 3, THEN = 4, OR = 5;

    abstract int getKind();

    /**
     * The empty rule (consume no input, build no tree node)
     */
    public static class Empty extends Rule {

        Empty() {
        }

        @Override
        int getKind() {
            return EMPTY;
        }
    }

    /**
     * The skip rule (move past a key token without building anything)
     */
    public static class Skip extends Rule {

        int symbolKind;

        Skip(int kind) {
            symbolKind = kind;
        }

        @Override
        int getKind() {
            return SKIP;
        }
    }

    /**
     * The accept rule (accept a token and build a leaf node from it)
     */
    public static class Accept extends Rule {

        int symbolKind;

        Accept(int kind) {
            symbolKind = kind;
        }

        @Override
        int getKind() {
            return ACCEPT;
        }
    }

    /**
     * The build rule (ignore input, build a compound tree node with n subnodes)
     */
    public static class Build extends Rule {

        int kind, size;

        Build(int kind, int size) {
            this.kind = kind;
            this.size = size;
        }

        @Override
        int getKind() {
            return BUILD;
        }
    }

    /**
     * The then rule (deal with the two subrules one after the other)
     */
    public static class Then extends Rule {

        int left, right;

        Then(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        int getKind() {
            return THEN;
        }
    }

    /**
     * The or rule (choose one of the two alternative subrules)
     */
    public static class Or extends Rule {

        int left, right;

        Or(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        int getKind() {
            return OR;
        }
    }
}
