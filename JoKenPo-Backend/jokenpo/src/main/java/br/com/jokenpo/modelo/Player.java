package br.com.jokenpo.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {

	@Id 
	private Long jogador;
	private String nome; 
	
	public Player() {
		
	}
	
	public Player(Long jogador, String nome) {
		this.jogador   = jogador;
		this.nome   = nome;
	}
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
	
	
	
}
