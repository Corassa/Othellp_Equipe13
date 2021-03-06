package players;

/**
 *
 * @author Marcelo Paglione
 */
import java.util.List;
import java.util.Random;

import jogo.AbstractPlayer;
import jogo.Casa;
import jogo.Jogada;
import jogo.JogoOthello;

public class RandomPlayer extends AbstractPlayer {

    public RandomPlayer(int depth) {
        super(depth);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Casa jogar(int[][] tab) {
        JogoOthello jogo = new JogoOthello();
        Random r = new Random();
        List<Jogada> jogadas = jogo.getJogadasValidas(tab, getMinhaMarcaTabuleiro());
        if (jogadas.size() > 0) {
            return jogadas.get(r.nextInt(jogadas.size())).getCasa();
        } else {
            return new Casa(-1, -1);
        }
    }

}
