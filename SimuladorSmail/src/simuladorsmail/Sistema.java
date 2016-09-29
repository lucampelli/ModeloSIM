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
    
    private ArrayList<Evento> filaDeEventos;
    private StartEntity se;
    private ReceptModule rm;
    private ServiceModule sml;
    private ServiceModule smr;
    private EndEntity ee;
    
    public Sistema(){
        filaDeEventos = new ArrayList<Evento>();
        se = new StartEntity(this);
        rm = new ReceptModule(this);
        sml = new ServiceModule(this);
        smr = new ServiceModule(this);
        ee = new EndEntity();
        
        createEvent(Evento.tipoDeEvento.CRIACAOL, Utilities.EXPO(0.5f), null, null);
        createEvent(Evento.tipoDeEvento.CRIACAOR, Utilities.EXPO(0.6f), null, null);
        
        while (!filaDeEventos.isEmpty()){
            System.out.println("TempoAtual :" + Utilities.getTempo());
            printFilaDeEventos();
            Evento atual = getNextEvent();
            Run(atual);
        }
    }
    
    public void createEvent(Evento.tipoDeEvento tipo, float tempo, Module r, Entity e){
        filaDeEventos.add(new Evento(tipo,tempo,r,e));
    }
    
    public Evento getNextEvent(){
        
        Evento chosen = filaDeEventos.get(0);
        float tempo = chosen.getTempo();
        
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
    
    public void Run(Evento e){
        if(e.getTipo() == Evento.tipoDeEvento.CRIACAOL){
            se.createEvent(true);
        }
        if(e.getTipo() == Evento.tipoDeEvento.CRIACAOR){
            se.createEvent(false);
        }
        if(e.getTipo() == Evento.tipoDeEvento.SELECAO){
            rm.createEvent(e.getEntity());
        }
        if(e.getTipo() == Evento.tipoDeEvento.SERVICO){
            
        }
        if(e.getTipo() == Evento.tipoDeEvento.SAIDA){
            
        }
        if(e.getTipo() == Evento.tipoDeEvento.FINAL){
            
        }
    }
    
    
    public void printFilaDeEventos(){
        for(Evento e : filaDeEventos){
            System.out.println(e.getTipo() + ", " + e.getTempo());
        }
    }
    
    public ServiceModule getServiceModule(boolean local){
        if(local){
            return sml;
        } else {
            return smr;
        }
    }
}
