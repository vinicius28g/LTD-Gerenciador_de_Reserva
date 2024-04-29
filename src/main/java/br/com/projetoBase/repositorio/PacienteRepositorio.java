package br.com.projetoBase.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoBase.modelo.Paciente;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long>{

}
