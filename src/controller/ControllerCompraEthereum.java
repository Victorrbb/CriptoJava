




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
import view.CompraEthereum;
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
public class ControllerCompraEthereum {
    private CompraEthereum view;
    private Investidor investidor ;

    public ControllerCompraEthereum(CompraEthereum view) {
        this.view = view;
    }

    

    public CompraEthereum getView() {
        return view;
    }

    public void setView(CompraEthereum view) {
        this.view = view;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }

    public ControllerCompraEthereum(CompraEthereum view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
    }
    
    public void compraEthereum() {
    double real = Double.parseDouble(view.getTxtReal().getText());
    Conexao conexao = new Conexao();
    String nome = investidor.getNome();
    String cpf = investidor.getCpf();
    String senha = investidor.getSenha();
    
    double cotacaoethereum = investidor.getCotacao().getCotacaoethereum();
    double saldorealatual = investidor.getCarteira().getMoedas().get(0).getSaldo();
    double saldoatualethereum = investidor.getCarteira().getMoedas().get(2).getSaldo();
    Ethereum ethereumInstance = new Ethereum();                       
    double taxaEthereumCompra = ethereumInstance.getTaxaCompra();            
    
    double saldoAposTaxa = real * (1 - taxaEthereumCompra);
    double realfinal = saldorealatual - real;
    
    // Verifica se o saldo final de Real é negativo
    if (realfinal < 0) {
        JOptionPane.showMessageDialog(view, "Saldo insuficiente para comprar Ethereum.");
        return;
    }
    
    double quantidadeEthereum = saldoAposTaxa / cotacaoethereum;
    double ethereumfinal = saldoatualethereum + quantidadeEthereum;
    
    ArrayList<Moedas> moedasCarteira = new ArrayList<>();
    moedasCarteira.add(new Real(realfinal)); // Atualizando saldo final de Real
    moedasCarteira.add(new Bitcoin(investidor.getCarteira().getMoedas().get(1).getSaldo())); // Mantendo saldo de Bitcoin
    moedasCarteira.add(new Ethereum(ethereumfinal)); // Atualizando saldo final de Ethereum
    moedasCarteira.add(new Ripple(investidor.getCarteira().getMoedas().get(3).getSaldo())); // Mantendo saldo de Ripple
    Carteira carteira = new Carteira(moedasCarteira);
    Investidor investidorAtualizado = new Investidor(carteira, nome, cpf, senha);
    
    Connection conn = null;
    try {
        conn = conexao.getConnection();
        conn.setAutoCommit(false); // Inicia a transação

        InvestidorDAO dao = new InvestidorDAO(conn);
        dao.atualizarInvestidor(investidorAtualizado);
        dao.salvarTransacao(investidor, "+", saldoAposTaxa, "ethereum", cotacaoethereum, taxaEthereumCompra, realfinal, investidor.getCarteira().getMoedas().get(1).getSaldo(), ethereumfinal, investidor.getCarteira().getMoedas().get(3).getSaldo());

        conn.commit(); // Confirma a transação
        JOptionPane.showMessageDialog(view, "Compra de Ethereum realizada com sucesso");

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