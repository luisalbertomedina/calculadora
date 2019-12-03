/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import modelo.Modelo;
import vista.vista;


/**
 *
 * @author alexg
 */
public class ControladorPrincipal{
    public static void main(String[] args) {
        Modelo modelo =new Modelo();
        vista vista =new vista();
        ControladorVista ctrl = new ControladorVista(vista, modelo);
        ctrl.iniciar();
        vista.setVisible(true);
        
    }
}
