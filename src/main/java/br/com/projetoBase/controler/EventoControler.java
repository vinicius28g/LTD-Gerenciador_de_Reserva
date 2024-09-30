package br.com.projetoBase.controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoBase.dto.RetornoEvento;
import br.com.projetoBase.modelo.Evento;
import br.com.projetoBase.repositorio.EventoRepositorio;

@RestController
@RequestMapping("/evento")
public class EventoControler {
	
	@Autowired
	private EventoRepositorio eventoRepositorio;
	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody Evento evento){
		try {
			if(evento.getId()!= null)
				evento.setDataAtualizacao(new Date());
			else
				evento.setDataCriacao(new Date());
//			if(evento.getCapacidade()> 0 && evento.getLocal().getCapacidade() > evento.getCapacidade()) {
//				return new ResponseEntity<> ("capacidade de pessoa é maior da permitida", HttpStatus.CONFLICT);
//			}
			eventoRepositorio.save(evento);
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.CONFLICT);
			// TODO: handle exception
		}
		
	}
	
	@GetMapping("/get{id}")
	public ResponseEntity<?> get(@PathVariable long id){
		Evento evento = eventoRepositorio.findById(id).get();
		RetornoEvento retornoEvento= new RetornoEvento(evento.getId(), evento.getNome(), evento.getDescicao(), evento.getCapacidade());
		
		return new ResponseEntity<> (retornoEvento, HttpStatus.OK);
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<?> listAll(){
		List<Evento> eventos = eventoRepositorio.findAll();
		List<RetornoEvento> retornos = new ArrayList<>();
		for (Evento evento: eventos) {
			RetornoEvento retornoEvento= new RetornoEvento(
					evento.getId(),evento.getNome(),evento.getDescicao(), evento.getCapacidade());
			retornos.add(retornoEvento);
		}
		
		return new ResponseEntity<>(retornos, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete{id}")
	public ResponseEntity<?> delete(@PathVariable long id){
		try {
			eventoRepositorio.deleteById(id);
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
