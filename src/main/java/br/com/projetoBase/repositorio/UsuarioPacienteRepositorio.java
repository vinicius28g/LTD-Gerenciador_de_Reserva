package br.com.projetoBase.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.projetoBase.modelo.UsuarioPaciente;

public interface UsuarioPacienteRepositorio extends CrudRepository<UsuarioPaciente, Long>,
    JpaSpecificationExecutor<UsuarioPaciente>,
    JpaRepository<UsuarioPaciente, Long> {

}

