package model;

public class Ripple extends Moedas {
    public Ripple() {
        super();
    }

    public Ripple(double saldo) {
        super(saldo);
    }

    @Override
    public double getTaxaCompra() {
        return 0.01; // Taxa de compra de Ripple: 1%
    }

    @Override
    public double getTaxaVenda() {
        return 0.01; // Taxa de venda de Ripple: 1%
    }
}
