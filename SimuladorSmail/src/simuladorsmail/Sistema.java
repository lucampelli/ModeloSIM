/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorsmail;

//For testing purposes, or maybe not

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sistema {
    public Sistema(){
        int maxPlayTime = 100;
        StartEntity e1 = new StartEntity(10);
        while(Utilities.getTempo() < maxPlayTime){
            e1.doRun();
            Utilities.proxTempo();
            try {
                sleep(100);
            } catch (InterruptedException ex) {
               ex.printStackTrace();
            }
        }
        
    }
}
