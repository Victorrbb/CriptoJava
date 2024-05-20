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
    private Investidor aluno ;

    public ControllerSacar(Sacar view) {
        this.view = view;
    }

    public ControllerSacar(Investidor aluno) {
        this.aluno = aluno;
    }

    public ControllerSacar(Sacar view, Investidor aluno) {
        this.view = view;
        this.aluno = aluno;
    }

    public Sacar getView() {
        return view;
    }

    public void setView(Sacar view) {
        this.view = view;
    }

    public Investidor getAluno() {
        return aluno;
    }

    public void setAluno(Investidor aluno) {
        this.aluno = aluno;
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
        ArrayList<Moedas> moedasCarteira = new ArrayList<Moedas>();
        moedasCarteira.add(new Real(novosaldoreal));
        Carteira carteira = new Carteira(moedasCarteira);
        
        Investidor investidor = new Investidor(carteira,"",cpf,"");
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
                InvestidorDAO dao = new InvestidorDAO(conn);
                dao.attreal(investidor);
                JOptionPane.showMessageDialog(view,"Atualizado com sucesso! agora esse é seu novo saldo:"+ novosaldoreal);

            } catch(SQLException e){
                JOptionPane.showMessageDialog(view,"Falha  de conexão");
            
            
        
        }
    
    
    
    }
    
}
