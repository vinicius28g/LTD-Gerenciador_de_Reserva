package br.com.projetoBase.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import br.com.projetoBase.dto.FuncCordeCadastro;
import br.com.projetoBase.dto.RetornoUsuario;
import br.com.projetoBase.dto.UsuarioCadastro;
import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.TipoUsuario;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.ClinicaRepositorio;



import br.com.projetoBase.repositorio.UsuarioRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private ClinicaRepositorio clinicaRepositorio;

	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}
	
	public Usuario buscarByUser(String user) {
	    return usuarioRepositorio.findByUser(user);
	}
	
	@Transactional
	public Usuario buscarPorId(long id) {
		Optional<Usuario> usuario =usuarioRepositorio.findById(id);
		return  usuario.get();
	}
	
	  public ResponseEntity<?> salvar(UsuarioCadastro usuarioCadastro, TipoUsuario tipo){
		  
	  if(usuarioRepositorio.findByUser(usuarioCadastro.user())!= null) {
		  return new ResponseEntity<>("Usuario já existente", HttpStatus.CONFLICT);
	  }
        Usuario usuario = new Usuario();
       
        usuario.setNomeCompleto(usuarioCadastro.nome());
        usuario.setTelefone(usuarioCadastro.telefone());
        usuario.setDataNascimento(usuarioCadastro.DataNascimento());
        usuario.setTipoUsuario(tipo);
        usuario.setUser(usuarioCadastro.user());
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioCadastro.pass()));
        
        this.salvar(usuario);
        
        return new ResponseEntity<>(new RetornoUsuario(
				usuario.getId(),
				usuario.getUser(),
				usuario.getTipoUsuario().getNome(),
				usuario.getNomeCompleto(),
				usuario.getTelefone(),
				usuario.getDataNascimento()), HttpStatus.CREATED);
	    }
	  

	  public ResponseEntity<?> salvarFuncCord(FuncCordeCadastro usuarioCadastro, TipoUsuario tipo){
		  
		  if(usuarioRepositorio.findByUser(usuarioCadastro.user())!= null) {
			  return new ResponseEntity<>("Usuario já existente", HttpStatus.CONFLICT);
		  }
	        Usuario usuario = new Usuario();
	        
	        Optional<Clinica> opcionalClinica =  clinicaRepositorio.findById(usuarioCadastro.clinicaId());
	        if(opcionalClinica.isEmpty()) {
	        	return new ResponseEntity<>("clinica não encontrada", HttpStatus.CONFLICT); 
	        }
	        usuario.setClinica(opcionalClinica.get());
	        usuario.setNomeCompleto(usuarioCadastro.nome());
	        usuario.setTelefone(usuarioCadastro.telefone());
	        usuario.setDataNascimento(usuarioCadastro.DataNascimento());
	        usuario.setTipoUsuario(tipo);
	        usuario.setUser(usuarioCadastro.user());
	        usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioCadastro.pass()));
	        
	        this.salvar(usuario);
	        
	        return new ResponseEntity<>(new RetornoUsuario(
					usuario.getId(),
					usuario.getUser(),
					usuario.getTipoUsuario().getNome(),
					usuario.getNomeCompleto(),
					usuario.getTelefone(),
					usuario.getDataNascimento()), HttpStatus.CREATED);
		    }
	  

	  public ResponseEntity<?> editar(UsuarioCadastro usuarioCadastro, TipoUsuario tipo){
		  
		  Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(usuarioCadastro.id());
		  if(usuarioOptional.isEmpty()) {
			  return new ResponseEntity<>("usuario não encontrado", HttpStatus.NOT_FOUND);
		  }
			Usuario usuario = usuarioOptional.get();
			Usuario usuCompare = (Usuario)usuarioRepositorio.findByUser(usuarioCadastro.user());
	
			if (usuCompare != null && usuCompare != usuario ) {
				return new ResponseEntity<>("Login ja existente", HttpStatus.CONFLICT);
			}
			
			usuario.setNomeCompleto(usuarioCadastro.nome());
	        usuario.setTelefone(usuarioCadastro.telefone());
	        usuario.setDataNascimento(usuarioCadastro.DataNascimento());
	        usuario.setTipoUsuario(tipo);
	        usuario.setUser(usuarioCadastro.user());
	        usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioCadastro.pass()));
	        
	        this.salvar(usuario);
	
			return new ResponseEntity<>(
					new RetornoUsuario(
							usuario.getId(),
							usuario.getUser(),
							usuario.getTipoUsuario().getNome(),
							usuario.getNomeCompleto(),
							usuario.getTelefone(),
							usuario.getDataNascimento()), HttpStatus.CREATED);
		    }
	  
	  public ResponseEntity<?> listAll(TipoUsuario tipo){
		  
		  List<Usuario> usuarios = usuarioRepositorio.findAllByTipoUsuario(tipo);
		  List<RetornoUsuario> usarioRetornos = new ArrayList<>();
		  
		  for(Usuario usuario: usuarios){
			  RetornoUsuario retorno = new RetornoUsuario(
					  usuario.getId(),
					  usuario.getUser(),
					  usuario.getTipoUsuario().getNome(),
					  usuario.getNomeCompleto(),
					  usuario.getTelefone(),
					  usuario.getDataNascimento());
			  usarioRetornos.add(retorno);
		  }
	
			return new ResponseEntity<>(usarioRetornos, HttpStatus.OK);
	  }
}
