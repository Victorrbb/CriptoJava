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
import view.VendeEthereum;

public class ControllerVendeEthereum {
    private VendeEthereum view;
    private Investidor investidor;

    public ControllerVendeEthereum(VendeEthereum view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
    }

    public void vendeEthereum() {
        double quantidadeemreal = Double.parseDouble(view.getTxtVendaReal().getText());
        Conexao conexao = new Conexao();

        double cotacaoethereum = investidor.getCotacao().getCotacaoethereum();
        double saldorealatual = investidor.getCarteira().getMoedas().get(0).getSaldo();
        double saldobitcoinatual = investidor.getCarteira().getMoedas().get(1).getSaldo();
        double saldoatualethereum = investidor.getCarteira().getMoedas().get(2).getSaldo();
        double saldoatualripple = investidor.getCarteira().getMoedas().get(3).getSaldo();
        Ethereum ethereumInstance = new Ethereum();
        double taxaEthereumVenda = ethereumInstance.getTaxaVenda();

        // Converter a quantidade em reais para a quantidade de Ethereum correspondente
        double quantidadeEthereum = quantidadeemreal / cotacaoethereum;

        // Calcula o valor da taxa de venda
        double taxaVenda = quantidadeemreal * taxaEthereumVenda;

        // Calcula o valor líquido que o usuário receberá
        double valorLiquidoRecebido = quantidadeemreal - taxaVenda;

        if (quantidadeEthereum > saldoatualethereum) {
            JOptionPane.showMessageDialog(view, "Saldo insuficiente de Ethereum para a venda.");
            return;
        }

        double realfinal = saldorealatual + valorLiquidoRecebido;
        double ethereumfinal = saldoatualethereum - quantidadeEthereum;

        // Verifica se o saldo final de Ethereum é negativo, se sim, ajusta para zero
        if (ethereumfinal < 0) {
            ethereumfinal = 0;
            JOptionPane.showMessageDialog(view, "Saldo de Ethereum após a venda não pode ser negativo. Venda cancelada.");
            return;
        }

        ArrayList<Moedas> moedasCarteira = new ArrayList<>();
        moedasCarteira.add(new Real(realfinal)); // Atualizando saldo final de Real
        moedasCarteira.add(new Bitcoin(saldobitcoinatual)); // Atualizando saldo final de Bitcoin
        moedasCarteira.add(new Ethereum(ethereumfinal)); // Atualizando saldo final de Ethereum
        moedasCarteira.add(new Ripple(saldoatualripple)); // Atualizando saldo final de Ripple

        Carteira carteira = new Carteira(moedasCarteira);
        Investidor investidorAtualizado = new Investidor(carteira, investidor.getCotacao(), investidor.getNome(), investidor.getCpf(), investidor.getSenha());

        Connection conn = null;
        try {
            conn = conexao.getConnection();
            conn.setAutoCommit(false); // Inicia a transação

            InvestidorDAO dao = new InvestidorDAO(conn);
            dao.atualizarInvestidor(investidorAtualizado);
            dao.salvarTransacao(investidor, "-", taxaVenda, "ethereum", cotacaoethereum, taxaEthereumVenda, realfinal, saldobitcoinatual, ethereumfinal, saldoatualripple);

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