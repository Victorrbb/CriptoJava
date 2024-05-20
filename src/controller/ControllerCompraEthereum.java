




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
    double saldobitcoinatual = investidor.getCarteira().getMoedas().get(1).getSaldo();
    double saldoatualethereum = investidor.getCarteira().getMoedas().get(2).getSaldo();
    double saldoatualripple = investidor.getCarteira().getMoedas().get(3).getSaldo();
    Ethereum ethereumInstance = new Ethereum();                       
    double taxaEthereumCompra = ethereumInstance.getTaxaCompra();            
    
    double saldoAposTaxa = real * (1 - taxaEthereumCompra);
    if (saldoAposTaxa < 0) {
        JOptionPane.showMessageDialog(view, "Saldo insuficiente para comprar bitcoin.");
        return; // Sai do método se o saldo após a taxa for negativo
    }
    double quantidadeEthereum = saldoAposTaxa / cotacaoethereum;
    double realfinal = saldorealatual - real;
    double ethereumfinal = saldoatualethereum + quantidadeEthereum;
    
    ArrayList<Moedas> moedasCarteira = new ArrayList<>();
    moedasCarteira.add(new Real(realfinal)); // Atualizando saldo final de Real
    moedasCarteira.add(new Bitcoin(saldobitcoinatual));
    moedasCarteira.add(new Ethereum(ethereumfinal));
    moedasCarteira.add(new Ripple(saldoatualripple));// Atualizando saldo final de Bitcoin
    Carteira carteira = new Carteira(moedasCarteira);
    Investidor investidorAtualizado = new Investidor(carteira, nome, cpf, senha);
    
    try {
        Connection conn = conexao.getConnection();
        InvestidorDAO dao = new InvestidorDAO(conn);
        dao.atualizarInvestidor(investidorAtualizado);
        JOptionPane.showMessageDialog(view, "Compra realizada com sucesso");

        // Passa o investidor atualizado para a nova view
        Consultar viewConsultar = new Consultar(investidorAtualizado);
        viewConsultar.setVisible(true);
        view.setVisible(false);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(view, "Falha de conexão");
    }
}
    
}