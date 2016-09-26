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
    
    public static enum tipoDeEvento {CRIACAOL,CRIACAOR,SELECAO,FILA,SERVICO,ADIAMENTO,SAIDA} 
    private int tempo = 0;
    private tipoDeEvento tipo;
    
    
    public Evento (tipoDeEvento Tipo, int tempo){
        this.tipo = Tipo;
        this.tempo = tempo;
    }
    
    public tipoDeEvento getTipo(){
        return tipo;
    }
    
    public int getTempo(){
        return tempo;
    }
    
}
