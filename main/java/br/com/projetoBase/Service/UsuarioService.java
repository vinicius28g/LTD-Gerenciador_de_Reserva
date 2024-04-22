package br.com.projetoBase.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.UsuarioRepositorio;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}
}
