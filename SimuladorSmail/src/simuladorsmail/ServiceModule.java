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
 Módulo responsável por encaminhar os e-mails que possui uma fila de e-mails aguardando para serem 
 encaminhadas para o destino pelos servidores, pode haver chance de adiamento e caso positivo volta
 para a fila de serviço e é adicionado a QuantAdiamentos um adiamento. Em caso de sucesso ou fracasso 
 ela é encaminhada para o EndEntity. 
 Chances:

 Direção|Sucesso|Fracasso|Adiamento
 LL     |87,00  |0,50    |12,50
 LR     |96,00  |1,50    |2,50
 RL     |96,00  |3,00    |1,00
 RR     |90,00  |1,00    |9,00

 Tempos:

 Dúvida Tabela 5!
 */
// ArrayListDeEntidades: Fila de serviço
// Quantidade de servidoes internos
import java.util.ArrayList;

public class ServiceModule extends Module {

    private static enum ocorrencia {

        ADIAMENTO, FRACASSO, SUCESSO
    };
    int servidoresOcupados = 0;
    final int quantidadeDeServidores = 10;
    private ArrayList<Entity> filaDeServico;
    private Sistema sisRef;

    public ServiceModule(Sistema sis) {
        sisRef = sis;
        filaDeServico = new ArrayList<Entity>();
    }

    public void createEventEntrada(Entity entidade) {
        if (servidoresOcupados < quantidadeDeServidores) {
            //Gera tempoPocessamento e chances de sucesso fracaso ou adiamento!
            float ts = Utilities.getTESS(entidade);//Utilities.TS();
            entidade.setTempoNaFila(Utilities.getTempo());
            sisRef.createEvent(Evento.tipoDeEvento.SAIDA, Utilities.getTempo() + ts, entidade);
            servidoresOcupados++;
        } else {
            //InsereNaFila
            entidade.setTempoEntradaFila(Utilities.getTempo()); //Passar tempo atual!
            filaDeServico.add(entidade);
        }
    }

    public void createEventSaida(Entity entidade) {
        if (entidade.getFalha() || entidade.getSucesso()) {//Fracasso || Sucesso
            sisRef.createEvent(Evento.tipoDeEvento.FINAL, Utilities.getTempo(), entidade);//Enviar para EndEntity
            servidoresOcupados--;
            if (!filaDeServico.isEmpty()) {
                sisRef.createEvent(Evento.tipoDeEvento.SERVICO, Utilities.getTempo(), filaDeServico.remove(0));
            }
            if(entidade.getFalha()){
                System.out.println("Fail");
            }
            if(entidade.getSucesso()){
                System.out.println("Sucesso");
            }
            return;
        }
        if (entidade.getAdiamento()) {//Adiamento
            entidade.addAdiamentos();
            createEventEntrada(entidade);
            Utilities.setDestiny(entidade);
            servidoresOcupados--;
            if (!filaDeServico.isEmpty()) {
                sisRef.createEvent(Evento.tipoDeEvento.SERVICO, Utilities.getTempo(), filaDeServico.remove(0));
            }
            System.out.println("Adiado");
            return;
        }
    }
}
