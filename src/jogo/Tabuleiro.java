/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import display.Display;

/**
 *
 * @author Iuri
 */
public class Tabuleiro {

    private char[] conversao = {'o', ' ', 'x'};
    private int[][] tabuleiro;
    private AbstractJogo jogo;
    private String divisor;

    public Tabuleiro(AbstractJogo jogo) {
        this.jogo = jogo;
        tabuleiro = jogo.tabuleiro_inicial();
        divisor = gerarDivisor();
    }

    public void imprimir() {
        if (!Display.ENABLE || Torneio.JOGO == 1) {
            for (int i = 0; i < getJogo().tam; i++) {
                for (int j = 0; j < getJogo().tam; j++) {
                    System.out.printf(" %c %c", getConversao()[getTabuleiro()[i][j] + 1], (j == (getJogo().tam - 1)) ? ' ' : '|');
                }
                if (i != getJogo().tam - 1) {
                    System.out.println(getDivisor());
                }
            }
            System.out.println("\r\n");
        }
    }

    public String gerarDivisor() {
        String d = new String("\r\n");
        for (int i = 0; i < (getJogo().tam - 1); i++) {
            d += "---+";
        }
        d += "---";
        return d;
    }

    public char[] getConversao() {
        return conversao;
    }

    public void setConversao(char[] conversao) {
        this.conversao = conversao;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public AbstractJogo getJogo() {
        return jogo;
    }

    public void setJogo(AbstractJogo jogo) {
        this.jogo = jogo;
    }

    public String getDivisor() {
        return divisor;
    }

    public void setDivisor(String divisor) {
        this.divisor = divisor;
    }
}
