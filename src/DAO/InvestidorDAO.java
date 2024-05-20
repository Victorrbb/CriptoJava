/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import model.Cotacao;
import model.Investidor;

/**
 *
 * @author victo
 */
public class InvestidorDAO {
    
    private Connection conn;

    public InvestidorDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Investidor investidor) throws SQLException{

        String sql = "select * from investidor where cpf = ? and senha = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, investidor.getCpf());
        statement.setString(2, investidor.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    public void inserir(Investidor investidor) throws SQLException{
//        String sql = "insert into investidor (nome, cpf, senha) values ('" +
//                investidor.getNome()     + "', '" + 
//                investidor.getCpf()  + "', '" +
//                investidor.getSenha()    + "')";
        String sql = "insert into investidor(nome, cpf, senha, "
                  + "real, bitcoin, ethereum, ripple)values "
                  + "(?, ?, ?, 0, 0, 0, 0)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, investidor.getNome());
        statement.setString(2, investidor.getCpf());
        statement.setString(3, investidor.getSenha());

        statement.execute();
        conn.close();
    }
    
    public void deposito (Investidor investidor) throws SQLException{
        
        String sql = "update principal set real = ? where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, investidor.getSenha());
        statement.setString(2,investidor.getCpf());
        statement.execute();
        conn.close();
        
    
    
    }
        public void attreal(Investidor investidor) throws SQLException{
        
        String sql = "update investidor set real = ? where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDouble(1,investidor.getCarteira().getMoedas().get(0).getSaldo());
        statement.setString(2,investidor.getCpf());       
        statement.execute();
        conn.close();
        
        
        
        
        }
        
        
        
        public ResultSet buscarcotacao(Investidor investidor) throws SQLException{
        
        String sql = "select * from cotacao where id = ? ";        
        PreparedStatement statement = conn.prepareStatement(sql);
        if (investidor.getCotacao() == null) {
        // Se for null, cria uma nova instância de Cotacao com o ID desejado
        investidor.setCotacao(new Cotacao(1)); // Defina o ID desejado, no seu caso 1
    }
    
    // Define o parâmetro da consulta com o ID da Cotacao
    statement.setInt(1, investidor.getCotacao().getId());
    
    statement.execute();
    ResultSet resultado = statement.getResultSet();
    return resultado;
        
    
    }
    
    
    public void atualizarcotacao (Investidor investidor) throws SQLException{
        
        String sql = "update cotacao set cotacaobitcoin = ?,cotacaoethereum = ? , cotacaoripple = ? where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        if (investidor.getCotacao() == null) {
        // Se for null, cria uma nova instância de Cotacao com o ID desejado
        investidor.setCotacao(new Cotacao(1)); // Defina o ID desejado, no seu caso 1
    }
        statement.setDouble(1, investidor.getCotacao().getCotacaobitcoin());
        statement.setDouble(2, investidor.getCotacao().getCotacaoethereum());
        statement.setDouble(3, investidor.getCotacao().getCotacaoripple());
        statement.setInt(4, investidor.getCotacao().getId());
        
         statement.executeUpdate();
         
        conn.close();
        
    
    
    }
    public void atualizarInvestidor(Investidor investidor) throws SQLException {
    String sql = "update investidor set real = ?, bitcoin = ?, ethereum = ?, ripple = ? where cpf = ?";
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setDouble(1, investidor.getCarteira().getMoedas().get(0).getSaldo());
    statement.setDouble(2, investidor.getCarteira().getMoedas().get(1).getSaldo());
    statement.setDouble(3, investidor.getCarteira().getMoedas().get(2).getSaldo());
    statement.setDouble(4, investidor.getCarteira().getMoedas().get(3).getSaldo());
    statement.setString(5, investidor.getCpf());

    statement.executeUpdate(); // Executa a atualização
    conn.close(); // Fecha a conexão
}
        
        
        
        
       
    }

    
    


