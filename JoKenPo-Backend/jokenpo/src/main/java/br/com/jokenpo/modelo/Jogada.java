package br.com.jokenpo.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Jogada {

	@Id 
	private Long jogador;
	private String palpite;
	private boolean vencedora;
	
	public Jogada() {
	}
	
	public Jogada(Long jogador, String palpite, boolean vencedora) {
		this.jogador   = jogador;
		this.palpite   = palpite;
		this.vencedora = vencedora;
	}	
	
	public Long getJogador() {
		return jogador;
	}

	public void setJogador(Long jogador) {
		this.jogador = jogador;
	}	
	
	public String getPalpite() {
		return palpite;
	}

	public void setPalpite(String palpite) {
		this.palpite = palpite;
	}
		
	public void setVencedora(boolean vencedora) {
		this.vencedora = vencedora;
	}	
	
	public boolean isVencedora() {
		return vencedora;
	}	
	
}
