/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
/*

    public static class Range {
        int start;
        int end;
    }

    public Range findRange(Tree tree) {
        System.out.println("Program.range() undefined");
        return new Range();
    }
    */
}
