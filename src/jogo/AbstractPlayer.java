/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iuri, silla
 */
abstract public class AbstractPlayer {

    private int marcaTabuleiro = 0, marcaTabuleiroOponente = 0, idUsuario;

    private AbstractJogo jogo;

    private int profundidade = 0;

    public AbstractPlayer(int depth) {
        profundidade = depth;
    }

    abstract public Casa jogar(int[][] tab);

    public int getMinhaMarcaTabuleiro() {
        return marcaTabuleiro;
    }

    public int getMarcaTabuleiroOponente() {
        return marcaTabuleiroOponente;
    }

    public void setMinhaMarcaTabuleiro(int marcaTabuleiro) {
        this.marcaTabuleiro = marcaTabuleiro;
    }

    public void setMarcaTabuleiroOponente(int marcaTabuleiro) {
        this.marcaTabuleiroOponente = marcaTabuleiro;
    }

    public AbstractJogo getJogo() {
        return jogo;
    }

    public void setJogo(AbstractJogo jogo) {
        this.jogo = jogo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getMarcaTabuleiro() {
        return marcaTabuleiro;
    }

    public void setMarcaTabuleiro(int marcaTabuleiro) {
        this.marcaTabuleiro = marcaTabuleiro;
    }

}
