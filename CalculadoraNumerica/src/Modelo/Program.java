
package Modelo;

public class Program {

    String source;
    Token[] tokens;
    Simbolo[] simbolos;
    Tree tree;

    public Program(String text) {
        source = text;
        tokens = null;
        simbolos = null;
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
