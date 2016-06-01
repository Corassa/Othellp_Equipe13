package players_s2015_2_team01;
/**
 *
 * @author Marcelo Paglione
 */
import java.util.List;

import jogo.AbstractPlayer;
import jogo.Casa;
import jogo.Jogada;
import jogo.JogoOthello;

public class RandomPlayerA extends AbstractPlayer {

    public RandomPlayerA(int depth) {
        super(depth);
    }

    @Override
    public Casa jogar(int[][] tab) {
        JogoOthello jogo = new JogoOthello();
        List<Jogada> jogadas = jogo.getJogadasValidas(tab, getMinhaMarcaTabuleiro());
        return jogadas.get(0).getCasa();
    }

}
