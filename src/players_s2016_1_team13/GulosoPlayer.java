package players_s2016_1_team13;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Marcelo Paglione
 */
import java.util.List;
import jogo.AbstractPlayer;
import jogo.Casa;
import jogo.Jogada;
import jogo.JogoOthello;

public class GulosoPlayer extends AbstractPlayer {

    public GulosoPlayer(int depth) {
        super(depth);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Casa jogar(int[][] tab) {
    	
        JogoOthello jogo = new JogoOthello();
        List<Jogada> jogadas = jogo.getJogadasValidas(tab, getMinhaMarcaTabuleiro());

        ArrayList<JogadaPossivel> list = new ArrayList<>();
        for (int i = 0; i < jogadas.size(); i++) {
			JogadaPossivel jgpossivel = new JogadaPossivel();
			jgpossivel.setJogada(jogadas.get(i));
			jgpossivel.setCasa(jogadas.get(i).getCasa());
			jgpossivel.setHeuristica(new Heuristicas().jogasPossíveis(jogadas.get(i)));
			
			list.add(jgpossivel);
		}
        
        Collections.sort(list);
        
        for (int i = 0; i < list.size(); i++) {
        	System.out.println(list.get(i).getHeuristica());
		}
        System.out.print("-------[");
        
        System.out.print("retornada : "+list.get(0).getHeuristica());
        
        System.out.println("]");
        return list.get(0).getCasa();
        
       //return jogadas.get(jogadas.size()-1).getCasa();
    }

}
