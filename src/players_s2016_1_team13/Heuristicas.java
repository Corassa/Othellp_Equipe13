package players_s2016_1_team13;

import java.util.List;

import jogo.Jogada;

public class Heuristicas {

	public int jogasPossíveis(Jogada jogada){
		int cont = 0;
		int[][] jog = jogada.getJogada();
    	for (int k = 0; k < jog.length; k++) {
    		for (int j = 0; j < jog.length; j++) {
    			if(jog[k][j] == -1)
    				cont--;
    			else
    				cont++;
    		}
		}
		return cont;
	}
	
	//quantas casas já tao garantidas
	
	//quantas voce vai perder
	
	//quantas voce vai ganhar
}
