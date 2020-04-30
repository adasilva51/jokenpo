package br.com.jokenpo.controler.form;

import br.com.jokenpo.modelo.Jogada;

public class JogadaForm {

	private Long jogador;
	private String palpite;
	private boolean vencedora;
	
	public void setJogador(Long jogador) {
		this.jogador = jogador;
	}

	public void setPalpite(String palpite) {
		this.palpite = palpite;
	}

	public void setVencedora(boolean vencedora) {
		this.vencedora = vencedora;
	}	
	
	public Jogada converter() {
		return new Jogada(jogador, palpite, vencedora);
	}
	
}
