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
import java.util.Random;

/*
 Guarda o tempo atual e possui os geradores de números aleatórios.
 */
//Tempo Atual: int tempoAtual;
public abstract class Utilities {

    private static float tempoAtual = 0;
    private static int creationLEq = 0;
    private static float[] creationLParam = new float[3];
    private static int creationREq = 0;
    private static float[] creationRParam = new float[3];
    private static float[] props = new float[4];   //[ll, lr, rl, rr]
    private static float[] sucPropsLL = new float[3]; //[s, f, a]
    private static float[] sucPropsLR = new float[3]; //[s, f, a]
    private static float[] sucPropsRL = new float[3]; //[s, f, a]
    private static float[] sucPropsRR = new float[3]; //[s, f, a]

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

    public static void setDestiny(Entity e) {
        float dest = rand.nextFloat();
        
        float[] central;
        
        if(e.getDestinatario() == 'l'){
            if(e.getRemetente() == 'l'){
                central = sucPropsLL;
            } else {
                central = sucPropsLR;
            }
        } else {
            if(e.getRemetente() == 'l'){
                central = sucPropsRL;
            } else {
                central = sucPropsRR;
            }
        }
        
        if (dest < central[0]) {
            e.setAdiamento(true);
        }
        if (dest < central[1] + central[0]) {
            e.falha();
        }
        if (dest < central[2] + central[1] + central[0]) {
            e.sucesso();
        }
    }

    public static float getTESR(Entity e) {
        if (e.getRemetente() == 'l') {
            if (e.getDestinatario() == 'l') {
                if (e.getSucesso()) {
                    return 0.12f;
                }
                if (e.getAdiamento()) {
                    return 0.11f;
                }
                if (e.getFalha()) {
                    return 0.14f;
                }
            } else {
                if (e.getSucesso()) {
                    return 0.12f;
                }
                if (e.getAdiamento()) {
                    return 0.15f;
                }
                if (e.getFalha()) {
                    return 0.13f;
                }
            }
        } else {
            if (e.getDestinatario() == 'l') {
                if (e.getSucesso()) {
                    return 0.12f;
                }
                if (e.getAdiamento()) {
                    return 0.11f;
                }
                if (e.getFalha()) {
                    return 0.14f;
                }
            } else {
                if (e.getSucesso()) {
                    return 0.16f;
                }
                if (e.getAdiamento()) {
                    return 0.16f;
                }
                if (e.getFalha()) {
                    return 0.13f;
                }
            }
        }
        return 0.10f;
    }

    public static float getTESS(Entity e) {
        if (e.getRemetente() == 'l') {
            if (e.getDestinatario() == 'l') {
                if (e.getSucesso()) {
                    NORM(0.55f, 0.05f);
                }
                if (e.getAdiamento()) {
                    UNIF(0.06f, 0.15f);
                }
                if (e.getFalha()) {
                    TRIA(0.02f, 0.05f, 0.012f);
                }
            } else {
                if (e.getSucesso()) {
                    NORM(0.65f, 0.04f);
                }
                if (e.getAdiamento()) {
                    TRIA(0.05f, 0.07f, 0.010f);
                }
                if (e.getFalha()) {
                    UNIF(0.16f, 0.25f);
                }
            }
        } else {
            if (e.getDestinatario() == 'l') {
                if (e.getSucesso()) {
                    UNIF(0.03f, 011f);
                }
                if (e.getAdiamento()) {
                    NORM(0.72f, 0.09f);
                }
                if (e.getFalha()) {
                    NORM(0.46f, 0.05f);
                }
            } else {
                if (e.getSucesso()) {
                    UNIF(0.09f, 0.18f);
                }
                if (e.getAdiamento()) {
                    NORM(0.63f, 0.04f);
                }
                if (e.getFalha()) {
                    TRIA(0.08f, 0.15f, 0.022f);
                }
            }
        }
        return 0.20f;
    }

    public static float nextCreationTime(boolean local) {
        if (local) {
            switch (creationLEq) {
                case 0:
                    return tempoAtual + EXPO(creationLParam[0]);
                case 1:
                    return tempoAtual + NORM(creationLParam[0], creationLParam[1]);
                case 2:
                    return tempoAtual + TRIA(creationLParam[0], creationLParam[1], creationLParam[2]);
                case 3:
                    return tempoAtual + UNIF(creationLParam[0], creationLParam[1]);
                default:
                    return tempoAtual + CONS(creationLParam[0]);
            }
        } else {
            switch (creationREq) {
                case 0:
                    return tempoAtual + EXPO(creationRParam[0]);
                case 1:
                    return tempoAtual + NORM(creationRParam[0], creationRParam[1]);
                case 2:
                    return tempoAtual + TRIA(creationRParam[0], creationRParam[1], creationRParam[2]);
                case 3:
                    return tempoAtual + UNIF(creationRParam[0], creationRParam[1]);
                default:
                    return tempoAtual + CONS(creationRParam[0]);
            }
        }
    }

    public static String nextCreation() {
        float r = rand.nextFloat();
        if (r < props[0]) {
            return "ll";
        }
        if (r < props[1] + props[0]) {
            return "lr";
        }
        if (r < props[2] + props[1] + props[0]) {
            return "rl";
        }
        if (r < props[3] + props[2] + props[1] + props[0]) {
            return "rr";
        }
        return "ll";
    }

    public static void setCreationLTimes(int eq, float[] param) {
        creationLEq = eq;
        creationLParam = param;
    }
    
    public static void setCreationRTimes(int eq, float[] param) {
        creationREq = eq;
        creationRParam = param;
    }

    public static void setProps(float[] prop) {
        props = prop;
    }
    
    public static void setSucPropsLL(float[] prop){
        sucPropsLL = prop;
    }
    public static void setSucPropsLR(float[] prop){
        sucPropsLR = prop;
    }
    public static void setSucPropsRL(float[] prop){
        sucPropsRL = prop;
    }
    public static void setSucPropsRR(float[] prop){
        sucPropsRR = prop;
    }

    //Equações
    public static float EXPO(float E) { //0
        return (float) ((-1 / E) * Math.log(1 - rand.nextFloat()));
    }

    public static float NORM(float M, float DP) { //1
        float random1 = rand.nextFloat();
        float random2 = rand.nextFloat();
        float Z = (float) (Math.sqrt((-2 * Math.log((double) random1))) * Math.cos(2 * Math.PI * random2));

        return rand.nextFloat() * 10;
    }

    public static float TRIA(float a, float b, float c) { //2
        float random = rand.nextFloat();
        if (random < ((c - a) / (b - a))) {
            return (float) (a + Math.sqrt(random * (b - a) * (c - a)));
        } else {
            return (float) (c - Math.sqrt((1 - random) * (c - b) * (c - a)));
        }
    }

    public static float UNIF(float a, float b) { //3
        return a + rand.nextFloat() * (b - a);
    }

    public static float CONS(float cons) { //4
        return cons;
    }

}
