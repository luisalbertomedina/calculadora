
package Controlador;


import Modelo.Modelo;
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
