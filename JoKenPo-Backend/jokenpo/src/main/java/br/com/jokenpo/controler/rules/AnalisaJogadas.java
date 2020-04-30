package br.com.jokenpo.controler.rules;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jokenpo.modelo.Jogada;

public class AnalisaJogadas {

	public List<Jogada> procuraVendedor(List<Jogada> jogadas) {
		
		for(Jogada jg: jogadas) {
			switch(jg.getPalpite()) {
			case "tesoura":
				 if(jogadas.stream().
						    filter(jgd->jgd.getPalpite().equals("pedra")|| jgd.getPalpite().equals("spock")).
						    findAny().isPresent()) 	
					 jg.setVencedora(false); 
				 break;
			case "papel":
				 if(jogadas.stream().
						    filter(jgd->jgd.getPalpite().equals("lagarto")|| jgd.getPalpite().equals("tesoura")).
						    findAny().isPresent()) 	
					 jg.setVencedora(false); 
				 break;
			case "pedra":
				 if(jogadas.stream().
						    filter(jgd->jgd.getPalpite().equals("spock")|| jgd.getPalpite().equals("papel")).
						    findAny().isPresent()) 	
					 jg.setVencedora(false); 
				 break;
			case "lagarto":
				 if(jogadas.stream().
						    filter(jgd->jgd.getPalpite().equals("tesoura")|| jgd.getPalpite().equals("pedra")).
						    findAny().isPresent()) 	
					 jg.setVencedora(false); 
				 break;
			case "spock":
				 if(jogadas.stream().
						    filter(jgd->jgd.getPalpite().equals("papel")|| jgd.getPalpite().equals("lagarto")).
						    findAny().isPresent()) 	
					 jg.setVencedora(false); 	
				 break;

		    }				
		}
		
		return jogadas.stream().filter(jg->jg.isVencedora()).collect(Collectors.toList());
	}

}
