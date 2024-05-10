/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author unifvbinda
 */
public class Usuario {
    
    
    private String nome, cpf;
    private String senha;
    

    
    

    
    

   

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String senha ) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        
        
    }

    
    

    

    

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

    
    
    
    
    
}
