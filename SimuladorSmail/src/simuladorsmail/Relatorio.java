/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorsmail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Abrams
 */

/*
 Classe responsável por gerar o ralátório final
 a) Número de Mensagens no Sistema (máximo e média): Ao longo do período simulado, 
 o número de mensagens presentes no servidor se altera, podendo assumir diversos valores discretos. 
 Os valores mínimos e máximos são respectivamente o menor e maior valor observado ao longo da simulação. 
 Para se obter uma estatística do valor médio, é necessário um acompanhamento (ao longo do período simulado) dos diversos valores assumidos e dos períodos de tempo ao longo dos quais estes permaneceram constantes. Em outras palavras, estas são variáveis dependentes do tempo. Sua obtenção requer o cálculo de uma média ponderada, cujos pesos serão parcelas (percentuais) do tempo total de observação (tempo simulado) nos quais a variável n.º de mensagens no sistema, permaneceu em determinado estado.
 b) Taxa Média de Ocupação dos Centros: Esta também é uma estatística dependente do tempo.
 c) Tempo de Transito das Mensagens no Sistema (mínimo; máximo e médio): O tempo de uma mensagem no 
 sistema é calculado desde o momento de sua chegada até seu despacho ao seu destino.
 d) Contadores de Mensagens Despachadas (trata-se de um simples acumulador).
 e) Contador de Mensagens por tipo (mais acumuladores).


 */
public abstract class Relatorio {
    /*
     [numero máximo |0, numero médio |1, numero de mensagens geradas |2 
     taxa média de ocupação centro L |3, taxa média de ocupação centro R |4,
     tempo transito minimo |5, tempo de transito máximo |6, tempo de transito médio |7, 
     numero de mansagens despachadas |8,
     numero mensagens LL |9, numero mensagens LR |10, numero mensagens RR |11, numero mensagens RL |12
     somaDosTemposNaFilaL |13, somaDosTemposNaFilaR |14
     ]
     */

    public static void geraRelatorio(float[] valores) {
        try {
            Scanner ler = new Scanner(System.in);

            FileWriter arq = new FileWriter(new File("Relatorio.txt"));
            PrintWriter gravarArq = new PrintWriter(arq);
            //Gravar texto do arquivo:
            gravarArq.printf("-----Relatório Simulação SMAIL:-----");
            gravarArq.printf("%n");
            gravarArq.printf("Número de mensagens despachadas: "+valores[8]);
            gravarArq.printf("%n");
            gravarArq.printf("Número de mensagens geradas: "+valores[2]);
            gravarArq.printf("%n");
            gravarArq.printf("Número de mensagens por tipo: LL: "+valores[9]
                              +" LR: "+valores[10]+" RR: "+valores[11]+" RL: "+valores[12]);
            gravarArq.printf("%n");
            gravarArq.printf("Número de mensagens ao longo do tempo Máximo: "+valores[0]
                              +" Médio: "+valores[1]);
            gravarArq.printf("%n");
            gravarArq.printf("Tempo de transito mínimo: "+valores[5]+" Máximo: "+valores[6]+" Médio: "+valores[7]);
            gravarArq.printf("%n");
            gravarArq.printf("Soma dos tempos nas filas do centro:  L: "+valores[13]+" R: "+valores[14]);
            gravarArq.printf("%n");
            //Fechando arquivo:
            arq.close();
            
            java.awt.Desktop.getDesktop().open(new File("Relatorio.txt"));//Testar//
            
        } catch (IOException ex) {
            System.out.printf("Relatório.TXT não pode ser gerado!");
        }
        
        return;
    }

}
