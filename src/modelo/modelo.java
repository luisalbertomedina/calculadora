/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class Modelo {

   
    
    private double resultado;

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public double getResultado() {
        return resultado;
    }

    public double calc(String expresion) throws Exception {
        Program program = new Program(expresion);
        Scanner escaner = new Scanner();
        Analizador Analizar = new Analizador();
        Interprete inter = new Interprete();

        program = escaner.scan(program);
        program = Analizar.parse(program);
        this.resultado=inter.interpret(program);

        return this.resultado;
    }
}
