/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author victo
 */
public class Taxa {
    double taxavenda;
    double taxacompra;

    public Taxa(double taxavenda, double taxacompra) {
        this.taxavenda = taxavenda;
        this.taxacompra = taxacompra;
    }
    
    

    public double getTaxavenda() {
        return taxavenda;
    }

    public void setTaxavenda(double taxavenda) {
        this.taxavenda = taxavenda;
    }

    public double getTaxacompra() {
        return taxacompra;
    }

    public void setTaxacompra(double taxacompra) {
        this.taxacompra = taxacompra;
    }
    
    
    
    
}
