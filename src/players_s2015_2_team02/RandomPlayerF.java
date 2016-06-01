package players_s2015_2_team02;
/**
 *
 * @author Marcelo Paglione
 */
import java.util.List;

import jogo.AbstractPlayer;
import jogo.Casa;
import jogo.Jogada;
import jogo.JogoOthello;

public class RandomPlayerF extends AbstractPlayer {

    public RandomPlayerF(int depth) {
        super(depth);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Casa jogar(int[][] tab) {
        JogoOthello jogo = new JogoOthello();
        List<Jogada> jogadas = jogo.getJogadasValidas(tab, getMinhaMarcaTabuleiro());


       return jogadas.get(jogadas.size()-1).getCasa();
    }

}
