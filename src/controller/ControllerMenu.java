/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

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

    public void setAluno(Investidor investidor) {
        this.investidor = investidor;
    }
    
}
