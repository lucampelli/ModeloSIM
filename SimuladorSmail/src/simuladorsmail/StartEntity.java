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
import java.util.ArrayList;

public class StartEntity extends Module{

    private int nextRem = 0;
    private int nextLoc = 0;
    private int criados;
    private int maxCriacao;
    private Random rand;
    private int maxPlayTime;
    
    public StartEntity(int maxCriacao){
        criados = 0;
        this.maxCriacao = maxCriacao;
        rand = new Random();
        doRun();
    }
    
    @Override
    public void receiveEnt(Entity e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doRun() {                                       //Schedule next creation
        System.out.println("Rem:" + nextRem);
        System.out.println("Loc:" + nextLoc);
        System.out.println(Utilities.getTempo());
        if(criados <= maxCriacao){
            char rem;                                                           //Remetente
            
            if(Utilities.getTempo() >= nextRem){                                //Bateu horario de criar nova entidade com destinatario remoto
                nextRem += Utilities.EXPO(0.6f);                                //Schedule next creation
                
                if(rand.nextFloat() > 0.33f){
                    rem = 'r';
                } else {                                                        //Chance aleatória de o remetente ser local 1/3 ou remoto 2/3
                    rem = 'l';
                }
                atual = new Entity('r',rem);                                    //cria nova mensagem     
                System.out.println("Atual destinatario " + atual.getDestiantario());
                System.out.println("Atual remetente " + atual.getRemetente());
                criados++;
                sendEnt(atual);                                                 //envia
            }
            
            if(Utilities.getTempo() >= nextLoc){                                //Bateu horario de criar nova entidade com destinatario local
                nextLoc += Utilities.EXPO(0.5f);                                //Schedule next creation
                
                if(rand.nextFloat() > 0.33f){
                    rem = 'r';
                } else {                                                        //Chance aleatória de o remetente ser local 1/3 ou remoto 2/3
                    rem = 'l';
                }
                atual = new Entity('l',rem);                                    //cria nova mensagem
                criados++;
                System.out.println("Atual destinatario " + atual.getDestiantario());
                System.out.println("Atual remetente " + atual.getRemetente());
                sendEnt(atual);                                                 //envia
            }   
            
            atual = null;                                                       //nulifica o atual
        }
        register();                                                             //quando terminar registra tudo
    }

    @Override
    public void register() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNext(Module next) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrev(Module prev) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
