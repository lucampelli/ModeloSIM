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

public class EndEntity extends Module{

    //ints
    private int numLeituras = 0;
    private int somaDosTemposTotais = 0;
    private int somaDosAdiamentos = 0;
    private int somaDosTemposNaFila = 0;
    
    
    
    //floats
    private float mediaDosTemposTotais = 0;
    private float mediaDosAdiamentos = 0;
    private float mediaDosTemposNaFila = 0;
    
    
    public void Register(Entity e){
        numLeituras++;
        somaDosTemposTotais += e.getTempoNoSistema();
        somaDosTemposNaFila += e.getTempoFila();
        somaDosAdiamentos += e.getAdiamentos();
        
    }
    
    public void END(){
        mediaDosTemposTotais = somaDosTemposTotais/numLeituras;
        mediaDosAdiamentos = somaDosAdiamentos/numLeituras;
        mediaDosTemposNaFila = somaDosTemposNaFila/numLeituras;
    }


}
