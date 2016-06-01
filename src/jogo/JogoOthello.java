/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iuri
 */
public class JogoOthello extends AbstractJogo {

    public JogoOthello() {
        tam = 8;
    }

    @Override
    public int teste_terminal(int[][] tab, int marcaTabuleiro) {
        int vitoria = 0, derrota = 0, retorno = 0;

        if (semEspaco(tab)) {
            for (int linha = 0; linha < tam; linha++) {
                for (int coluna = 0; coluna < tam; coluna++) {
                    if (tab[linha][coluna] == marcaTabuleiro) {
                        vitoria++;
                    } else {
                        derrota++;
                    }
                }
            }
            if (vitoria > derrota) {
                retorno = 1;
            } else if (vitoria == derrota) {
                retorno = 2;
            }
        }

        int marcaOponenteNumero = 0;
        int minhaMarcaNumero = 0;
        for (int linha = 0; linha < tam; linha++) {
            for (int coluna = 0; coluna < tam; coluna++) {
                if (tab[linha][coluna] != marcaTabuleiro && tab[linha][coluna] != 0) {
                    marcaOponenteNumero++;
                }
                if (tab[linha][coluna] == marcaTabuleiro) {
                    minhaMarcaNumero++;
                }
            }
        }
        if (marcaOponenteNumero == 0) {
            retorno = 1;
        }

        if (getJogadasValidas(tab, 1).isEmpty()
                && getJogadasValidas(tab, -1).isEmpty()) {
            if (minhaMarcaNumero > marcaOponenteNumero) {
                retorno = 1;
            } else if (minhaMarcaNumero == marcaOponenteNumero) {
                retorno = 2;
            }
        }

        return retorno;
    }

    @Override
    public List<Jogada> getJogadasValidas(int[][] tabOriginal, int marcaTabuleiro) {
        List<Casa> casas = new ArrayList<>();
        List<Jogada> tabuleiros = new ArrayList<>();
        PlayerOthello jogador = new PlayerOthello(-1);
        jogador.setMarcaTabuleiro(marcaTabuleiro);
        int tamanho = tabOriginal.length;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                Casa casaTeste = new Casa(i, j);
                if (validar_jogada(tabOriginal, casaTeste, jogador) == 0) {
                    casas.add(casaTeste);
                }
            }
        }

        for (Casa casa : casas) {
            int[][] tab = tabuleiro_inicial();
            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    tab[i][j] = tabOriginal[i][j];
                }
            }
            tabuleiros.add(new Jogada(efetuar_jogada(tab, casa, jogador), casa));
        }

        return tabuleiros;
    }

    @Override
    public int validar_jogada(int[][] tabOriginal, Casa jogada, AbstractPlayer jogador) {

//        pular jogada
        if (jogada.getLinha() == -1 && jogada.getColuna() == -1) {
            return 0;
        }

        if (tabOriginal[jogada.getLinha()][jogada.getColuna()] == 0) {
//            verifica coluna +
            if (jogada.getColuna() + 1 < tam && tabOriginal[jogada.getLinha()][jogada.getColuna() + 1] != 0 && tabOriginal[jogada.getLinha()][jogada.getColuna() + 1] != jogador.getMarcaTabuleiro()) {//verifica direita
                for (int i = jogada.getColuna() + 1; i < tam; i++) {
                    if (tabOriginal[jogada.getLinha()][i] == jogador.getMarcaTabuleiro()) {
                        return 0;
                    }
                    if (tabOriginal[jogada.getLinha()][i] == 0) {
                        break;
                    }
                }
            }
//                verifica coluna -
            if (jogada.getColuna() > 0 && tabOriginal[jogada.getLinha()][jogada.getColuna() - 1] != 0 && tabOriginal[jogada.getLinha()][jogada.getColuna() - 1] != jogador.getMarcaTabuleiro()) {//verifica esquerda
                for (int i = jogada.getColuna() - 1; i >= 0; i--) {
                    if (tabOriginal[jogada.getLinha()][i] == jogador.getMarcaTabuleiro()) {
                        return 0;
                    }
                    if (tabOriginal[jogada.getLinha()][i] == 0) {
                        break;
                    }
                }
            }
//                verifica linha -
            if (jogada.getLinha() > 0 && tabOriginal[jogada.getLinha() - 1][jogada.getColuna()] != 0 && tabOriginal[jogada.getLinha() - 1][jogada.getColuna()] != jogador.getMarcaTabuleiro()) {//verifica acima
                for (int i = jogada.getLinha() - 1; i >= 0; i--) {
                    if (tabOriginal[i][jogada.getColuna()] == jogador.getMarcaTabuleiro()) {
                        return 0;
                    }
                    if (tabOriginal[i][jogada.getColuna()] == 0) {
                        break;
                    }
                }
            }
//                verifica linha +
            if (jogada.getLinha() < tam - 1 && tabOriginal[jogada.getLinha() + 1][jogada.getColuna()] != 0 && tabOriginal[jogada.getLinha() + 1][jogada.getColuna()] != jogador.getMarcaTabuleiro()) {//verifica abaixo
                for (int i = jogada.getLinha() + 1; i < tam; i++) {
                    if (tabOriginal[i][jogada.getColuna()] == jogador.getMarcaTabuleiro()) {
                        return 0;
                    }
                    if (tabOriginal[i][jogada.getColuna()] == 0) {
                        break;
                    }
                }
            }
//                verifica diagonal principal +
            if (jogada.getColuna() < tam - 1 && jogada.getLinha() < tam - 1 && tabOriginal[jogada.getLinha() + 1][jogada.getColuna() + 1] != 0 && tabOriginal[jogada.getLinha() + 1][jogada.getColuna() + 1] != jogador.getMarcaTabuleiro()) {//verifica abaixo direita
                for (int i = jogada.getLinha() + 1, j = jogada.getColuna() + 1; i < tam && j < tam; i++, j++) {
                    if (tabOriginal[i][j] == jogador.getMarcaTabuleiro()) {
                        return 0;
                    }
                    if (tabOriginal[i][j] == 0) {
                        break;
                    }
                }
            }
//                verifica diagonal principal -
            if (jogada.getColuna() > 0 && jogada.getLinha() > 0 && tabOriginal[jogada.getLinha() - 1][jogada.getColuna() - 1] != 0 && tabOriginal[jogada.getLinha() - 1][jogada.getColuna() - 1] != jogador.getMarcaTabuleiro()) {//verifica cima esquerda
                for (int i = jogada.getLinha() - 1, j = jogada.getColuna() - 1; i >= 0 && j >= 0; i--, j--) {
                    if (tabOriginal[i][j] == jogador.getMarcaTabuleiro()) {
                        return 0;
                    }
                    if (tabOriginal[i][j] == 0) {
                        break;
                    }
                }
            }
//                verifica diagonal secundaria +
            if (jogada.getColuna() > 0 && jogada.getLinha() < tam - 1 && tabOriginal[jogada.getLinha() + 1][jogada.getColuna() - 1] != 0 && tabOriginal[jogada.getLinha() + 1][jogada.getColuna() - 1] != jogador.getMarcaTabuleiro()) {//verifica abaixo esquerda
                for (int i = jogada.getLinha() + 1, j = jogada.getColuna() - 1; i < tam && j >= 0; i++, j--) {
                    if (tabOriginal[i][j] == jogador.getMarcaTabuleiro()) {
                        return 0;
                    }
                    if (tabOriginal[i][j] == 0) {
                        break;
                    }
                }
            }
//                verifica diagonal secundaria -
            if (jogada.getColuna() < tam - 1 && jogada.getLinha() > 0 && tabOriginal[jogada.getLinha() - 1][jogada.getColuna() + 1] != 0 && tabOriginal[jogada.getLinha() - 1][jogada.getColuna() + 1] != jogador.getMarcaTabuleiro()) {//verifica cima direita
                for (int i = jogada.getLinha() - 1, j = jogada.getColuna() + 1; i >= 0 && j < tam; i--, j++) {
                    if (tabOriginal[i][j] == jogador.getMarcaTabuleiro()) {
                        return 0;
                    }
                    if (tabOriginal[i][j] == 0) {
                        break;
                    }
                }
            }
        } else {
            return -1;
        }
        return -2;
    }

    public boolean verificaPosicaoAoRedorEPosicaoJogada(int linha, int coluna, Casa casa) {
        if (linha == casa.getLinha() && coluna == casa.getColuna()) {
            return true;
        }
        if (linha == casa.getLinha() + 1 && coluna == casa.getColuna() + 1) {
            return true;
        }
        if (linha == casa.getLinha() - 1 && coluna == casa.getColuna() - 1) {
            return true;
        }
        if (linha == casa.getLinha() - 1 && coluna == casa.getColuna() + 1) {
            return true;
        }
        if (linha == casa.getLinha() + 1 && coluna == casa.getColuna() - 1) {
            return true;
        }
        if (linha == casa.getLinha() + 1 && coluna == casa.getColuna()) {
            return true;
        }
        if (linha == casa.getLinha() - 1 && coluna == casa.getColuna()) {
            return true;
        }
        if (linha == casa.getLinha() && coluna == casa.getColuna() + 1) {
            return true;
        }
        if (linha == casa.getLinha() && coluna == casa.getColuna() - 1) {
            return true;
        }
        return false;
    }

    @Override
    public int[][] efetuar_jogada(int[][] tabOriginal, Casa jogada, AbstractPlayer jogador) {
//        pular jogada
        if (jogada.getLinha() == -1 && jogada.getColuna() == -1) {
            return tabOriginal;
        }

        tabOriginal[jogada.getLinha()][jogada.getColuna()] = jogador.getMarcaTabuleiro();
        // procura a propria marca na linha para traz
        int posicao = -1;
        for (int i = jogada.getLinha() - 1; i >= 0; i--) {
            // rdr - caso encontre uma casa em branco antes da marca do jogador
            if (tabOriginal[i][jogada.getColuna()] == 0) {
                break;
            }
            if (tabOriginal[i][jogada.getColuna()] == jogador.getMarcaTabuleiro()) {
                posicao = i;
                break;
            }
        }
        if (posicao != -1) {
            posicao++;
            for (int i = posicao; i < jogada.getLinha(); i++) {
                if (tabOriginal[i][jogada.getColuna()] != 0) {
                    tabOriginal[i][jogada.getColuna()] = jogador.getMarcaTabuleiro();
                }
            }
        }
        // procura a propria marca na linha para Frente
        posicao = -1;
        for (int i = jogada.getLinha() + 1; i < 8; i++) {
            // rdr - caso encontre uma casa em branco antes da marca do jogador
            if (tabOriginal[i][jogada.getColuna()] == 0) {
                break;
            }
            if (tabOriginal[i][jogada.getColuna()] == jogador.getMarcaTabuleiro()) {
                posicao = i;
                break;
            }
        }
        if (posicao != -1) {
            posicao--;
            for (int i = posicao; i > jogada.getLinha(); i--) {
                if (tabOriginal[i][jogada.getColuna()] != 0) {
                    tabOriginal[i][jogada.getColuna()] = jogador.getMarcaTabuleiro();
                }
            }
        }
        /**
         * **************************************************************************************
         */
        // procura a propria marca na coluna para traz
        posicao = -1;
        for (int i = jogada.getColuna() - 1; i >= 0; i--) {
            // rdr - caso encontre uma casa em branco antes da marca do jogador
            if (tabOriginal[jogada.getLinha()][i] == 0) {
                break;
            }
            if (tabOriginal[jogada.getLinha()][i] == jogador.getMarcaTabuleiro()) {
                posicao = i;
                break;
            }
        }
        if (posicao != -1) {
            posicao++;
            for (int i = posicao; i < jogada.getColuna(); i++) {
                if (tabOriginal[jogada.getLinha()][i] != 0) {
                    tabOriginal[jogada.getLinha()][i] = jogador.getMarcaTabuleiro();
                }
            }
        }
        // procura a propria marca na coluna para Frente
        posicao = -1;
        for (int i = jogada.getColuna() + 1; i < 8; i++) {
            // rdr - caso encontre uma casa em branco antes da marca do jogador
            if (tabOriginal[jogada.getLinha()][i] == 0) {
                break;
            }
            if (tabOriginal[jogada.getLinha()][i] == jogador.getMarcaTabuleiro()) {
                posicao = i;
                break;
            }
        }
        if (posicao != -1) {
            posicao--;
            for (int i = posicao; i > jogada.getColuna(); i--) {
                if (tabOriginal[jogada.getLinha()][i] != 0) {
                    tabOriginal[jogada.getLinha()][i] = jogador.getMarcaTabuleiro();
                }
            }
        }
        /**
         * ******************************************************
         */
        // procura a propria marca na diagonal para traz
        int linha = jogada.getLinha() - 1;
        int coluna = jogada.getColuna() - 1;
        // rdr - indica se uma casa em branco foi encontrada antes da marca do jogador
        boolean indBranco = false;

        while (linha >= 0 && coluna >= 0) {
            if (tabOriginal[linha][coluna] == jogador.getMarcaTabuleiro()) {
                break;
            }
            // rdr - inserida condicao para sair quanto encontrar uma casa em branco
            if (tabOriginal[linha][coluna] == 0) {
                indBranco = true;
                break;
            }
            linha--;
            coluna--;
        }
        if (linha >= 0 && coluna >= 0 && !indBranco) { // rdr - verifica indBranco
            while (linha != jogada.getLinha()
                    && coluna != jogada.getColuna()) {
                if (tabOriginal[linha][coluna] != 0) {
                    tabOriginal[linha][coluna] = jogador.getMarcaTabuleiro();
                }
                linha++;
                coluna++;
            }
        }
        // procura a propria marca na diagonal para Frente
        linha = jogada.getLinha() + 1;
        coluna = jogada.getColuna() + 1;
        // rdr - inicia indBranco
        indBranco = false;
        while (linha < 8 && coluna < 8) {
            if (tabOriginal[linha][coluna] == jogador.getMarcaTabuleiro()) {
                break;
            }
            // rdr - inserida condicao para sair quanto encontrar uma casa em branco
            if (tabOriginal[linha][coluna] == 0) {
                indBranco = true;
                break;
            }
            linha++;
            coluna++;
        }
        if (linha < 8 && coluna < 8 && !indBranco) { // rdr - verifica indBranco
            while (linha != jogada.getLinha()
                    && coluna != jogada.getColuna()) {
                if (tabOriginal[linha][coluna] != 0) {
                    tabOriginal[linha][coluna] = jogador.getMarcaTabuleiro();
                }
                linha--;
                coluna--;
            }
        }
        /**
         * ******************************************************
         */
        // procura a propria marca na diagonal inversa para traz
        linha = jogada.getLinha() - 1;
        coluna = jogada.getColuna() + 1;
        // rdr - inicia indBranco
        indBranco = false;
        while (linha >= 0 && coluna < 8) {
            if (tabOriginal[linha][coluna] == jogador.getMarcaTabuleiro()) {
                break;
            }
            // rdr - inserida condicao para sair quanto encontrar uma casa em branco
            if (tabOriginal[linha][coluna] == 0) {
                indBranco = true;
                break;
            }
            linha--;
            coluna++;
        }
        if (linha >= 0 && coluna < 8 && !indBranco) { // rdr - verifica indBranco
            while (linha != jogada.getLinha()
                    && coluna != jogada.getColuna()) {
                if (tabOriginal[linha][coluna] != 0) {
                    tabOriginal[linha][coluna] = jogador.getMarcaTabuleiro();
                }
                linha++;
                coluna--;
            }
        }
        // procura a propria marca na diagonal inversa para Frente
        linha = jogada.getLinha() + 1;
        coluna = jogada.getColuna() - 1;
        // rdr - inicia indBranco
        indBranco = false;
        while (linha < 8 && coluna >= 0) {
            if (tabOriginal[linha][coluna] == jogador.getMarcaTabuleiro()) {
                break;
            }
            // rdr - inserida condicao para sair quanto encontrar uma casa em branco
            if (tabOriginal[linha][coluna] == 0) {
                indBranco = true;
                break;
            }
            linha++;
            coluna--;
        }
        if (linha < 8 && coluna >= 0 && !indBranco) { // rdr - verifica indBranco
            while (linha != jogada.getLinha()
                    && coluna != jogada.getColuna()) {
                if (tabOriginal[linha][coluna] != 0) {
                    tabOriginal[linha][coluna] = jogador.getMarcaTabuleiro();
                }
                linha--;
                coluna++;
            }
        }
        /**
         * ******************************************************
         */
        return tabOriginal;
    }

    @Override
    public int[][] tabuleiro_inicial() {
        int[][] tab = new int[tam][tam];
        tab[3][3] = -1;
        tab[4][4] = -1;
        tab[3][4] = 1;
        tab[4][3] = 1;
        return tab;
    }

    public boolean semEspaco(int[][] tab) {
        for (int linha = 0; linha < tam; linha++) {
            for (int coluna = 0; coluna < tam; coluna++) {
                if (tab[linha][coluna] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
