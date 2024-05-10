package model;

public class Moedas {
    protected double saldo;

    public Moedas(double saldo) {
        this.saldo = saldo;
    }

    public Moedas() {
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    

    // Métodos para as taxas de compra e venda
    public double getTaxaCompra() {
        return 0;
    }

    public double getTaxaVenda() {
        return 0;
    }
}
