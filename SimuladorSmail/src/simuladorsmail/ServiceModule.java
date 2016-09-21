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

public class ServiceModule{

}
