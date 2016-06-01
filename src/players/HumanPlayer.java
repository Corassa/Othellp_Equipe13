package players;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import jogo.AbstractPlayer;
import jogo.Casa;
import jogo.Jogada;
import jogo.JogoOthello;

/**
 *
 * @author Lucas M. Oliveira
 */
public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(int depth) {
        super(depth);
    }

    @Override
    public Casa jogar(int[][] tab) {
        Casa casa = new Casa();

        JogoOthello jogo = new JogoOthello();
        Random r = new Random();
        List<Jogada> jogadas = jogo.getJogadasValidas(tab, getMinhaMarcaTabuleiro());
//        for(Jogada j: jogadas){
//			System.out.print(j.getCasa().getLinha());
//			System.out.println(j.getCasa().getColuna());
//			System.out.println();
//		}

        Scanner leitor = new Scanner(System.in);

        int i, j;

        System.out.println("Insira a linha");
        i = leitor.nextInt();
        System.out.println("Insira a coluna");
        j = leitor.nextInt();
        if (tab[i][j] == 0) {
            casa.setLinha(i);
            casa.setColuna(j);
        }
        return casa;
    }

}
