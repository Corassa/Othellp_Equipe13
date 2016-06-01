package players;

import java.util.List;
import jogo.AbstractPlayer;
import jogo.Casa;
import jogo.Jogada;
import jogo.Jogar;
import jogo.JogoOthello;

/**
 *
 * @author Marcelo Paglione
 */
public class HumanPlayerDisplay extends AbstractPlayer {

    public HumanPlayerDisplay(int depth) {
        super(depth);
    }

    @Override
    public synchronized Casa jogar(int[][] tab) {
        JogoOthello jogo = new JogoOthello();
        List<Jogada> jogadas = jogo.getJogadasValidas(tab, getMinhaMarcaTabuleiro());
        Casa casa = new Casa(-1, -1);
        if (jogadas.size() > 0) {
            casa = Jogar.displayOtello.get();
            return casa;
        } else {
            return casa;
        }

    }

}
