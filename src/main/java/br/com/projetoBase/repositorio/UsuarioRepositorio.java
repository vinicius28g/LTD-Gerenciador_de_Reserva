package br.com.projetoBase.repositorio;

import br.com.projetoBase.modelo.Pessoa;
import br.com.projetoBase.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

    Usuario findByUser(String nome);
    
    Usuario findByPessoa(Pessoa pessoa);
    
//    Usuario save(Usuario usuario);

}

