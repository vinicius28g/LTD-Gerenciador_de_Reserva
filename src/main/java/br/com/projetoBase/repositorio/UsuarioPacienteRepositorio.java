package br.com.projetoBase.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.projetoBase.modelo.UsuarioPaciente;

public interface UsuarioPacienteRepositorio extends CrudRepository<UsuarioPaciente, Long>,
    JpaSpecificationExecutor<UsuarioPaciente>,
    JpaRepository<UsuarioPaciente, Long> {
	@Query(value = "SELECT * FROM usuario_paciente WHERE cpf = :cpf", nativeQuery = true)
	Optional<UsuarioPaciente> buscarPorCpf(@Param("cpf") String cpf);
}

