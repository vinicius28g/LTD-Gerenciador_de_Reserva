package br.com.projetoBase.controler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoBase.Service.UsuarioService;
import br.com.projetoBase.dto.UsuarioCadastro;
import br.com.projetoBase.dto.UsuarioRetorno;
import br.com.projetoBase.dto.CadastroPaciente;
import br.com.projetoBase.dto.RetornoPaciente;
import br.com.projetoBase.modelo.Paciente;
import br.com.projetoBase.modelo.Pessoa;
import br.com.projetoBase.modelo.TipoUsuario;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.PacienteRepositorio;
import br.com.projetoBase.repositorio.PessoaRepositorio;

@RestController
@RequestMapping("/home/paciente")
@CrossOrigin(origins = "*")
public class PacienteControler {
	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	@Autowired
	private UsuarioService usuarioService;
	
	 @GetMapping("/listAll")
	    public ResponseEntity<?> listar(){
	       return new ResponseEntity<>(pacienteRepositorio.findAll(),
	               HttpStatus.OK);
	    }
	 
	 @PostMapping("/salvar")
	    public ResponseEntity<?> salvar(@RequestBody CadastroPaciente CadastroPaciente){
		 Usuario usuario = new Usuario();
		 Paciente paciente = new Paciente();
	       
	        Pessoa pessoa = new Pessoa();
	        usuario.setTipoUsuario(TipoUsuario.PACIENTE);
	        usuario.setUser(CadastroPaciente.user());
	        usuario.setPass(new BCryptPasswordEncoder().encode(CadastroPaciente.pass()));
	        
	        pessoa = CadastroPaciente.paciente().getPessoa();
	        paciente.setPessoa(pessoa);
	               
	        usuario.setPessoa(pessoa);
	        pessoaRepositorio.save(pessoa);
	        Usuario usuarioSalvo = usuarioService.salvar(usuario);
	        pacienteRepositorio.save(paciente);
	        
	        RetornoPaciente retornoPaciente  = new RetornoPaciente(usuarioSalvo, paciente);
	        
	       return new ResponseEntity<>(retornoPaciente,HttpStatus.CREATED);
	    }
	 @GetMapping("/lisparPor{id}")
	    public ResponseEntity<?> buscar(@PathVariable Long id){
		 Optional<Paciente> pacienteOptional = pacienteRepositorio.findById(id);
		 Paciente paciente = pacienteOptional.get(); 
	       return new ResponseEntity<>(paciente,
	                HttpStatus.OK);
	    }

}
