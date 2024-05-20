/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.InvestidorDAO;
import DAO.Conexao;
import javax.swing.JOptionPane;
import model.Investidor;
import view.Depositar;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Carteira;
import model.Moedas;
import model.Real;

/**
 *
 * @author victo
 */
public class ControllerDeposito {
    
    
    private Depositar view;
    private Investidor investidor ;

    public ControllerDeposito(Depositar view) {
        this.view = view;
    }

    public ControllerDeposito(Investidor investidor) {
        this.investidor = investidor;
    }

    public ControllerDeposito(Depositar view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
    }

    public Depositar getView() {
        return view;
    }

    public void setView(Depositar view) {
        this.view = view;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }
   
    public void deposito(){
        double deposito =  Double.parseDouble(view.getTxtDeposito().getText());
        double realatual = Double.parseDouble(view.getLblReal().getText());
        String cpf = view.getLblCpf().getText();
        double novosaldoreal=deposito+realatual;
        ArrayList<Moedas> moedasCarteira = new ArrayList<Moedas>();
        moedasCarteira.add(new Real(novosaldoreal));
        Carteira carteira = new Carteira(moedasCarteira);
        
        Investidor investidor = new Investidor(carteira,"",cpf,"");
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
                InvestidorDAO dao = new InvestidorDAO(conn);
                dao.attreal(investidor);
                JOptionPane.showMessageDialog(view,"Atualizado com sucesso!agora seu saldo é de "+ novosaldoreal);

            } catch(SQLException e){
                JOptionPane.showMessageDialog(view,"Falha  de conexão");
            
            
        
        }
    
    
    
    }
    }
