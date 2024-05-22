package controller;

import DAO.Conexao;
import DAO.InvestidorDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Investidor;
import view.Extrato;

public class ControllerExtrato {
    private final Extrato view;
    private final Investidor investidor;

    public ControllerExtrato(Extrato view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
        initController();
    }

    private void initController() {
        this.view.getJbjExibir().addActionListener(e -> exibirExtrato());
    }

    public void exibirExtrato() {
        Conexao conexao = new Conexao();
        try (Connection conn = conexao.getConnection()) {
            InvestidorDAO dao = new InvestidorDAO(conn);
            ResultSet res = dao.acharExtrato(investidor.getCpf());

            DefaultListModel<String> model = new DefaultListModel<>();
            model.addElement(String.format("Nome: %s", investidor.getNome()));
            model.addElement(String.format("CPF: %s", investidor.getCpf()));
            
            model.addElement("");

            boolean hasData = false;  // Verificar se há dados

            while (res.next()) {
                hasData = true;
                String data = String.format("Data: %s", res.getString("data"));
                String tipo = res.getString("sinal");
                String valor = String.format("Valor: R$ %.2f", res.getDouble("valor"));
                String moeda = String.format("Moeda: %s", res.getString("moeda"));
                String cotacao = String.format("Cotação: %.4f", res.getDouble("cotacao"));
                String taxa = String.format("Taxa: %.4f", res.getDouble("taxa"));
                String saldoReal = String.format("Saldo Real: R$ %.2f", res.getDouble("real"));
                String saldoBitcoin = String.format("Saldo Bitcoin: %.4f", res.getDouble("bitcoin"));
                String saldoEthereum = String.format("Saldo Ethereum: %.4f", res.getDouble("ethereum"));
                String saldoRipple = String.format("Saldo Ripple: %.4f", res.getDouble("ripple"));

                model.addElement(String.join(" | ", data, tipo, valor, moeda, cotacao, taxa, saldoReal, saldoBitcoin, saldoEthereum, saldoRipple));
            }

            if (!hasData) {
                model.addElement("Nenhum registro encontrado.");
            }

            this.view.getjList1().setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erro ao exibir extrato!", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
