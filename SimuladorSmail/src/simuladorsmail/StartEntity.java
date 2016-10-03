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

    private Sistema sisRef;
    private Random rand;
    
    public StartEntity(Sistema sis){
        sisRef = sis;
        rand = new Random();
    }

    
    
    public Entity createEvent(boolean local){
        Entity newE;
        String next = Utilities.nextCreation();
        newE = new Entity(next.charAt(0),next.charAt(1));
        if(next.charAt(0) == 'l'){
            sisRef.createEvent(Evento.tipoDeEvento.CRIACAOL,(Utilities.nextCreationTime(true)), null);
        } else {
            sisRef.createEvent(Evento.tipoDeEvento.CRIACAOL,(Utilities.nextCreationTime(false)), null);
        }
        sisRef.createEvent(Evento.tipoDeEvento.SELECAO, Utilities.getTempo(), newE);
        
        return newE;
        
    }
    
    
}
