package br.com.projetoBase.repositorio;

import br.com.projetoBase.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>,
        JpaSpecificationExecutor<Usuario>,
        JpaRepository<Usuario, Long> {

    Usuario findByUser(String nome);

}
