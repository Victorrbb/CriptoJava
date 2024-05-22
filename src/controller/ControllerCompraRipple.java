




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
import view.CompraRipple;
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
public class ControllerCompraRipple {
    private CompraRipple view;
    private Investidor investidor ;

    public ControllerCompraRipple(CompraRipple view) {
        this.view = view;
    }

    

    public CompraRipple getView() {
        return view;
    }

    public void setView(CompraRipple view) {
        this.view = view;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }

    public ControllerCompraRipple(CompraRipple view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
    }
    
    public void compraRipple() {
    double real = Double.parseDouble(view.getTxtReal().getText());
    Conexao conexao = new Conexao();
    String nome = investidor.getNome();
    String cpf = investidor.getCpf();
    String senha = investidor.getSenha();
    
    double cotacaoripple = investidor.getCotacao().getCotacaoripple();
    double saldorealatual = investidor.getCarteira().getMoedas().get(0).getSaldo();
    double saldobitcoinatual = investidor.getCarteira().getMoedas().get(1).getSaldo();
    double saldoatualethereum = investidor.getCarteira().getMoedas().get(2).getSaldo();
    double saldoatualripple = investidor.getCarteira().getMoedas().get(3).getSaldo();
    Ripple rippleInstance = new Ripple();                       
    double taxaRippleCompra = rippleInstance.getTaxaCompra();            
    
    double saldoAposTaxa = real * (1 - taxaRippleCompra);
    if (saldoAposTaxa < 0) {
        JOptionPane.showMessageDialog(view, "Saldo insuficiente para comprar bitcoin.");
        return; // Sai do método se o saldo após a taxa for negativo
    }
    double quantidadeRipple = saldoAposTaxa / cotacaoripple;
    double realfinal = saldorealatual - real;
    double ripplefinal = saldoatualripple + quantidadeRipple;
    
    ArrayList<Moedas> moedasCarteira = new ArrayList<>();
    moedasCarteira.add(new Real(realfinal)); // Atualizando saldo final de Real
    moedasCarteira.add(new Bitcoin(saldobitcoinatual));
    moedasCarteira.add(new Ethereum(saldoatualethereum));
    moedasCarteira.add(new Ripple(ripplefinal));// Atualizando saldo final de Bitcoin
    Carteira carteira = new Carteira(moedasCarteira);
    Investidor investidorAtualizado = new Investidor(carteira, nome, cpf, senha);
    
    Connection conn = null;
        try {
            conn = conexao.getConnection();
            conn.setAutoCommit(false); // Inicia a transação

            InvestidorDAO dao = new InvestidorDAO(conn);
            dao.atualizarInvestidor(investidorAtualizado);
            dao.salvarTransacao(investidor, "+", saldoAposTaxa, "ripple", cotacaoripple, taxaRippleCompra, realfinal, saldobitcoinatual, saldoatualethereum, ripplefinal);

            conn.commit(); // Confirma a transação
            JOptionPane.showMessageDialog(view, "Compra realizada com sucesso");

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