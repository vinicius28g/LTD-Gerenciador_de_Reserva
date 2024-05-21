package br.com.projetoBase.Service;

import br.com.projetoBase.dto.clinica.ConsultasDisponiveisResponseDTO;
import br.com.projetoBase.dto.clinica.IntervaloHorarioDTO;
import br.com.projetoBase.dto.consulta.ConsultasDTO;
import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.Consulta;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    UsuarioService usuarioService;

    public List<ConsultasDTO> findByPaciente (String nomePaciente) {
        Usuario paciente = usuarioService.buscarByUser(nomePaciente);

        List<Consulta> consultasDoPaciente = consultaRepository.findByPaciente(paciente);

        return consultasDoPaciente.stream()
                .map(this::mapToConsultaDTO)
                .collect(Collectors.toList());
    }

    public ConsultasDisponiveisResponseDTO isHorarioOcupado (Clinica clinica, LocalDate data, LocalTime inicio, LocalTime fim) {
       int limiteConsultasHora = clinica.getMaxPorHorario();

       List<IntervaloHorarioDTO> intervaloHorarioDTOS = new ArrayList<>();
       LocalTime intervaloAtual = inicio;

       while (intervaloAtual.isBefore(fim)) {
           LocalTime proxIntervalo = intervaloAtual.plusHours(1);
           if (proxIntervalo == fim) {
               proxIntervalo = fim;
           }

           int consultasAgendadas = consultaRepository.countByClinicaAndDiaSemanaAndHorarioInicioAndHorarioFim(clinica, data, intervaloAtual, proxIntervalo);
           int consultasDisponiveis = limiteConsultasHora - consultasAgendadas;

           if (consultasDisponiveis < 0) {
               consultasDisponiveis = 0;
           }

           if (consultasDisponiveis > 0) {
               IntervaloHorarioDTO intervaloHorarioDTO = new IntervaloHorarioDTO(intervaloAtual, proxIntervalo, consultasDisponiveis);
               intervaloHorarioDTOS.add(intervaloHorarioDTO);
           }

           intervaloAtual = proxIntervalo;
       }

        return new ConsultasDisponiveisResponseDTO(limiteConsultasHora, intervaloHorarioDTOS);

    }

    public long contarPorData (Clinica clinica, LocalDate data){
        return consultaRepository.countByClinicaAndDiaSemana(clinica, data);
    }

    public List<ConsultasDTO> listarAll () {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream()
                .map(this::mapToConsultaDTO)
                .collect(Collectors.toList());
    }

    public  List<ConsultasDTO> listarByClinicaDTO (Clinica clinica) {
        List<Consulta> consultasByClinica = consultaRepository.findByClinica(clinica);
        return consultasByClinica.stream()
                .map(this::mapToConsultaDTO)
                .collect(Collectors.toList());
    }

    public ConsultasDTO mapToConsultaDTO (Consulta consulta) {
        String nomePaciente = consulta.getPaciente().getNomeCompleto();
        String nomeFuncionario = consulta.getFuncionario() != null ? consulta.getFuncionario().getNomeCompleto() : "Aguardando atualizações...";

        return new ConsultasDTO(
                consulta.getId(),
                nomePaciente,
                nomeFuncionario,
                consulta.getClinica(),
                consulta.getDiaSemana(),
                consulta.getHorarioInicio(),
                consulta.getHorarioFim(),
                consulta.getInformacoesGerais()
        );
    }

    public List<ConsultasDTO> listarByDayEClinica(Clinica clinica, LocalDate date) {
        List<Consulta> ConsultasByDay = consultaRepository.findByClinicaAndDiaSemana(clinica, date);
        return ConsultasByDay.stream()
                .map(this::mapToConsultaDTO)
                .collect(Collectors.toList());
    }
}
