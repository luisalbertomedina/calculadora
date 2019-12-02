/**
 * A Program object holds a program or program component (file/module/class..).
 * It contains the source text, token array, symbol table, and parse tree.
 */
package modelo;

public class Program {

    String source;
    Token[] tokens;
    Symbol[] symbols;
    Tree tree;

    public Program(String text) {
        source = text;
        tokens = null;
        symbols = null;
        tree = null;
    }

    /**
     * Given a node in the parse tree, the corresponding range of source text
     * can be found. A Range is specified by two character positions.
     */
    public static class Range {

        int start;
        int end;
    }

    public Range findRange(Tree tree) {
        System.out.println("Program.range() undefined");
        return new Range();
    }
}
