/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author A17016364
 */
public class Bracket extends Tree {
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
