/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import modelo.modelo;
import vista.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorVista implements ActionListener {

    private vista view;
    private modelo mod;

    public ControladorVista(vista view, modelo mod) {
        this.view = view;
        this.mod = mod;
        this.view.btnEvaluar.addActionListener(this);
    }

    public void iniciar() {
        view.setTitle("calcular");
        view.setLocationRelativeTo(null);
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
        String exp = view.txtExpresion.getText();
        view.txtResultado.setText(String.valueOf(mod.calc(exp)));
    }
}
