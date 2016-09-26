/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorsmail;

//For testing purposes, or maybe not

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sistema {
    
    public ArrayList<Evento> filaDeEventos;
    
    public Sistema(){
        filaDeEventos = new ArrayList<Evento>();
        
        while (!filaDeEventos.isEmpty()){
            
        }
    }
    
    public void createEvent(Evento.tipoDeEvento tipo, int tempo, Module r){
        filaDeEventos.add(new Evento(tipo,tempo,r));
    }
    
    public Evento getNextEvent(){
        
        Evento chosen = filaDeEventos.get(0);
        int tempo = chosen.getTempo();
        
        for(Evento e : filaDeEventos){
            if(e.getTempo() < tempo){
                chosen = e;
                tempo = e.getTempo();
            }
        }
        Utilities.proxTempo(tempo);
        filaDeEventos.remove(chosen);
        return chosen;
    }
}
