/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proceso;

import java.util.LinkedList;

/**
 *
 * @author alexg
 */
public class calcular {
    public calcular() {
    }

    public double calcular(String Expre) throws Exception
    {
        double resultado=0;
        String aux = Expre;
        Comprobador scanner = new Comprobador();
        aux = scanner.Scan(aux);        
        if(aux.equals("P")||aux.equals("O"))
        {     
            if(aux.equals("P"))
              throw new Exception("Verificar los paréntesis. Gracias.");
            else
              throw new Exception("Error Sintáctico");
        }else
        {
            
            Expresion e;
            e = new Expresion(aux);
            LinkedList exp = e.CompletoPrefija();
            if((exp.size()!=0)&&(exp.size()!=2))
            {
                Arbol ar = new Arbol(exp);
                resultado = ar.Evaluar(); 
            }else
                throw new Exception("Verifique la expresión. Gracias.");
        }
        return resultado;   
    }
}
