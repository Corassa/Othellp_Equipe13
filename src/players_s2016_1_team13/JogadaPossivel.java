package players_s2016_1_team13;

import jogo.Casa;
import jogo.Jogada;
import java.util.Collections;
import java.lang.Comparable;

public class JogadaPossivel implements Comparable<JogadaPossivel>{
	
	Jogada jogada;
	int heuristica;
	Casa casa;
	
	public Casa getCasa() {
		return casa;
	}
	public void setCasa(Casa casa) {
		this.casa = casa;
	}
	public Jogada getJogada() {
		return jogada;
	}
	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}
	public int getHeuristica() {
		return heuristica;
	}
	public void setHeuristica(int heuristica) {
		this.heuristica = heuristica;
	}
	
	@Override
	public int compareTo(JogadaPossivel outraJogada) {
		// TODO Auto-generated method stub
		if(this.heuristica < outraJogada.heuristica){
			return 1;
		}
		if(this.heuristica > outraJogada.heuristica){
			return -1;
			
		}		
		return 0;
	}

	
}
