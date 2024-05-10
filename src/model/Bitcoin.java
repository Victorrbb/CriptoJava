package model;

public class Bitcoin extends Moedas {
    public Bitcoin() {
        super();
    }

    public Bitcoin(double saldo) {
        super(saldo);
    }

    @Override
    public double getTaxaCompra() {
        return 0.02; // Taxa de compra de Bitcoin: 2%
    }

    @Override
    public double getTaxaVenda() {
        return 0.03; // Taxa de venda de Bitcoin: 3%
    }
}
