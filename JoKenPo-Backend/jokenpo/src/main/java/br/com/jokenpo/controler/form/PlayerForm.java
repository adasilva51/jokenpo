package br.com.jokenpo.controler.form;

import br.com.jokenpo.modelo.Jogada;
import br.com.jokenpo.modelo.Player;

public class PlayerForm {

	private Long jogador;
	private String nome;
	
	public Long getJogador() {
		return jogador;
	}
	public void setJogador(Long jogador) {
		this.jogador = jogador;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}		
	
	public Player converter() {
		return new Player(jogador, nome);
	}	
	
}
