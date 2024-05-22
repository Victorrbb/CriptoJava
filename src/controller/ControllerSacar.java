/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.InvestidorDAO;
import DAO.Conexao;
import javax.swing.JOptionPane;
import model.Investidor;
import view.Sacar;
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
public class ControllerSacar {
    
    
    private Sacar view;
    private Investidor investidor ;

    public ControllerSacar(Sacar view) {
        this.view = view;
    }

    public ControllerSacar(Investidor investidor) {
        this.investidor = investidor;
    }

    public ControllerSacar(Sacar view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
    }

    public Sacar getView() {
        return view;
    }

    public void setView(Sacar view) {
        this.view = view;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }
   
    public void sacar(){
        double saque =  Double.parseDouble(view.getTxtSaque().getText());
        double realatual = Double.parseDouble(view.getLblReal().getText());
        
        String cpf = view.getLblCpf().getText();
        
        double novosaldoreal=realatual-saque;
        
        if(novosaldoreal<0){
            JOptionPane.showMessageDialog(null, "O saldo resultante não pode ser menor que zero.");
        return;
            
        
        }
         investidor.getCarteira().getMoedas().get(0).setSaldo(novosaldoreal);
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
                InvestidorDAO dao = new InvestidorDAO(conn);
                dao.attreal(investidor);
                dao.extratosaque(investidor, saque);
                JOptionPane.showMessageDialog(view,"Atualizado com sucesso!agora seu saldo é de "+ novosaldoreal);

            } catch(SQLException e){
                JOptionPane.showMessageDialog(view,"Falha  de conexão");
            
            
        
        }
    
    
    
    }
    
}
