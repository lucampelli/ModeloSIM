/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorsmail;

//For testing purposes, or maybe not
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import GUI.Painel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sistema implements Runnable {

    private ArrayList<Evento> filaDeEventos;
    private StartEntity se;
    private ReceptModule rm;
    private ServiceModule sml;
    private ServiceModule smr;
    private EndEntity ee;
    private Painel painel;
    private int spd = 0;
    private boolean paused = false;

    public Sistema() {
        filaDeEventos = new ArrayList<Evento>();
        se = new StartEntity(this);
        rm = new ReceptModule(this);
        sml = new ServiceModule(this);
        smr = new ServiceModule(this);
        ee = new EndEntity(this);

        GUI.Inicial inicial = new GUI.Inicial();
        inicial.setVisible(true);
        inicial.setSis(this);
    }

    @Override
    public void run() {
        createEvent(Evento.tipoDeEvento.CRIACAOL, Utilities.nextCreationTime(true), null);
        createEvent(Evento.tipoDeEvento.CRIACAOR, Utilities.nextCreationTime(false), null);

        while (!filaDeEventos.isEmpty()) {
            try {
                //System.out.println("TempoAtual :" + Utilities.getTempo());
                //printFilaDeEventos();
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!paused){
                Evento atual = getNextEvent();
                RunEvent(atual);
            }
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

    public void RunEvent(Evento e) {
        if (e.getTipo() == Evento.tipoDeEvento.CRIACAOL) {
            se.createEvent(true);
            //System.out.println("Criacao Local");
        }
        if (e.getTipo() == Evento.tipoDeEvento.CRIACAOR) {
            se.createEvent(false);
            //System.out.println("Criacao Remoto");
        }
        if (e.getTipo() == Evento.tipoDeEvento.SELECAO) {
            rm.createEvent(e.getEntity());
            //System.out.println("Receipt Event");
        }
        if (e.getTipo() == Evento.tipoDeEvento.SERVICO) {
            if (e.getEntity().getDestinatario() == 'l') {
                sml.createEventEntrada(e.getEntity());
                //System.out.println("Entry Event Local");
            } else {
                smr.createEventEntrada(e.getEntity());
                //System.out.println("Entry Event Remote");
            }

        }
        if (e.getTipo() == Evento.tipoDeEvento.SAIDA) {
            if (e.getEntity().getDestinatario() == 'l') {
                sml.createEventSaida(e.getEntity());
                //System.out.println("Exit Event Local");
            } else {
                smr.createEventSaida(e.getEntity());
                //System.out.println("Exit Event Remote");
            }
        }
        if (e.getTipo() == Evento.tipoDeEvento.FINAL) {
            ee.Register(e.getEntity());
            //System.out.println("Dispose Event");
        }
        if (e.getTipo() == Evento.tipoDeEvento.TERMINO) {
            END();
        }
        
        try {
            Painel.setLStuff(sml.getFilaSize(), sml.getOcupacao());
            Painel.setRStuff(smr.getFilaSize(), smr.getOcupacao());
            Painel.setWays(countEvents());
            sleep(spd);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
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

    public void setTempoSimulacao(float time) {
        if (time != 0) {
            createEvent(Evento.tipoDeEvento.TERMINO, time, null);
        }
    }
    
    public void setDelay(int delay){
        if(delay == 1){
            spd = 100;
        } else
        if(delay == 2){
            spd = 500;
        } else
        if(delay == 3){
            spd = 800;
        }
    }

    public void setPanel(Painel panel) {
        painel = panel;
    }

    public void setTECL(int eq, float[] param) {
        Utilities.setCreationLTimes(eq, param);
    }

    public void setTECR(int eq, float[] param) {
        Utilities.setCreationRTimes(eq, param);
    }

    public void setProps(float[] props) {
        Utilities.setProps(props);
    }

    public void setSucPropsLL(float[] prop) {
        Utilities.setSucPropsLL(prop);
    }

    public void setSucPropsLR(float[] prop) {
        Utilities.setSucPropsLR(prop);
    }

    public void setSucPropsRL(float[] prop) {
        Utilities.setSucPropsRL(prop);
    }

    public void setSucPropsRR(float[] prop) {
        Utilities.setSucPropsRR(prop);
    }

    public void setServerNum(int num) {
        sml.setServerNum(num);
        smr.setServerNum(num);
    }

    public void pause(){
        paused = !paused;
    }
    
    public void END() {
        ee.END();
        System.exit(0);
    }
    
    public StartEntity getStartEntity(){
        return se;
    }
    
    public ServiceModule getSeviceModuleL (){
        return sml;
    }
    
    public ServiceModule getServiceModuleR (){
        return smr;
    }
    
    public int[] countEvents(){
        int i[] = new int[4];
        for(Evento e : filaDeEventos){
            if(e.getTipo() == Evento.tipoDeEvento.SELECAO){
                i[0]++;
            }
            if(e.getTipo() == Evento.tipoDeEvento.SERVICO){
                if(e.getEntity().getDestinatario() =='l'){
                    i[1]++;
                } else {
                    i[2]++;
                }
            }
            if(e.getTipo() == Evento.tipoDeEvento.FINAL){
                i[3]++;
            }
        }
        return i;
    }

}
