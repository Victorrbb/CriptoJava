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
import model.Carteira;
import model.Investidor;
import model.Moedas;
import model.Real;
import view.Depositar;
import view.Login;
import view.LoginDepositar;
import view.Menu;

/**
 *
 * @author victo
 */
public class ControllerEntrarDeposito {
    
    private LoginDepositar view;

    public ControllerEntrarDeposito(LoginDepositar view) {
        this.view = view;
    }

    public LoginDepositar getView() {
        return view;
    }

    public void setView(LoginDepositar view) {
        this.view = view;
    }
    
    
    public void loginDeposito(){
        Investidor investidor = new Investidor(null, null ,view.getTxtCpf().getText(), 
                                      view.getTxtSenha().getText());   
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            InvestidorDAO dao = new InvestidorDAO(conn);
            ResultSet res = dao.consultar(investidor);
            if(res.next()){
                JOptionPane.showMessageDialog(view, "liberado para usar a opção depositar!"); 
                
                String cpf = res.getString("cpf");
                String senha = res.getString("senha");
                String nome = res.getString("nome");
                double real = res.getDouble("real");
                ArrayList<Moedas> moedasCarteira = new ArrayList<Moedas>();
                moedasCarteira.add(new Real(real));
                Carteira carteira = new Carteira(moedasCarteira);
                Depositar viewDepositar = new Depositar(new Investidor(carteira,nome,cpf,senha));
               
                viewDepositar.setVisible(true);
                view.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(view, "erro na senha ou cpf!");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexao!");
        }
    }
    
}
