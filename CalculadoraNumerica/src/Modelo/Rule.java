
package Modelo;

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

        Skip(int kind) {
            symbolKind = kind;
        }

        @Override
        int getKind() {
            return SKIP;
        }
    }

    
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
