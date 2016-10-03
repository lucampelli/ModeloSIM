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
    
    public Handler(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 800);
        //this.pack();
        this.setVisible(true);
    }
}
