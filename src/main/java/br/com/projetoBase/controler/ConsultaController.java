package br.com.projetoBase.controler;

import br.com.projetoBase.Service.ClinicaService;
import br.com.projetoBase.Service.ConsultaService;
import br.com.projetoBase.Service.UsuarioService;
import br.com.projetoBase.dto.*;
import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.Consulta;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.ConsultaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
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

    @PostMapping("/salvar")
    public ResponseEntity agendar (@RequestBody AgendarConsultaDTO agendarConsultaDTO) {
        Consulta consulta = new Consulta();

        consulta.setPaciente(usuarioService.buscarByUser(agendarConsultaDTO.paciente()));

        Clinica clinica = agendarConsultaDTO.clinica();
        consulta.setClinica(clinica);

        LocalDate diaConsulta = agendarConsultaDTO.dia();
        LocalTime inicio = agendarConsultaDTO.horaInicio();
        LocalTime fim = agendarConsultaDTO.horaFim();
        if (consultaService.isHorarioOcupado(clinica, diaConsulta, inicio, fim)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Horário de consulta indisponível para o horário " +inicio+ " até " +fim+ " no data " +diaConsulta+ ".");
        }

        if (consultaService.contarPorData(clinica, diaConsulta) >= clinica.getQuantidadeMax()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantidade máxima de consultas atingidas para o dia " +diaConsulta+".");
        }

        consulta.setDiaSemana(diaConsulta);
        consulta.setHorarioInicio(inicio);
        consulta.setHorarioFim(fim);
        consulta.setInformacoesGerais(agendarConsultaDTO.info());
        consultaRepository.save(consulta);

        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);

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
    public ResponseEntity infoConsulta (@RequestBody @Valid InfoDTO infoDTO) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(infoDTO.consulta());
        Usuario usuarioFuncionario = usuarioService.buscarByUser(infoDTO.user());
        Consulta consulta = new Consulta();

        if (consultaOptional.isPresent()) {
            consulta = consultaOptional.get();
            consulta.setInformacoesGerais(infoDTO.info());
            consulta.setFuncionario(usuarioFuncionario);
            consultaRepository.save(consulta);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
    }
}