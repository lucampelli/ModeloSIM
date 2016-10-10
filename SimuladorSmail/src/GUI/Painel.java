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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Luca
 */
public class Painel extends JPanel implements Runnable {

    public static int ALTURA = 420;
    public static int LARGURA = 745;

    private Thread thread;
    private BufferedImage image;
    private Graphics2D g;

    private BufferedImage BI;

    private int FPS = 60;
    private long tempo = 1000 / FPS;

    private static int ServLocOcc;
    private static int ServLocQ;
    private static int ServRemOcc;
    private static int ServRemQ;
    private static int St2Re;
    private static int Re2Sml;
    private static int Re2Smr;
    private static int Sm2Dis;

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
        g.drawString(ServLocOcc + "", 433, 28);
        g.drawString(ServLocQ + "", 513, 83);
        g.drawString(ServRemOcc + "", 435, 310);
        g.drawString(ServRemQ + "", 514, 362);
        g.drawString(St2Re + "", 132, 125);
        g.drawString(Re2Sml + "", 318, 140);
        g.drawString(Re2Smr + "", 325, 305);
        g.drawString(Sm2Dis + "", 600, 200);
        g.drawString("TempoAtual: " + Utilities.getTempo(), 15, 390);
    }

    public void drawToScreen() {
        Graphics g2 = getGraphics();
        if (g2 != null) {
            g2.drawImage(image, 0, 0, null);
        }
    }

    public static void setLStuff(int LO, int LQ) {
        ServLocOcc = LO;
        ServLocQ = LQ;

    }

    public static void setRStuff(int RO, int RQ) {
        ServRemOcc = RO;
        ServRemQ = RQ;
    }

    public static void setWays(int[] ways) {
        St2Re = ways[0];
        Re2Sml = ways[1];
        Re2Smr = ways[2];
        Sm2Dis = ways[3];
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
                    Logger.getLogger(Painel.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
