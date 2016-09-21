/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorsmail;

/**
 *
 * @author Abrams
 */

/*
Entidade do sistema, armazena informações nescessárias para a simulação.
*/
//Tipo: Char Destinatario = 'L'||'R';
//      Char Remetente    = 'L'||'R';
//Quantidade de adiamentos ocorridos: int QuantAdiamentos;
//Boolean sucesso;
//Boolean falha;
//Tempo no Sistema;

public class Entity {
    private char destinatario;
    private char remetente;
    private int adiamentos;
    private boolean falha;
    private boolean sucesso;
    private int tempoNoSistema;
    
    public Entity(char destinatario, char remetente){
        this.destinatario = destinatario;
        this.remetente = remetente;
    }
    
    public char getDestiantario(){
        return destinatario;
    }
    
    public char getRemetente(){
        return remetente;
    }
    
    public int getAdiamentos(){
        return adiamentos;
    }
    
    public boolean getFalha(){
        return falha;
    }
    
    public boolean getSucesso(){
        return sucesso;
    }
    
    public int getTempoNoSistema(){
        return tempoNoSistema;
    }
    
    public void setDestinatario(char input){
        this.destinatario = input;
    }
    
    public void setRemetente(char input){
        this.remetente = input;
    }
    
    public void addAdiamentos(){
        adiamentos ++;
    }
    
    public void falha(){
        falha = true;
    }
    
    public void sucesso(){
        sucesso = true;
    }
}
