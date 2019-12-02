/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;


import MVC.modelo.modelo;
import MVC.vista.vista;

/**
 *
 * @author alexg
 */
public class ControladorPrincipal{
    public static void main(String[] args) {
        modelo mode =new modelo();
        vista view =new vista();
        ControladorVista ctrl = new ControladorVista(view, mode);
        ctrl.iniciar();
        view.setVisible(true);
        
    }
}
