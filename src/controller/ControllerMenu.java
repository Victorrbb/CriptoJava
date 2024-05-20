/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import DAO.Conexao;
import DAO.InvestidorDAO;
import java.util.Random;
import model.Cotacao;
import model.Investidor;
import view.Menu;

/**
 *
 * @author victo
 */
public class ControllerMenu {
    
    private Menu view;
    private Investidor investidor ;

    public ControllerMenu(Menu view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
    }
    

    public Menu getView() {
        return view;
    }

    public void setView(Menu view) {
        this.view = view;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }
    
    
    public void atualizarCripto(){
 
        Conexao conexao = new Conexao();
        
        
        try{
            Connection conn = conexao.getConnection();
            InvestidorDAO dao = new InvestidorDAO(conn);
            ResultSet res = dao.buscarcotacao(investidor);
            if(res.next()){
                              
                double cotacaobitcoin = res.getDouble("cotacaobitcoin");
                double cotacaoethereum = res.getDouble("cotacaoethereum");
                double cotacaoripple = res.getDouble("cotacaoripple");
                
                double variacao = (new Random().nextDouble() - 0.5) * 0.1; // Random() gera de 0 a 1, então subtrai 0.5 para ficar entre -0.5 e 0.5, multiplica por 0.1 para ficar entre -0.05 e 0.05

        
                cotacaobitcoin *= (1 + variacao);
                cotacaoethereum *= (1 + variacao);
                cotacaoripple *= (1 + variacao);

                
                Cotacao cotacao = new Cotacao(cotacaobitcoin,cotacaoethereum,cotacaoripple);
                Investidor investidor =new Investidor (null,cotacao,null,null,null);
                
                
                

            // Atualiza as cotações no banco de dados
                dao.atualizarcotacao(investidor);
                JOptionPane.showMessageDialog(view,"Atualizado com sucesso!");
                
                
                
            } else {
                JOptionPane.showMessageDialog(view, "atualização de cripto não efetivada!");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexao!");
        }
        
    
    
    }
    
}
