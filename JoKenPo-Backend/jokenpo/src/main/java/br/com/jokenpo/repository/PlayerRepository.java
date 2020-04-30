package br.com.jokenpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jokenpo.modelo.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

	Optional<Player> getPlayerByJogador(Long jogador);	
	
}
