/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author unifvbinda
 */
public class Real extends Moedas{
   
    public Real() {
        super();
    }

    public Real(double saldo) {
        super(saldo);
    }

    

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    

    
    
    
    
    
    
}
