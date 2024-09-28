package br.com.projetoBase.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoBase.modelo.Local;

public interface LocalRepositorio extends JpaRepository<Local, Long>{
	
}
