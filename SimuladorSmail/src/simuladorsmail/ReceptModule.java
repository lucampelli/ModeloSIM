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
Módulo responsável por receber os e-mails e encaminhar para o Módulo de Serviço correto
dependendo do email ter destino Remoto ou Local.

Tempos:

Dúvida Tabela 5!
*/



public class ReceptModule extends Module{
    
    private Sistema sisRef;
    
    public ReceptModule(Sistema sis){
        sisRef = sis;
    }

    public void createEvent(Entity e){
        ServiceModule m = sisRef.getServiceModule(e.getDestiantario() == 'l');
        sisRef.createEvent(Evento.tipoDeEvento.SERVICO, Utilities.getTempo(), m, e);
    }
    
}
