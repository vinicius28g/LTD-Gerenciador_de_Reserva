package br.com.projetoBase.repositorio;

import br.com.projetoBase.modelo.TipoUsuario;
import br.com.projetoBase.modelo.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

    Usuario findByUser(String nome);
    List<Usuario> findAllByTipoUsuario(TipoUsuario tipo);
}

