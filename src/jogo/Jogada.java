package jogo;

public class Jogada {

    private int[][] tabuleiro;
    private Casa casa;

    public Jogada(int[][] tab, Casa c) {

        tabuleiro = tab;
        casa = c;

    }

    public int[][] getJogada() {
        return tabuleiro;
    }

    public Casa getCasa() {
        return casa;
    }

}
