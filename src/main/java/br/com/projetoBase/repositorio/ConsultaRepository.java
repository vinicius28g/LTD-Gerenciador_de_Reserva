package br.com.projetoBase.repositorio;

import br.com.projetoBase.dto.consulta.ListByConsultasDTO;
import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.Consulta;
import br.com.projetoBase.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByClinica(Clinica clinica);

    List<Consulta> findByClinica(ListByConsultasDTO list);

    long countByClinicaAndDiaSemana(Clinica clinica, LocalDate data);

    int countByClinicaAndDiaSemanaAndHorarioInicioAndHorarioFim(Clinica clinica, LocalDate data, LocalTime inicio, LocalTime fim);

    List<Consulta> findByClinicaAndDiaSemana(Clinica clinica, LocalDate date);

    List<Consulta> findByPaciente(Usuario paciente);
}
