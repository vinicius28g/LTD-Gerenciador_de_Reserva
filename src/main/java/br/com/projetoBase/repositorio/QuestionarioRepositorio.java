package br.com.projetoBase.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.projetoBase.modelo.Questionario;

public interface QuestionarioRepositorio extends CrudRepository<Questionario, Long>,
JpaSpecificationExecutor<Questionario>,
JpaRepository<Questionario, Long> {

}
