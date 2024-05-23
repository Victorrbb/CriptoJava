




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
import view.Comprabitcoin;
import model.Bitcoin;
import model.Carteira;
import model.Moedas;
import model.Real;
import model.Ethereum;
import model.Ripple;
import view.Consultar;

/**
 *
 * @author victo
 */
public class ControllerCompraBitcoin {
    private Comprabitcoin view;
    private Investidor investidor ;

    public ControllerCompraBitcoin(Comprabitcoin view) {
        this.view = view;
    }

    

    public Comprabitcoin getView() {
        return view;
    }

    public void setView(Comprabitcoin view) {
        this.view = view;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }

    public ControllerCompraBitcoin(Comprabitcoin view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
    }
    
    public void compraBitcoin() {
    double real = Double.parseDouble(view.getTxtReal().getText());
    Conexao conexao = new Conexao();
    String nome = investidor.getNome();
    String cpf = investidor.getCpf();
    String senha = investidor.getSenha();
    
    double cotacaobitcoin = investidor.getCotacao().getCotacaobitcoin();
    double saldorealatual = investidor.getCarteira().getMoedas().get(0).getSaldo();
    double saldobitcoinatual = investidor.getCarteira().getMoedas().get(1).getSaldo();
    Bitcoin bitcoinInstance = new Bitcoin();                       
    double taxaBitcoinCompra = bitcoinInstance.getTaxaCompra();            
    
    double saldoAposTaxa = real * (1 - taxaBitcoinCompra);
    double realfinal = saldorealatual - real;
    
    // Verifica se o saldo final de Real é negativo
    if (realfinal < 0) {
        JOptionPane.showMessageDialog(view, "Saldo insuficiente para comprar Bitcoin.");
        return;
    }
    
    double quantidadeBitcoin = saldoAposTaxa / cotacaobitcoin;
    double bitcoinfinal = saldobitcoinatual + quantidadeBitcoin;
    
    ArrayList<Moedas> moedasCarteira = new ArrayList<>();
    moedasCarteira.add(new Real(realfinal)); // Atualizando saldo final de Real
    moedasCarteira.add(new Bitcoin(bitcoinfinal)); // Atualizando saldo final de Bitcoin
    moedasCarteira.add(new Ethereum(investidor.getCarteira().getMoedas().get(2).getSaldo())); // Mantendo saldo de Ethereum
    moedasCarteira.add(new Ripple(investidor.getCarteira().getMoedas().get(3).getSaldo())); // Mantendo saldo de Ripple
    Carteira carteira = new Carteira(moedasCarteira);
    Investidor investidorAtualizado = new Investidor(carteira, nome, cpf, senha);
    
    Connection conn = null;
    try {
        conn = conexao.getConnection();
        conn.setAutoCommit(false); // Inicia a transação

        InvestidorDAO dao = new InvestidorDAO(conn);
        dao.atualizarInvestidor(investidorAtualizado);
        dao.salvarTransacao(investidor, "+", saldoAposTaxa, "bitcoin", cotacaobitcoin, taxaBitcoinCompra, realfinal, bitcoinfinal, investidor.getCarteira().getMoedas().get(2).getSaldo(), investidor.getCarteira().getMoedas().get(3).getSaldo());

        conn.commit(); // Confirma a transação
        JOptionPane.showMessageDialog(view, "Compra de Bitcoin realizada com sucesso");

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
    