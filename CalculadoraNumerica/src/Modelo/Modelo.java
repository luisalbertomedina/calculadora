
package Modelo;


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
        program = Analizar.analizar(program);
        this.resultado=inter.interpret(program);

        return this.resultado;
    }
}
