package br.com.projetoBase.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.projetoBase.dto.QuestionarioPaciente;
import br.com.projetoBase.repositorio.QuestionarioRepositorio;
import br.com.projetoBase.repositorio.UsuarioPacienteRepositorio;

@RestController
@RequestMapping("/home")
@CrossOrigin("*")

public class QuestionarioPacienteControlle {
	private final QuestionarioRepositorio questionarioRepositorio;
	private final UsuarioPacienteRepositorio usuarioPacienteRepositorio;

	
	public QuestionarioPacienteControlle(QuestionarioRepositorio questionarioRepositorio,
			UsuarioPacienteRepositorio usuarioPacienteRepositorio) {
		
		this.questionarioRepositorio = questionarioRepositorio;
		this.usuarioPacienteRepositorio = usuarioPacienteRepositorio;
	}
	@PostMapping("/quest")
	public ResponseEntity<?> salvar(@RequestBody QuestionarioPaciente questionarioPaciente){
		var usuarioPaciente = usuarioPacienteRepositorio.buscarPorCpf(questionarioPaciente.usuarioPaciente().getCpf());
		if(usuarioPaciente.isPresent()) {
			var aux = usuarioPaciente.get();
			questionarioPaciente.usuarioPaciente().setId(aux.getId());			
		}

		usuarioPacienteRepositorio.save(questionarioPaciente.usuarioPaciente());
		
		if(questionarioPaciente.questionario() != null) {
			questionarioPaciente.questionario().setUsuariopaciente(questionarioPaciente.usuarioPaciente());
			questionarioRepositorio.save(questionarioPaciente.questionario());
		}
		
		return new ResponseEntity<>(questionarioPaciente.usuarioPaciente(), HttpStatus.CREATED);
	}
	
	
	
	
}
