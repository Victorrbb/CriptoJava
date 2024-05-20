/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author victo
 */
public class Cotacao {
    double cotacaobitcoin;
    double cotacaoethereum;
    double cotacaoripple;
    int id;

    public double getCotacaobitcoin() {
        return cotacaobitcoin;
    }

    public void setCotacaobitcoin(double cotacaobitcoin) {
        this.cotacaobitcoin = cotacaobitcoin;
    }

    public double getCotacaoethereum() {
        return cotacaoethereum;
    }

    public void setCotacaoethereum(double cotacaoethereum) {
        this.cotacaoethereum = cotacaoethereum;
    }

    public double getCotacaoripple() {
        return cotacaoripple;
    }

    public void setCotacaoripple(double cotacaoripple) {
        this.cotacaoripple = cotacaoripple;
    }

    public int getId() {
        return 1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cotacao(double cotacaobitcoin, double cotacaoethereum, double cotacaoripple, int id) {
        this.cotacaobitcoin = cotacaobitcoin;
        this.cotacaoethereum = cotacaoethereum;
        this.cotacaoripple = cotacaoripple;
        this.id = id;
    }

    public Cotacao(double cotacaobitcoin, double cotacaoethereum, double cotacaoripple) {
        this.cotacaobitcoin = cotacaobitcoin;
        this.cotacaoethereum = cotacaoethereum;
        this.cotacaoripple = cotacaoripple;
    }
    public Cotacao (int id){
        
        this.id = id;
    
    }
    
    
    
}
