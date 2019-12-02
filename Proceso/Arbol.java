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
public class Arbol {
   

    private Nodo Raiz;
    private int n;

    public Arbol() {
        this.Raiz = null;
        this.n = 0;
    }

    public Arbol(LinkedList Exp) {
        Object aux = Exp.get(0);
        Tupla a = new Tupla();
        a.setValor(aux.toString());
        if (operador(aux)) {
            a.setOperador(true);
        } else {
            a.setOperador(false);
        }
        Nodo op, q = new Nodo(a);
        Pila p = new Pila();
        boolean antesOperando = false;
        Raiz = q;
        for (int i = 1; i < Exp.size(); i++) {
            Object aux2 = Exp.get(i);
            Tupla b = new Tupla(aux2.toString(), false);
            if (operador(aux2)) {
                b.setOperador(true);
            }
            op = new Nodo(b);
            if (antesOperando) {
                q = (Nodo) p.Sacar();
                q.setHD(op);
            } else {
                q.setHI(op);
                p.Poner(q);
            }
            q = op;
            if (operador(Exp.get(i))) {
                antesOperando = false;
            } else {
                antesOperando = true;
            }
        }

    }

    public boolean Hoja(Nodo R) {
        return ((R.getHI() == null) && (R.getHD() == null));
    }

    public void setRaiz(Nodo R) {
        if (R != null) {
            this.Raiz = R;
            n++;
        } else {
            this.Raiz = R;
        }
    }

    public Nodo getRaiz() {
        return this.Raiz;
    }

    public int getNodos() {
        return n;
    }

    private boolean operador(Object c) {
        char operadores[] = {'+', '-', '*', '/', '^'};
        boolean existe = false;
        char aux = c.toString().charAt(0);
        for (int i = 0; ((i < 5) && (!existe)); i++) {
            if (aux == operadores[i]) {
                existe = true;
            }
        }
        return existe;
    }

    public double Evaluar() {
        return Evaluar(Raiz);
    }

    private double Evaluar(Nodo R) {
        double res = 0;
        if (R == null) {
            return res = 0;
        } else {
            if (Hoja(R)) // Operando
            {
                String aux = R.getData().getValor();
                res = Double.parseDouble(aux);
            } else {
                double vizq = Evaluar(R.getHI());
                double vder = Evaluar(R.getHD());
                Character op = R.getData().getValor().charAt(0);
                switch (op) {
                    case '+':
                        res = vizq + vder;
                        break;
                    case '-':
                        res = vizq - vder;
                        break;
                    case '*':
                        res = vizq * vder;
                        break;
                    case '/':
                        res = vizq / vder;
                        break;
                    case '^':
                        res = Math.pow(vizq, vder);
                        break;
                    default:;
                        break;
                }
            }
        }
        return res;
    }

}
