/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Luca
 */
public class Simbolo{
    
    private BufferedImage image;
    private int x;
    private int y;
    private int dx;
    private int dy;
    
    private int[] dest; 
    
    public Simbolo(int x, int y, int dx, int dy, int destx, int desty){
        try {
            image = ImageIO.read(getClass().getResourceAsStream("Entity.png"));
        } catch (IOException ex) {
            Logger.getLogger(Simbolo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.x = x;
        this.y = y;
        dest = new int[2];
        dest[0] = destx;
        dest[1] = desty;
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public void move(){
        this.x += dx;
        this.y += dy;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int[] getDest(){
        return dest;
    }
}
