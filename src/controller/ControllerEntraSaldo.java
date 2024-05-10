/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import DAO.InvestidorDAO;
import DAO.Conexao;
import model.Investidor;
import view.EntrarConsultarDeposito;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Bitcoin;
import model.Carteira;
import model.Ethereum;
import model.Moedas;
import view.Consultar;
import model.Real;
import model.Ripple;

/**
 *
 * @author victo
 */
public class ControllerEntraSaldo {
    private EntrarConsultarDeposito view;
    
    
    
    public ControllerEntraSaldo(EntrarConsultarDeposito view) {
        this.view = view;
    }
    
    public void entrarSaldo(){
        Investidor investidor = new Investidor(null, null,view.getTxtCpf().getText(), 
                                      view.getTxtSenha().getText());   
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            InvestidorDAO dao = new InvestidorDAO(conn);
            ResultSet res = dao.consultar(investidor);
            if(res.next()){
                JOptionPane.showMessageDialog(view, "veja seu saldo!");               
                String cpf = res.getString("cpf");
                String senha = res.getString("senha");
                double real = res.getDouble("real");
                double bitcoin=res.getDouble("bitcoin");
                double ethereum=res.getDouble("ethereum");
                double ripple=res.getDouble("ripple");                
                ArrayList<Moedas> moedasCarteira = new ArrayList<Moedas>();
                moedasCarteira.add(new Real(real));
                moedasCarteira.add(new Bitcoin(bitcoin));
                moedasCarteira.add(new Ethereum(ethereum));
                moedasCarteira.add(new Ripple(ripple));
                
                
                
                Carteira carteira = new Carteira(moedasCarteira);
                Consultar viewConsultar = new Consultar(new Investidor(carteira,null,cpf,senha));
                viewConsultar.setVisible(true);
                view.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(view, "entrada para vizulizar o saldo não efetivada!");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexao!");
        }
    }
    
}
