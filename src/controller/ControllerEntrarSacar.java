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
import model.Ethereum;
import model.Investidor;
import model.Moedas;
import model.Real;
import model.Ripple;

import view.Sacar;
import view.Login;

import view.LoginSacar;
import view.Menu;
/**
 *
 * @author victo
 */
public class ControllerEntrarSacar {
    private LoginSacar view;

    public ControllerEntrarSacar(LoginSacar view) {
        this.view = view;
    }

    public LoginSacar getView() {
        return view;
    }

    public void setView(LoginSacar view) {
        this.view = view;
    }
    
    
    public void loginSacar(){
        Investidor investidor = new Investidor(null, null ,view.getTxtCpf().getText(), 
                                      view.getTxtSenha().getText());   
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            InvestidorDAO dao = new InvestidorDAO(conn);
            ResultSet res = dao.consultar(investidor);
            if(res.next()){
                JOptionPane.showMessageDialog(view, "liberado parau sar a opção sacar!");               
                String cpf = res.getString("cpf");
                String senha = res.getString("senha");
                String nome = res.getString("nome");
                double real = res.getDouble("real");
                double bitcoin = res.getDouble("bitcoin");
                double ethereum = res.getDouble("ethereum");
                double ripple = res.getDouble("ripple");
                ArrayList<Moedas> moedasCarteira = new ArrayList<Moedas>();
                moedasCarteira.add(new Real(real));
                 moedasCarteira.add(new Bitcoin(bitcoin));
                moedasCarteira.add(new Ethereum(ethereum));
                moedasCarteira.add(new Ripple(ripple));
                Carteira carteira = new Carteira(moedasCarteira);
                Sacar viewSacar = new Sacar(new Investidor(carteira,nome,cpf,senha));
               
                viewSacar.setVisible(true);
                view.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(view, "erro na senha ou cpf!");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexao!");
        }
    }
    
}
