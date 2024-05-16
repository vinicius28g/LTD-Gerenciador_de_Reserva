
package br.com.projetoBase.repositorio;

import br.com.projetoBase.modelo.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ClinicaRepositorio extends JpaRepository<Clinica, Long>,
        JpaSpecificationExecutor<Clinica>, CrudRepository<Clinica, Long>{
    
}
