/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAO.InvestidorDAO;
import DAO.Conexao;
import model.Investidor;
import view.Cadastro;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
/**
 *
 * @author victo
 */
public class ControllerCadastro {

    private Cadastro view;
    
    public ControllerCadastro(Cadastro view) {
        this.view = view;
    }
    
    public void salvarInvestidor(){
        String nome = view.getTxtNome().getText();
        String senha = view.getTxtSenha().getText();
        String cpf = view.getTxtCpf().getText();
        
        
        Investidor investidor = new Investidor(nome, cpf ,senha);
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            System.out.println("conectou");
            InvestidorDAO dao = new InvestidorDAO(conn);
            dao.inserir(investidor);
            JOptionPane.showMessageDialog(view, "Investidor Cadastrado!");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Investidor nao Cadastrado!");
        }
    }
    
    
}
