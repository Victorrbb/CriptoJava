




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.InvestidorDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import model.Cotacao;
import model.Investidor;

import model.Bitcoin;
import model.Carteira;
import model.Moedas;
import model.Real;
import model.Ethereum;
import model.Ripple;
import view.Consultar;
import view.VendeBitcoin;

/**
 *
 * @author victo
 */
public class ControllerVendeBitcoin {
    private VendeBitcoin view;
    private Investidor investidor ;

   
    public ControllerVendeBitcoin(VendeBitcoin view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
    }
 
 
    public void vendeBitcoin() {
        double quantidadeemreal = Double.parseDouble(view.getTxtVendaReal().getText());
        Conexao conexao = new Conexao();

        double cotacaobitcoin = investidor.getCotacao().getCotacaobitcoin();
        double saldorealatual = investidor.getCarteira().getMoedas().get(0).getSaldo();
        double saldobitcoinatual = investidor.getCarteira().getMoedas().get(1).getSaldo();
        double saldoatualethereum = investidor.getCarteira().getMoedas().get(2).getSaldo();
        double saldoatualripple = investidor.getCarteira().getMoedas().get(3).getSaldo();
        Bitcoin bitcoinInstance = new Bitcoin();
        double taxaBitcoinVenda = bitcoinInstance.getTaxaVenda();

        // Converter a quantidade em reais para a quantidade de Bitcoin correspondente
        double quantidadeBitcoin = quantidadeemreal / cotacaobitcoin;

// Calcula o valor da taxa de venda
        double taxaVenda = quantidadeemreal * taxaBitcoinVenda;

        // Calcula o valor líquido que o usuário receberá
        double valorLiquidoRecebido = quantidadeemreal - taxaVenda;

        if (quantidadeBitcoin > saldobitcoinatual) {
            JOptionPane.showMessageDialog(view, "Saldo insuficiente de Bitcoin para a venda.");
            return;
        }

        double realfinal = investidor.getCarteira().getMoedas().get(0).getSaldo() + valorLiquidoRecebido;
        double bitcoinfinal = saldobitcoinatual - quantidadeBitcoin;

        // Verifica se o saldo final de bitcoin é negativo, se sim, ajusta para zero
        if (bitcoinfinal < 0) {
            bitcoinfinal = 0;
            JOptionPane.showMessageDialog(view, "Saldo de Bitcoin após a venda não pode ser negativo. Venda cancelada.");
            return;
        }


        ArrayList<Moedas> moedasCarteira = new ArrayList<>();
        moedasCarteira.add(new Real(realfinal)); // Atualizando saldo final de Real
        moedasCarteira.add(new Bitcoin(bitcoinfinal)); // Atualizando saldo final de Bitcoin
        moedasCarteira.add(new Ethereum(saldoatualethereum));
        moedasCarteira.add(new Ripple(saldoatualripple));
        Carteira carteira = new Carteira(moedasCarteira);
        Investidor investidorAtualizado = new Investidor(carteira, investidor.getCotacao(), investidor.getNome(), investidor.getCpf(), investidor.getSenha());

        try (Connection conn = conexao.getConnection()) {
            InvestidorDAO dao = new InvestidorDAO(conn);
            dao.atualizarInvestidor(investidorAtualizado);
            JOptionPane.showMessageDialog(view, "Venda realizada com sucesso");

            // Passa o investidor atualizado para a nova view
            Consultar viewConsultar = new Consultar(investidorAtualizado);
            viewConsultar.setVisible(true);
            view.setVisible(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Falha de conexão: " + e.getMessage());
        }
    }
}
    
