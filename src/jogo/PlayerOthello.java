/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

/**
 *
 * @author iuri
 */
public class PlayerOthello extends AbstractPlayer {

    public PlayerOthello(int depth) {
        super(depth);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Casa jogar(int[][] tab) {
        Casa casa = new Casa();
        Boolean casaValida = false;
        loopExterno:
        for (int i = 0; i < getJogo().tam; i++) {
            for (int j = 0; j < getJogo().tam; j++) {

                if (getJogo().validar_jogada(tab, new Casa(i, j), this) == 0) {
                    if (i < getJogo().tam - 1 && tab[i + 1][j] != 0 && tab[i + 1][j] != getMarcaTabuleiro()) {
                        casaValida = true;
                    } else if (i < getJogo().tam - 1 && j < getJogo().tam - 1 && tab[i + 1][j + 1] != 0 && tab[i + 1][j + 1] != getMarcaTabuleiro()) {
                        casaValida = true;
                    } else if (j < getJogo().tam - 1 && tab[i][j + 1] != 0 && tab[i][j + 1] != getMarcaTabuleiro()) {
                        casaValida = true;
                    } else if (i != 0 && tab[i - 1][j] != 0 && tab[i - 1][j] != getMarcaTabuleiro()) {
                        casaValida = true;
                    } else if (i != 0 && j != 0 && tab[i - 1][j - 1] != 0 && tab[i - 1][j - 1] != getMarcaTabuleiro()) {
                        casaValida = true;
                    } else if (j != 0 && tab[i][j - 1] != 0 && tab[i][j - 1] != getMarcaTabuleiro()) {
                        casaValida = true;
                    }
                }

                if (tab[i][j] == 0 && casaValida) {
                    casa.setLinha(i);
                    casa.setColuna(j);
                    break loopExterno;
                }
            }
        }
        return casa;
    }

    public Boolean verificarLinhaColuna(int[][] tab, int linha, int coluna) {
        Boolean valido = false;
        for (int i = 0; i < getJogo().tam; i++) {
            if (tab[linha][i] == getMarcaTabuleiro()
                    || tab[i][coluna] == getMarcaTabuleiro()) {
                valido = true;
                break;
            }
            if (linha - i >= 0 && coluna - i >= 0) {
                if (tab[linha - i][coluna - i] == getMarcaTabuleiro()) {
                    valido = true;
                    break;
                }
            }
            if (linha + i < getJogo().tam && coluna + i < getJogo().tam) {
                if (tab[linha + i][coluna + i] == getMarcaTabuleiro()) {
                    valido = true;
                    break;
                }
            }
            if (linha + i < getJogo().tam && coluna - i >= 0) {
                if (tab[linha + i][coluna - i] == getMarcaTabuleiro()) {
                    valido = true;
                    break;
                }
            }
            if (linha - i >= 0 && coluna + i < getJogo().tam) {
                if (tab[linha - i][coluna + i] == getMarcaTabuleiro()) {
                    valido = true;
                    break;
                }
            }
        }

        return valido;
    }
}
