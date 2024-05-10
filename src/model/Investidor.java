/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author unifvbinda
 */
public class Investidor extends Usuario {
    
    
    private Carteira carteira;

    public Investidor() {
        
    

}
    
        
       
    
    

    public Investidor( Carteira carteira) {
        
        this.carteira = carteira;
    }

    public Investidor(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
        this.carteira = new Carteira();
    }

    public Investidor(Carteira carteira, String nome, String cpf, String senha) {
        super(nome, cpf, senha);
        this.carteira =  carteira;
    }
    

    

    


    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }
    
    
    
    
            
    
    
    
}
