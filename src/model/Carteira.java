/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author unifvbinda
 */
public class Carteira {
    private ArrayList<Moedas> moedas;
    
    public Carteira(){
        this.moedas = new ArrayList<>();
        
    }

    public Carteira(ArrayList<Moedas> moedas) {
        this.moedas = moedas;
    }
    
    

    public ArrayList<Moedas> getMoedas() {
        return moedas;
    }

    public void setMoedas(ArrayList<Moedas> moedas) {
        this.moedas = moedas;
    }
    
    
}
