package br.com.jokenpo.controler;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jokenpo.controler.dto.PlayerDto;
import br.com.jokenpo.controler.form.PlayerForm;
import br.com.jokenpo.modelo.Player;
import br.com.jokenpo.repository.PlayerRepository;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;	
	
	@GetMapping
	public List<PlayerDto> lista() {
		List<Player> players = playerRepository.findAll();
		return PlayerDto.converter(players);		
	}		
	
	@GetMapping("/{jogador}")
	public ResponseEntity<PlayerDto> detalhar(@PathVariable Long jogador) {
		Optional<Player> player = playerRepository.getPlayerByJogador(jogador);
		if (player.isPresent()) {
			return ResponseEntity.ok(new PlayerDto(player.get()));
		}
		return ResponseEntity.notFound().build();
	}		
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeJogadores", allEntries = true)
	public ResponseEntity<PlayerDto> cadastrar(@RequestBody @Valid PlayerForm form, UriComponentsBuilder uriBuilder) {
		Player player = form.converter();
		playerRepository.save(player);
		
		URI uri = uriBuilder.path("/player/{id}").buildAndExpand(player.getJogador()).toUri();
		return ResponseEntity.created(uri).body(new PlayerDto(player));
	}	
	
	
	@DeleteMapping("/{jogador}")
	@Transactional
	@CacheEvict(value = "listaDeJogadores", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long jogador) {
		Optional<Player> optional = playerRepository.findById(jogador);
		if (optional.isPresent()) {
			playerRepository.deleteById(jogador);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}	
		
	
}
