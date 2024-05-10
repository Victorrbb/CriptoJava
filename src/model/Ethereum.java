package model;

public class Ethereum extends Moedas {
    public Ethereum() {
        super();
    }

    public Ethereum(double saldo) {
        super(saldo);
    }

    @Override
    public double getTaxaCompra() {
        return 0.01; // Taxa de compra de Ethereum: 1%
    }

    @Override
    public double getTaxaVenda() {
        return 0.02; // Taxa de venda de Ethereum: 2%
    }
}
