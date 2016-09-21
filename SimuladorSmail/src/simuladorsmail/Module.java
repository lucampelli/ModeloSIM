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

//SuperClasse
public abstract class Module {
    Module next;
    Module prev;
    
    
    public abstract void receiveEnt(Entity e);
    public void sendEnt(Entity e){
        next.receiveEnt(e);
    }
    public abstract void doRun(Entity e);
    public abstract void register();
}
