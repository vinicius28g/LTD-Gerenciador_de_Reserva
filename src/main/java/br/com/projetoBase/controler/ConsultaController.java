package br.com.projetoBase.controler;

import br.com.projetoBase.Service.ClinicaService;
import br.com.projetoBase.Service.ConsultaService;
import br.com.projetoBase.Service.UsuarioService;
import br.com.projetoBase.dto.*;
import br.com.projetoBase.dto.clinica.ConsultasDisponiveisResponseDTO;
import br.com.projetoBase.dto.clinica.IntervaloHorarioDTO;
import br.com.projetoBase.dto.clinica.VerificarHorariosRequestDTO;
import br.com.projetoBase.dto.consulta.*;
import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.Consulta;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.ClinicaRepositorio;
import br.com.projetoBase.repositorio.ConsultaRepository;
import com.sun.jdi.PrimitiveValue;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home/consulta")
public class ConsultaController {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ClinicaRepositorio clinicaRepositorio;

    @PostMapping("/salvar")
    public ResponseEntity agendar (@RequestBody AgendarConsultaDTO agendarConsultaDTO, @AuthenticationPrincipal Usuario usuarioPaciente) {
        Consulta consulta = new Consulta();

        consulta.setPaciente(usuarioPaciente);

        Optional<Clinica> clinicaOptional = clinicaRepositorio.findById(agendarConsultaDTO.clinica());
        if (clinicaOptional.isPresent()) {
            Clinica clinica = clinicaOptional.get();

            consulta.setClinica(clinica);

            LocalDate diaConsulta = agendarConsultaDTO.dia();
            LocalTime inicio = agendarConsultaDTO.horaInicio();
            LocalTime fim = agendarConsultaDTO.horaFim();

            if (consultaService.contarPorData(clinica, diaConsulta) >= clinica.getQuantidadeMax()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantidade máxima de consultas atingidas para o dia " +diaConsulta+".");
            }

            consulta.setDiaSemana(diaConsulta);
            consulta.setHorarioInicio(inicio);
            consulta.setHorarioFim(fim);
            consulta.setInformacoesGerais(agendarConsultaDTO.info());
            consultaRepository.save(consulta);

            return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.mapToConsultaDTO(consulta));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/horario-disponiveis")
    public ResponseEntity<ConsultasDisponiveisResponseDTO> verificarHorariosDisponiveis (@RequestBody VerificarHorariosRequestDTO dto) {
        Optional<Clinica> clinicaOptional = clinicaRepositorio.findById(dto.clinica());
        if (clinicaOptional.isPresent()){
            Clinica clinica = clinicaOptional.get();

            List<IntervaloHorarioDTO> HorarioManha = null;
            if (clinica.getInicio() != null && clinica.getFim() != null) {
                ConsultasDisponiveisResponseDTO responseDTO = consultaService.isHorarioOcupado(clinica, dto.data(), clinica.getInicio(), clinica.getFim());
                HorarioManha = responseDTO.intervaloHorarioDTOS();
            }


            List<IntervaloHorarioDTO> HorarioTarde = null;
            if (clinica.getInicio2() != null && clinica.getFim2() != null) {
                ConsultasDisponiveisResponseDTO responseDTO = consultaService.isHorarioOcupado(clinica, dto.data(), clinica.getInicio2(), clinica.getFim2());
                HorarioTarde = responseDTO.intervaloHorarioDTOS();
            }


            List<IntervaloHorarioDTO> horariosDisponiveisCombinados = new ArrayList<>();
            if (HorarioManha != null) {
                horariosDisponiveisCombinados.addAll(HorarioManha);
            }
            if (HorarioTarde != null) {
                horariosDisponiveisCombinados.addAll(HorarioTarde);
            }
            
            ConsultasDisponiveisResponseDTO combinedResponseDTO = new ConsultasDisponiveisResponseDTO(clinica.getMaxPorHorario(), horariosDisponiveisCombinados);
            return ResponseEntity.ok(combinedResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listarByDay")
    public ResponseEntity<List<ConsultasDTO>> listarByDay (@RequestBody @Valid ConsultasByDayDTO consultas) {
        List<ConsultasDTO> consultasDTOS = consultaService.listarByDayEClinica(consultas.clinica(), consultas.date());
        return new ResponseEntity<>(consultasDTOS, HttpStatus.OK);
    }

    @GetMapping("/listarByPaciente")
    public ResponseEntity<List<ConsultasDTO>> listarByPaciente (@RequestBody @Valid ListByPacienteDTO pacienteDTO) {
        List<ConsultasDTO> consultasDTOS = consultaService.findByPaciente(pacienteDTO.paciente());
        return new ResponseEntity<>(consultasDTOS, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity <List<ConsultasDTO>> listarAll() {
        List<ConsultasDTO> consultasDTOS = consultaService.listarAll();
        return new ResponseEntity<>(consultasDTOS, HttpStatus.OK);
    }

    @GetMapping("/por-clinica")
    public ResponseEntity<List<ConsultasDTO>> ListarByClinica (@RequestBody @Valid ListByConsultasDTO clinicaDTO) {
        Clinica clinica = clinicaDTO.clinica();
        List<ConsultasDTO> consultasByClinicasDTO = consultaService.listarByClinicaDTO(clinica);
        return new ResponseEntity<>(consultasByClinicasDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletar (@RequestBody @Valid DeletarDTO deletarDTO) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(deletarDTO.id());
        if(consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();
            consultaRepository.delete(consulta);
           return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/info")
    public ResponseEntity infoConsulta (@RequestBody @Valid InfoDTO infoDTO, @AuthenticationPrincipal Usuario usuario) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(infoDTO.consulta());
        Consulta consulta = new Consulta();

        if (consultaOptional.isPresent()) {
            consulta = consultaOptional.get();
            consulta.setInformacoesGerais(infoDTO.info());
            consulta.setFuncionario(usuario);
            consultaRepository.save(consulta);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
    }
}