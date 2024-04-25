package br.com.projetoBase.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoBase.modelo.Pessoa;

public interface PessoaRepositorio extends JpaRepository<Pessoa, Long>{
	
	Pessoa findByNomeCompleto(String NomeCompleto);

}
