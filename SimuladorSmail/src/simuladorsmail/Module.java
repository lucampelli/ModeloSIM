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
    Entity atual;
    
    public abstract void receiveEnt(Entity e);
    public void sendEnt(Entity e){
        if(next != null)
            next.receiveEnt(e);
    }
    public abstract void doRun();
    public abstract void register();
    public abstract void setNext(Module next);
    public abstract void setPrev(Module prev);
}
