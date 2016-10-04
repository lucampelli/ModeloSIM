/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import simuladorsmail.Utilities;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Luca
 */
public class Painel extends JPanel implements Runnable{
    
    public static int ALTURA = 500;
    public static int LARGURA = 745;
    
    private Thread thread;
    private BufferedImage image;
    private Graphics2D g;
    
    private BufferedImage BI;
    
    private int FPS = 60;
    private long tempo = 1000 / FPS;

    private int ServLocOcc;
    private int ServLocQ;
    private int ServRemOcc;
    private int ServRemQ;
    
    public Painel() {
        super();
        setPreferredSize(new Dimension(LARGURA, ALTURA));
        setFocusable(true);
        requestFocus(true);
        setVisible(true);
        try {
            BI = ImageIO.read(getClass().getResourceAsStream("BackGround.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
    }

    public void init() {
        this.image = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
        this.g = (Graphics2D) image.getGraphics();
        g.setColor(Color.red);
    }

    @Override
    public void addNotify() {
        //AddNotify
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
        }
        thread.start();
    }

    public void update() {
        
    }

    public void draw(Graphics2D g) {
        g.drawImage(BI, 0, 0, null);
        g.drawString(ServLocOcc + "", 435, 50);
        g.drawString(ServLocQ + "", 500, 150);
        g.drawString(ServRemOcc + "", 435, 315);
        g.drawString(ServRemQ + "", 500, 350);
    }

    public void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
    }

    @Override
    public void run() {
        this.init();

        while (true) {

            long timerStart = System.currentTimeMillis();

            update();
            draw(g);
            drawToScreen();

            long timerEnd = System.currentTimeMillis();

            long elapsed = timerEnd - timerStart;

            if (elapsed < this.tempo) {
                try {
                    if (elapsed >= 0) {
                        thread.sleep(tempo - elapsed);
                    } else {
                        thread.sleep(tempo);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
