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

    private float periodo;
    private int criados;
    private int maxCriacao;
    private Random rand;
    
    public StartEntity(int maxCriacao, int periodo){
        periodo = periodo;
        criados = 0;
        maxCriacao = maxCriacao;
        rand = new Random();
    }
    
    @Override
    public void receiveEnt(Entity e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doRun(Entity e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void register() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
