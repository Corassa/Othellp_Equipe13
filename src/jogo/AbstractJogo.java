/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.List;

/**
 *
 * @author iuri
 */
abstract public class AbstractJogo {

    public int tam = 0;

    //0 = nao acabou, 1 = vitoria, 2 = empate
    abstract public int teste_terminal(int[][] tab, int marcaTabuleiro);

    // 0 = ok, -1 = Casa preenchida, -2 = jogada invalida
    abstract public int validar_jogada(int[][] tabOriginal, Casa jogada, AbstractPlayer jogador);

    abstract public int[][] efetuar_jogada(int[][] tabOriginal, Casa jogada, AbstractPlayer jogador);

    abstract public int[][] tabuleiro_inicial();

    abstract public List<Jogada> getJogadasValidas(int[][] tabOriginal, int marcaTabuleiro);

}
