package br.com.projetoBase.controler;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoBase.modelo.Local;
import br.com.projetoBase.repositorio.LocalRepositorio;

@RestController
@RequestMapping("/local")
public class LocalControler {
	@Autowired
	private LocalRepositorio  localRepositorio;
	
	/* metodo salva e atualiza
	 * para atualizar basta passar o id do local que ele vai ser atualizado*/ 
	 @Transactional
	 @PostMapping("/salvar") 
	public ResponseEntity<?> salvarLocal(@RequestBody Local local){
		try {
			if(local.getId()!= null)
				local.setDataAtualizacao(new Date());
			else
				local.setDataCriacao(new Date());
			localRepositorio.save(local);
			return new ResponseEntity<>(local, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/get{id}")
	public ResponseEntity<?> get(@PathVariable long id){
		return new ResponseEntity<> (localRepositorio.findById(id).get(), HttpStatus.OK);
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(localRepositorio.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete{id}")
	public ResponseEntity<?> delete(@PathVariable long id){
		try {
			localRepositorio.deleteById(id);
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	 
	
}
