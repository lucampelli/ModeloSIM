/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorsmail;

/**
 *
 * @author Luca
 */
public class Evento {
    
    public static enum tipoDeEvento {CRIACAOL,CRIACAOR,SELECAO,FILA,SERVICO,ADIAMENTO,SAIDA,SEIZE,RELEASE} 
    private int tempo = 0;
    private tipoDeEvento tipo;
    private Module responsa;
    
    
    public Evento (tipoDeEvento Tipo, int tempo, Module responsavel){
        this.tipo = Tipo;
        this.tempo = tempo;
        this.responsa = responsavel;
    }
    
    public Module getResponsavel(){
        return responsa;
    }
    
    public tipoDeEvento getTipo(){
        return tipo;
    }
    
    public int getTempo(){
        return tempo;
    }
    
}
