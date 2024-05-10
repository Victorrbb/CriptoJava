/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
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
//        String sql = "select * from aluno where usuario = '" + 
//                aluno.getUsuario() + "' AND senha = '" +
//                aluno.getSenha() + "'";
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
        public void attreal(Investidor investidor) throws SQLException{;
        
        String sql = "update investidor set real = ? where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDouble(4,investidor.getCarteira().getMoedas().get(0).getSaldo());
        statement.setString(2,investidor.getCpf());       
        statement.execute();
        conn.close();
        
        
        
        
        }

    
    
//    public void atualizar (Aluno aluno) throws SQLException{;
//        
//        String sql = "update aluno set senha = ? where usuario = ?";
//        PreparedStatement statement = conn.prepareStatement(sql);
//        statement.setString(1, aluno.getSenha());
//        statement.setString(2,aluno.getUsuario());
//        statement.execute();
//        conn.close();
}
