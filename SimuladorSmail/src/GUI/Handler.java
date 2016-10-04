/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;

/**
 *
 * @author Luca
 */
public class Handler extends JFrame{
    
    Painel p;
    
    public Handler(){
        super("Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 800);
        p = new Painel();
        this.setContentPane(p);
        new Thread(p).start();
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    public Painel getPanel(){
        return p;
    }
}
