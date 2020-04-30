package br.com.jokenpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jokenpo.modelo.Jogada;

public interface JogadaRepository extends JpaRepository<Jogada, Long>{

	Optional<Jogada> getJogadaByJogador(Long jogador);

	//Jogada findByNome(Long jogador); 
	
}
