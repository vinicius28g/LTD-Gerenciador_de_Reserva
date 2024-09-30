package br.com.projetoBase.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoBase.modelo.Evento;

public interface EventoRepositorio extends JpaRepository<Evento, Long> {

}
