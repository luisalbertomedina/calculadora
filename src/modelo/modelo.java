/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class modelo {

   
    
    private double result;

    public void setResult(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public double calc(String expresion) throws Exception {
        Program program = new Program(expresion);
        Scanner scanner = new Scanner();
        Analizador Analizar = new Analizador();
        Interprete inter = new Interprete();

        program = scanner.scan(program);
        program = Analizar.parse(program);
        this.result=inter.interpret(program);

        return this.result;
    }
}
