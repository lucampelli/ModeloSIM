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
Módulo de início o qual cria uma entidade e encaminha no sistema armazenando a quantidade
de entidades total e de cada tipo criadas.
Chances:
-Tempo entre chegadas:
Origem|TEC
Local |EXPO (0,5)
Remota|EXPO (0,6)
*/
//Número de Chegadas: int[Tipos] NumChegadas;[total][QuantTipo1][QuantTipo2][...];
import java.util.Random;

public class StartEntity extends Module{

    Sistema sisRef;
    Random rand;
    
    public StartEntity(Sistema sis){
        sisRef = sis;
        rand = new Random();
    }
    
    @Override
    public void receiveEnt(Entity e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doRun() {
    
    }

    @Override
    public void register() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Entity createEvent(boolean local){
        Entity newE;
        boolean random = rand.nextBoolean();
        if(local){
            if(random){
                newE = new Entity('l','r');
            } else {
                newE = new Entity('l','l');
            }
            sisRef.createEvent(Evento.tipoDeEvento.CRIACAOL,(Utilities.getTempo() + Utilities.EXPO(0.5f)), this, null);
        } else {
            if(random){
                newE = new Entity('r','r');
            } else {
                newE = new Entity('r','l');
            }
            sisRef.createEvent(Evento.tipoDeEvento.CRIACAOR,(Utilities.getTempo() + Utilities.EXPO(0.6f)), this, null);
        }
        
        sisRef.createEvent(Evento.tipoDeEvento.SELECAO, Utilities.getTempo(), null, newE);
        
        return newE;
        
    }
    
    
}
