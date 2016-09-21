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
Guarda o tempo atual e possui os geradores de números aleatórios.
*/
//Tempo Atual: int tempoAtual;

public abstract class Utilities {
    private static int tempoAnterior = 0;
    private static int tempoAtual = 0;
    private static char unit = 'h'; //horas, minutos, segundos.
    private static float delayAmount = 0;
    
    public static int getTempo(){
        return tempoAtual;
    }
    public static void proxTempo(){
        tempoAnterior = tempoAtual;
        tempoAtual++;
    }
    public static void proxTempo(int skip){
        tempoAnterior = tempoAtual;
        tempoAtual += skip;
    }
    public static int getTempoAnt(){
        return tempoAnterior;
    }
    public static void setUnit(char newUnit){
        unit = newUnit;
    }
    public static char getUnit(){
        return unit;
    }
    public static void setDelayAmount(float delay){
        delayAmount = delay;
    }
    public static float NORM(float M, float DP){
        return 0f;
    }
    public static float TRIA(float S, float MID, float E){
        return 0f;
    }
    public static float UNIF(float U, float S){
        return 0f;
    }
    public static float CONST(float cons){
        return cons;
    }
    
           
}
