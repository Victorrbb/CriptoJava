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
import javax.swing.JOptionPane;
import model.Bitcoin;
import model.Carteira;
import model.Cotacao;
import model.Ethereum;
import model.Investidor;
import model.Moedas;
import model.Real;
import model.Ripple;
import view.Compra;
import view.EntraExtrato;
import view.Extrato;

/**
 *
 * @author victo
 */
public class ControllerEntraExtrato {
    private EntraExtrato view;

    public ControllerEntraExtrato(EntraExtrato view) {
        this.view = view;
    }

    public EntraExtrato getView() {
        return view;
    }

    public void setView(EntraExtrato view) {
        this.view = view;
    }
    
    public void entraExtrato(){
        
        System.out.println("Iniciando o método entraCompraCripto");
        
        Investidor investidor = new Investidor(null, null, view.getLblCpf().getText(), view.getTxtSenha().getText());
        Conexao conexao = new Conexao();
        
        try (Connection conn = conexao.getConnection()) {
            System.out.println("Conexão estabelecida com sucesso");
            InvestidorDAO dao = new InvestidorDAO(conn);
            ResultSet res = dao.consultar(investidor);
            
            if (res.next()) {
                System.out.println("Investidor encontrado");
                ResultSet cot = dao.buscarcotacao(investidor);
                
                if (cot.next()) {
                    System.out.println("Cotações encontradas");
                    double cotacaobitcoin = cot.getDouble("cotacaobitcoin");
                    double cotacaoethereum = cot.getDouble("cotacaoethereum");
                    double cotacaoripple = cot.getDouble("cotacaoripple");
                    String cpf = res.getString("cpf");
                    String senha = res.getString("senha");
                    String nome = res.getString("nome");
                    double real = res.getDouble("real");
                    double bitcoin = res.getDouble("bitcoin");
                    double ethereum = res.getDouble("ethereum");
                    double ripple = res.getDouble("ripple");
                    Cotacao cotacao = new Cotacao(cotacaobitcoin, cotacaoethereum, cotacaoripple);

                    ArrayList<Moedas> moedasCarteira = new ArrayList<>();
                    moedasCarteira.add(new Real(real));
                    moedasCarteira.add(new Bitcoin(bitcoin));
                    moedasCarteira.add(new Ethereum(ethereum));
                    moedasCarteira.add(new Ripple(ripple));

                    Carteira carteira = new Carteira(moedasCarteira);
                    Extrato viewExtrato = new Extrato(new Investidor(carteira, cotacao, nome, cpf, senha));

                    viewExtrato.setVisible(true);
                    view.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(view, "Cotações não encontradas!");
                }
                
            } else {
                JOptionPane.showMessageDialog(view, "Erro na senha ou CPF!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro de conexão: " + e.getMessage());
        }
        
        System.out.println("Método entraExtrato finalizado");
        
        
        
        
        
        
    
    }
    
}
