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
public class Id extends Tree{
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
