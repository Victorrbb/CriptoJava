/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAO.InvestidorDAO;
import DAO.Conexao;
import model.Investidor;
import view.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import view.Menu;
/**
 *
 * @author victo
 */
public class ControllerLogin {
    
    private Login view;

    public ControllerLogin(Login view) {
        this.view = view;
    }
    
    public void loginInvestidor(){
        Investidor investidor = new Investidor(null, null ,view.getTxtCpf().getText(), 
                                      view.getTxtSenha().getText());   
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            InvestidorDAO dao = new InvestidorDAO(conn);
            ResultSet res = dao.consultar(investidor);
            if(res.next()){
                JOptionPane.showMessageDialog(view, "Login feito!");               
                String cpf = res.getString("cpf");
                String senha = res.getString("senha");
                String nome = res.getString("nome");
                Menu viewMenu = new Menu(new Investidor(null,nome,cpf,senha));
                viewMenu.setVisible(true);
                view.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(view, "Login nao foi efetuado!");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexao!");
        }
    }
    
}
