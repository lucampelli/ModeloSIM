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
    
    public static enum tipoDeEvento {CRIACAOL,CRIACAOR,SELECAO,SERVICO,SAIDA,FINAL} 
    private float tempo = 0;
    private tipoDeEvento tipo;
    private Module responsa;
    private Entity entidade;
    
    
    public Evento (tipoDeEvento Tipo, float tempo, Module responsavel, Entity e){
        this.tipo = Tipo;
        this.tempo = tempo;
        this.responsa = responsavel;
        this.entidade = e;
    }
    
    public Module getResponsavel(){
        return responsa;
    }
    
    public tipoDeEvento getTipo(){
        return tipo;
    }
    
    public float getTempo(){
        return tempo;
    }
    
    public Entity getEntity(){
        return entidade;
    }
    
}
