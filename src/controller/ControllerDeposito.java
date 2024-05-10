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

/**
 *
 * @author victo
 */
public class ControllerDeposito {
    private Depositar view;
    
    public ControllerDeposito(Depositar view) {
        this.view = view;
    }

    public Depositar getView() {
        return view;
    }

    public void setView(Depositar view) {
        this.view = view;
    }
    
    public void deposito(){
        
        String cpf= view.getTxtCpf().getText();
        String senha = view.getTxtSenha().getText();
        double valodeposito = Double.parseDouble(view.getTxtDeposito().getText());
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            InvestidorDAO dao = new InvestidorDAO(conn);
        
        }
    
    }
    
    
//    public class ControllerDeposito { 
//    private Deposito view;
//
//    public ControllerDeposito(Deposito view) {
//        this.view = view;
//    }
//    public void depositarSalario(){
//        String cpf = view.getTxtCpfCliente().getText();
//        String senha = view.getTxtSenhaCliente().getText();
//        double valor_deposito = Double.parseDouble(view.getEntrada_valor().getText());
//
//        conexao_banco conexao = new conexao_banco();
//        try{
//            Connection conn = conexao.getConnection();
//            DB_Cliente db = new DB_Cliente(conn);
//            Cliente res = db.getCliente(cpf,senha);
//            if(res.getCpf().equals("") ){
//                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);
//            }
//            else{
//                res.DepositarContaSalario(valor_deposito);
//                db.updateCliente(res);
//                JOptionPane.showMessageDialog(view, "Deposito na conta salario efetuado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//            }
//        } catch( SQLException e){
//            JOptionPane.showMessageDialog(view, "Erro de conexão, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
//        e.printStackTrace();
//        }
//    }

    
    
    
}
