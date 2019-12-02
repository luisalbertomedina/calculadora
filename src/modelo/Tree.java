/**
 * The Tree class provides a number of kinds of tree node, Tree.Id,
 * Tree.Add etc for representing parse trees.  Each kind of node has a kind tag, a
 * prefix number, and a postfix number associated with it.  The kind tag,
 * obtained by getKind(), is an integer allowing code to decide efficiently what
 * kind of Tree node it is looking at, e.g. using a switch.  The prefix and
 * postfix numbers help to find the range of source text corresponding to a tree
 * without explicit range information in every node.
 *
 * The prefix number is the number of tokens in the source to the left of the
 * construct, and similarly for the postfix number.  For the "( _ )"
 * bracketed-expression construct, both prefix and postfix are 1 because there is
 * 1 key "(" to the left and 1 key ")" to the right.
 *
 * The build methods allow a tree node of a given kind to be built.
 */
package MVC.modelo;

public abstract class Tree {

    public static final int ERROR = 0, ID = 1, BRACKET = 2, ADD = 3, SUB = 4, MUL = 5, DIV = 6, POW = 7;

    /**
     * Build a tree with one subtree, given its kind tag
     */
    static Tree build1(int kind, Tree node) {
        if (kind != BRACKET) {
            System.out.println("Error interno: tipo de árbol incorrecto");
            System.exit(1);
        }
        return new Bracket(node);
    }

    /**
     * Build a tree with two subtrees, given its kind tag
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

    /*
      Ideally, kind, prefix, postfix would be static constants in each node
      class. However, Java's overriding rules don't work right for static
      constants, so we make them into methods instead. Further methods could be
      added to do source range calculations and help with other generic
      processing on Tree nodes without knowing which kind is being dealt with
      at each stage.
     */
    abstract int getKind();

    abstract int getPrefix();

    abstract int getPostfix();

    /**
     * The different kinds of tree are static inner classes of Tree just to keep
     * everything neatly together in one file.
     *
     * An error node represents a scan or parse error. It contains an error
     * message and the source range which the error covers as start and end
     * positions in the tokens array. (Not used at present.)
     */
    public static class Error extends Tree {

        String message;
        int start, end;

        Error(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        int getKind() {
            return ERROR;
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

    /**
     * An id node represents a number as a leaf node. It contains an integer
     * reference to the relevant symbol table entry and a start position in the
     * Source.
     */
    public static class Id extends Tree {

        int ref, start;

        Id(int r, int s) {
            ref = r;
            start = s;
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

    /**
     * A bracket node represents a bracketed subexpression. In most compilers,
     * bracket nodes would not be created, but in a compiler like this one which
     * has no explicit source range info in tree nodes, they are needed in order
     * to calculate source ranges.
     */
    public static class Bracket extends Tree {

        Tree expr;

        Bracket(Tree e) {
            expr = e;
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

    /**
     * An add node represents the sum of the two left and right subnodes. The
     * same goes for the other arithmetic nodes.
     */
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
