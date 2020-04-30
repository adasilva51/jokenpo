package br.com.jokenpo.controler.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jokenpo.modelo.Player;

public class PlayerDto {

	private Long jogador;
	private String nome;
	
	public PlayerDto(Player player) {
		this.jogador = player.getJogador();
		this.nome = player.getNome();
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
	
	public static List<PlayerDto> converter(List<Player> player) {
		return player.stream().map(PlayerDto::new).collect(Collectors.toList());
	}		

}
