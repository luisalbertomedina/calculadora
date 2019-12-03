/**
 * Provide a number of record classes for storing BNF grammar rules.  Each has
 * an integer kind for use in switches (implemented as a method because Java does
 * not override constants).
 */
package modelo;

public abstract class Rule {

    public static final int EMPTY = 0, SKIP = 1, ACCEPT = 2, BUILD = 3, THEN = 4, OR = 5;

    abstract int getKind();

  
    public static class Empty extends Rule {

        Empty() {
        }

        @Override
        int getKind() {
            return EMPTY;
        }
    }

  
    public static class Skip extends Rule {

        int symbolKind;

        Skip(int k) {
            symbolKind = k;
        }

        @Override
        int getKind() {
            return SKIP;
        }
    }

    
    public static class Accept extends Rule {

        int symbolKind;

        Accept(int k) {
            symbolKind = k;
        }

        @Override
        int getKind() {
            return ACCEPT;
        }
    }

   
    public static class Build extends Rule {

        int kind, size;

        Build(int k, int n) {
            kind = k;
            size = n;
        }

        @Override
        int getKind() {
            return BUILD;
        }
    }

   
    public static class Then extends Rule {

        int left, right;

        Then(int l, int r) {
            left = l;
            right = r;
        }

        @Override
        int getKind() {
            return THEN;
        }
    }


    public static class Or extends Rule {

        int left, right;

        Or(int l, int r) {
            left = l;
            right = r;
        }

        @Override
        int getKind() {
            return OR;
        }
    }
}
