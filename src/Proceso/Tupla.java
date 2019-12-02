/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proceso;

/**
 *
 * @author alexg
 */
public class Tupla {
    private String  valor;
    private boolean operador;
    
    public Tupla()
    {
        operador = false;
        valor= "";
        
    }
    
    public Tupla(String va , boolean  op)
    {
        valor = va;
        operador = op;
    }
    
    public void setValor(String va)
    {
        this.valor = va;
    }
    
    public void setOperador(boolean op)
    {
        this.operador = op;
    }
    
    public String getValor()
    {
        return valor;
    }
    
    public boolean getOperator()
    {
        return operador;
    }
}
