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
import java.util.ArrayList;
import java.util.Random;

/*
 Guarda o tempo atual e possui os geradores de números aleatórios.
 */
//Tempo Atual: int tempoAtual;
public abstract class Utilities {

    private static float tempoAtual = 0;

    public static enum unit {

        HORAS, MINUTOS, SEGUNDOS
    };
    private static unit timeUnit = unit.HORAS;
    private static float delayAmount = 0;
    private static Random rand = new Random();

    public static float getTempo() {
        return tempoAtual;
    }

    public static void proxTempo() {
        tempoAtual++;
    }

    public static void proxTempo(float skip) {
        tempoAtual = skip;
    }

    public static void setUnit(unit newUnit) {
        timeUnit = newUnit;
    }

    public static unit getUnit() {
        return timeUnit;
    }

    //Equações
    public static float EXPO(float E) {
        return (float) ((-1 / E) * Math.log(1 - rand.nextFloat()));
    }

    public static float NORM(float M, float DP) {
        float random1 = rand.nextFloat();
        float random2 = rand.nextFloat();
        float Z = (float) (Math.sqrt((-2 * Math.log((double) random1))) * Math.cos(2 * Math.PI * random2));

        return rand.nextFloat() * 10;
    }

    public static float TRIA(float a, float b, float c) {
        float random = rand.nextFloat();
        if (random < ((c - a) / (b - a))) {
            return (float) (a + Math.sqrt(random * (b - a) * (c - a)));
        } else {
            return (float) (c - Math.sqrt((1 - random) * (c - b) * (c - a)));
        }
    }

    public static float UNIF(float a, float b) {
        return a + rand.nextFloat() * (b - a);
    }

    public static float CONST(float cons) {
        return cons;
    }

}
