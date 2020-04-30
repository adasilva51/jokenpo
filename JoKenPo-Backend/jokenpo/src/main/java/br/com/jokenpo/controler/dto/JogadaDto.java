package br.com.jokenpo.controler.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jokenpo.modelo.Jogada;

public class JogadaDto {

	private Long jogador;
	private String palpite;	
	private boolean vencedora;

	public JogadaDto(Jogada jogada) {
		this.jogador = jogada.getJogador();
		this.palpite = jogada.getPalpite();
		this.vencedora = jogada.isVencedora();
	}

	public Long getJogador() {
		return jogador;
	}	
	
	public String getPalpite() {
		return palpite;
	}	
	
	public boolean isVencedora() {
		return vencedora;
	}	
	
	public static List<JogadaDto> converter(List<Jogada> jogada) {
		return jogada.stream().map(JogadaDto::new).collect(Collectors.toList());
	}		
		
	
}
