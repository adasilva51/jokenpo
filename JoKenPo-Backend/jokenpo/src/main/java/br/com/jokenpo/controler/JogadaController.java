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

import br.com.jokenpo.controler.dto.JogadaDto;
import br.com.jokenpo.controler.dto.PlayerDto;
import br.com.jokenpo.controler.form.JogadaForm;
import br.com.jokenpo.controler.rules.AnalisaJogadas;
import br.com.jokenpo.modelo.Jogada;
import br.com.jokenpo.modelo.Player;
import br.com.jokenpo.repository.JogadaRepository;
import br.com.jokenpo.repository.PlayerRepository;


@RestController
@RequestMapping("/jogada")
public class JogadaController {

	@Autowired
	private JogadaRepository jogadaRepository;	
	
	@Autowired
	private PlayerRepository playerRepository;		
	
	@GetMapping
	public List<JogadaDto> lista() {
		List<Jogada> jogadas = jogadaRepository.findAll();
		return JogadaDto.converter(jogadas);		
	}	
	
	@GetMapping("/{jogador}")
	public ResponseEntity<JogadaDto> detalhar(@PathVariable Long jogador) {
		Optional<Jogada> jogada = jogadaRepository.getJogadaByJogador(jogador);
		if (jogada.isPresent()) {
			return ResponseEntity.ok(new JogadaDto(jogada.get()));
		}
		return ResponseEntity.notFound().build();
	}	
	
	@GetMapping("/jogar")	
	public String jogar() {
		List<Jogada> jogadas = jogadaRepository.findAll();
		AnalisaJogadas analise = new AnalisaJogadas();
		List<Jogada> vencedores = analise.procuraVendedor(jogadas);
		
		jogadas.forEach(k->System.out.println(k.getJogador()+" palpite = "+ k.getPalpite() +" status = "+k.isVencedora()));
		System.out.println("  vencedores ");
		vencedores.forEach(k->System.out.println(k.getJogador()+" palpite = "+ k.getPalpite() +" status = "+k.isVencedora()));		
		
		if(vencedores.size()==1) 
			return "Jogador "+ vencedores.get(0).getJogador()+" com palpite "+ vencedores.get(0).getPalpite() +" ganhou";	
		else
			return "Houve empate";
	}		
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeJogadas", allEntries = true)
	public ResponseEntity<JogadaDto> cadastrar(@RequestBody @Valid JogadaForm form, UriComponentsBuilder uriBuilder) {
		Jogada jogada = form.converter();
		
		Optional<Player> player = playerRepository.getPlayerByJogador(jogada.getJogador());
		if (player.isPresent()) {
			jogadaRepository.save(jogada);
			URI uri = uriBuilder.path("/jogada/{id}").buildAndExpand(jogada.getJogador()).toUri();
			return ResponseEntity.created(uri).body(new JogadaDto(jogada));			
		}
		return ResponseEntity.notFound().build();		
	}	
	
	
	@DeleteMapping("/{jogador}")
	@Transactional
	@CacheEvict(value = "listaDeJogadas", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long jogador) {
		Optional<Jogada> optional = jogadaRepository.findById(jogador);
		if (optional.isPresent()) {
			jogadaRepository.deleteById(jogador);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}	
	
}
