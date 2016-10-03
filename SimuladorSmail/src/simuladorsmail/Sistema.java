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

    public Sistema() {
        filaDeEventos = new ArrayList<Evento>();
        se = new StartEntity(this);
        rm = new ReceptModule(this);
        sml = new ServiceModule(this);
        smr = new ServiceModule(this);
        ee = new EndEntity();

        GUI.Input input = new GUI.Input();
        input.setVisible(true);
        input.setSis(this);
    }
    
    public void start(){
        createEvent(Evento.tipoDeEvento.CRIACAOL, Utilities.nextCreationTime(true), null);
        createEvent(Evento.tipoDeEvento.CRIACAOR, Utilities.nextCreationTime(false), null);
        
        while (!filaDeEventos.isEmpty()) {
            System.out.println("TempoAtual :" + Utilities.getTempo());
            printFilaDeEventos();
            Evento atual = getNextEvent();
            Run(atual);
        }
    }

    public void createEvent(Evento.tipoDeEvento tipo, float tempo, Entity e) {
        filaDeEventos.add(new Evento(tipo, tempo, e));
    }

    public Evento getNextEvent() {

        Evento chosen = filaDeEventos.get(0);
        float tempo = chosen.getTempo();

        for (Evento e : filaDeEventos) {
            if (e.getTempo() < tempo) {
                chosen = e;
                tempo = e.getTempo();
            }
        }
        Utilities.proxTempo(tempo);
        filaDeEventos.remove(chosen);
        return chosen;
    }

    public void Run(Evento e) {
        if (e.getTipo() == Evento.tipoDeEvento.CRIACAOL) {
            se.createEvent(true);
            System.out.println("Criacao Local");
        }
        if (e.getTipo() == Evento.tipoDeEvento.CRIACAOR) {
            se.createEvent(false);
            System.out.println("Criacao Remoto");
        }
        if (e.getTipo() == Evento.tipoDeEvento.SELECAO) {
            rm.createEvent(e.getEntity());
            System.out.println("Receipt Event");
        }
        if (e.getTipo() == Evento.tipoDeEvento.SERVICO) {
            if (e.getEntity().getDestinatario() == 'l') {
                sml.createEventEntrada(e.getEntity());
                System.out.println("Entry Event Local");
            } else {
                smr.createEventEntrada(e.getEntity());
                System.out.println("Entry Event Remote");
            }
            
        }
        if (e.getTipo() == Evento.tipoDeEvento.SAIDA) {
            if (e.getEntity().getDestinatario() == 'l') {
                sml.createEventSaida(e.getEntity());
                System.out.println("Exit Event Local");
            } else {
                smr.createEventSaida(e.getEntity());
                System.out.println("Exit Event Remote");
            }
        }
        if (e.getTipo() == Evento.tipoDeEvento.FINAL) {
            ee.Register(e.getEntity());
            System.out.println("Dispose Event");
        }
    }

    public void printFilaDeEventos() {
        for (Evento e : filaDeEventos) {
            System.out.println(e.getTipo() + ", " + e.getTempo());
        }
    }

    public ServiceModule getServiceModule(boolean local) {
        if (local) {
            return sml;
        } else {
            return smr;
        }
    }
    
    public void setTECL(int eq, float[] param){
        Utilities.setCreationLTimes(eq, param);
    }
    
    public void setTECR(int eq, float[] param){
        Utilities.setCreationRTimes(eq, param);
    }
    
    public void setProps(float[] props){
       Utilities.setProps(props);
    }
    
    public void setSucPropsLL(float[] prop){
        Utilities.setSucPropsLL(prop);
    }
    public void setSucPropsLR(float[] prop){
        Utilities.setSucPropsLR(prop);
    }
    public void setSucPropsRL(float[] prop){
        Utilities.setSucPropsRL(prop);
    }
    public void setSucPropsRR(float[] prop){
        Utilities.setSucPropsRR(prop);
    }
    
}
