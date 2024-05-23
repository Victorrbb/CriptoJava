package controller;

import DAO.Conexao;
import DAO.InvestidorDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Carteira;
import model.Ethereum;
import model.Investidor;
import model.Moedas;
import model.Real;
import model.Bitcoin;
import model.Ripple;
import view.Consultar;
import view.VendeRipple;

public class ControllerVendeRipple {
    private VendeRipple view;
    private Investidor investidor;

    public ControllerVendeRipple(VendeRipple view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
    }

    public void vendeRipple() {
        double quantidadeemreal = Double.parseDouble(view.getTxtVendaReal().getText());
        Conexao conexao = new Conexao();

        double cotacaoripple = investidor.getCotacao().getCotacaoripple();
        double saldorealatual = investidor.getCarteira().getMoedas().get(0).getSaldo();
        double saldobitcoinatual = investidor.getCarteira().getMoedas().get(1).getSaldo();
        double saldoatualethereum = investidor.getCarteira().getMoedas().get(2).getSaldo();
        double saldoatualripple = investidor.getCarteira().getMoedas().get(3).getSaldo();
        Ripple rippleInstance = new Ripple();
        double taxaRippleVenda = rippleInstance.getTaxaVenda();

        // Converter a quantidade em reais para a quantidade de Ripple correspondente
        double quantidadeRipple = quantidadeemreal / cotacaoripple;

        // Calcula o valor da taxa de venda
        double taxaVenda = quantidadeemreal * (1-taxaRippleVenda);

        // Calcula o valor líquido que o usuário receberá
        double valorLiquidoRecebido = quantidadeemreal - taxaVenda;

        if (quantidadeRipple > saldoatualripple) {
            JOptionPane.showMessageDialog(view, "Saldo insuficiente de Ripple para a venda.");
            return;
        }

        double realfinal = saldorealatual + taxaVenda;
        double ripplefinal = saldoatualripple - quantidadeRipple;

        // Verifica se o saldo final de Ripple é negativo, se sim, ajusta para zero
        if (ripplefinal < 0) {
            ripplefinal = 0;
            JOptionPane.showMessageDialog(view, "Saldo de Ripple após a venda não pode ser negativo. Venda cancelada.");
            return;
        }

        ArrayList<Moedas> moedasCarteira = new ArrayList<>();
        moedasCarteira.add(new Real(realfinal)); // Atualizando saldo final de Real
        moedasCarteira.add(new Bitcoin(saldobitcoinatual)); // Atualizando saldo final de Bitcoin
        moedasCarteira.add(new Ethereum(saldoatualethereum)); // Atualizando saldo final de Ethereum
        moedasCarteira.add(new Ripple(ripplefinal)); // Atualizando saldo final de Ripple

        Carteira carteira = new Carteira(moedasCarteira);
        Investidor investidorAtualizado = new Investidor(carteira, investidor.getCotacao(), investidor.getNome(), investidor.getCpf(), investidor.getSenha());

        Connection conn = null;
        try {
            conn = conexao.getConnection();
            conn.setAutoCommit(false); // Inicia a transação

            InvestidorDAO dao = new InvestidorDAO(conn);
            dao.atualizarInvestidor(investidorAtualizado);
            dao.salvarTransacao(investidor, "-", taxaVenda, "ripple", cotacaoripple, taxaRippleVenda, realfinal, saldobitcoinatual, saldoatualethereum, ripplefinal);

            conn.commit(); // Confirma a transação
            JOptionPane.showMessageDialog(view, "Venda realizada com sucesso");

            Consultar viewConsultar = new Consultar(investidorAtualizado);
            viewConsultar.setVisible(true);
            view.setVisible(false);
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Desfaz a transação em caso de erro
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(view, "Falha de conexão: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}