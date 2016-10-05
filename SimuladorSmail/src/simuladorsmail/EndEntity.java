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
 Módulo de término o qual recebe uma entidade e dá fim nela guardando a quantidade de saidas
 total e de cada tipo de entidade e seus resultados.
 */
//Número de Saidas: int[QuantTipos] NumSaidas;[total][QuantTipo1][QuantTipo2][...];
//Numero de falhas: int NumFalhas;
//Numero de Sucessos: int NumSucessos;
//Numero de Adiamentos: int NumAdiamentos;
public class EndEntity extends Module {

    //ints
    private int numLeituras = 0;
    
    private int somaDosAdiamentos = 0;
    
    private int ll = 0;
    private int lr = 0;
    private int rr = 0;
    private int rl = 0;

    private int maiorQuantidade = 0;
    private int menorQuantidade = 0;
    
    private float mediaQuantidades = 0;

    private float maiorTempo = 0;
    private float menorTempo = 1000000;
    private float mediaDosTemposTotais = 0;
    
    private float somaDosTemposTotais = 0;
    private float somaDosTemposNaFilaL = 0;
    private float somaDosTemposNaFilaR = 0;

    private Sistema sis;

    public EndEntity(Sistema sis) {
        this.sis = sis;
    }

    public void Register(Entity e) {
        numLeituras++;
        somaDosTemposTotais += e.getTempoNoSistema();
        somaDosAdiamentos += e.getAdiamentos();

        //Quantidade de entidades no sitema:
        int quantidadeAtual = sis.getStartEntity().getQuantidadeAtual() - numLeituras;
        mediaQuantidades += quantidadeAtual;
        if (quantidadeAtual > maiorQuantidade) {
            maiorQuantidade = quantidadeAtual;
        }

        //Tempo da entidade no sistema:
        if (e.getTempoNoSistema() > maiorTempo) {
            maiorTempo = e.getTempoNoSistema();
        }
        if (e.getTempoNoSistema() < menorTempo) {
            menorTempo = e.getTempoNoSistema();
        }
        //
        if (e.getDestinatario() == 'l' && e.getRemetente() == 'l') {//LL
            ll++;
            somaDosTemposNaFilaL += e.getTempoFila();

            return;
        }
        if (e.getDestinatario() == 'l' && e.getRemetente() == 'r') {//LR
            lr++;
            somaDosTemposNaFilaL += e.getTempoFila();

            return;
        }
        if (e.getDestinatario() == 'r' && e.getRemetente() == 'r') {//RR
            rr++;
            somaDosTemposNaFilaR += e.getTempoFila();

            return;
        }
        if (e.getDestinatario() == 'r' && e.getRemetente() == 'l') {//RL
            rl++;
            somaDosTemposNaFilaR += e.getTempoFila();

            return;
        }

    }

    public void END() {

        mediaDosTemposTotais = somaDosTemposTotais / numLeituras;
        mediaQuantidades = mediaQuantidades / numLeituras;
        float valores[] = new float[15];
        valores[0] = maiorQuantidade;
        valores[1] = mediaQuantidades;
        valores[2] = sis.getStartEntity().getQuantidadeAtual();
        valores[3] = sis.getSeviceModuleL().getSomatorioQuantidades() / Utilities.getTempo();
        valores[4] = sis.getServiceModuleR().getSomatorioQuantidades() / Utilities.getTempo();
        valores[5] = menorTempo;
        valores[6] = maiorTempo;
        valores[7] = mediaDosTemposTotais;
        valores[8] = numLeituras;
        valores[9] = ll;
        valores[10] = lr;
        valores[11] = rr;
        valores[12] = rl;
        valores[13] = somaDosTemposNaFilaL / (ll + lr);
        valores[14] = somaDosTemposNaFilaR / (rr + rl);
        Relatorio.geraRelatorio(valores);

    }

}
