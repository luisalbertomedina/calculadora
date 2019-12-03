/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import modelo.Modelo;
import vista.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorVista implements ActionListener {

    private vista vista;
    private Modelo modelo;

    public ControladorVista(vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.btnEvaluar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("calcular");
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            evaluar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void evaluar() throws Exception {
        String expresion = vista.txtExpresion.getText();
        vista.txtResultado.setText(String.valueOf(modelo.calc(expresion)));
    }
}
