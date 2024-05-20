package br.com.projetoBase.Service;

import br.com.projetoBase.dto.ConsultasDTO;
import br.com.projetoBase.dto.ListByConsultasDTO;
import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.Consulta;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public boolean isHorarioOcupado (Clinica clinica, LocalDate data, LocalTime inicio, LocalTime fim) {
        long quantidadeConsultas = consultaRepository.countByClinicaAndDiaSemanaAndHorarioInicioAndHorarioFim(clinica, data, inicio, fim);
        return quantidadeConsultas >= clinica.getMaxPorHorario();
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

    private ConsultasDTO mapToConsultaDTO (Consulta consulta) {
        Usuario nomePaciente = consulta.getPaciente();
        Usuario nomeFuncionario = consulta.getFuncionario();

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
