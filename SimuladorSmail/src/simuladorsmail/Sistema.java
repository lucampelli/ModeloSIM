/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorsmail;

//For testing purposes, or maybe not

public class Sistema {
    public Sistema(){
        System.out.println(Utilities.CONST(15));
        System.out.println(Utilities.NORM(0, 0));
        System.out.println(Utilities.TRIA(0.1f, 0.2f, 0.34f));
        System.out.println(Utilities.UNIF(0.2f, 0.55f));
        Utilities.proxTempo(13);
        Utilities.proxTempo(13);
        Utilities.setDelayAmount(2);
        Utilities.setUnit('m');
        System.out.println(Utilities.getTempo());
        System.out.println(Utilities.getTempoAnt());
        System.out.println(Utilities.getUnit());
        
    }
}
