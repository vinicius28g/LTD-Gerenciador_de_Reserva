package br.com.projetoBase.Service;

import br.com.projetoBase.dto.clinica.ClinicaDTO;
import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.ClinicaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ClinicaService {

    private ClinicaRepositorio clinicaRepositorio;

    public ClinicaService(ClinicaRepositorio clinicaRepositorio) {
        this.clinicaRepositorio = clinicaRepositorio;
    }

    public Clinica buscarByUser(Usuario usuario) {

        Clinica clinica = usuario.getClinica();

        return clinicaRepositorio.findById(clinica.getId()).get();
    }

    public  Clinica buscarByName (String clinica) {

        Clinica clinica1 = clinicaRepositorio.findByNome(clinica);
        return clinica1;
    }

    public ClinicaDTO saveClinica (ClinicaDTO clinicaDTO) {
        Clinica clinica = new Clinica();
        clinica.setNome(clinicaDTO.nome());
        clinica.setDescricao(clinicaDTO.descricao());
        clinica.setQuantidadeMax(clinicaDTO.max());
        clinica.setMaxPorHorario(clinicaDTO.maxHora());
        clinica.setInicio(clinicaDTO.inicio());
        clinica.setFim(clinicaDTO.fim());
        clinica.setInicio2(clinicaDTO.inicio2());
        clinica.setFim2(clinicaDTO.fim2());
        clinica.setImage(clinicaDTO.file());
        clinica.setDiasAtendimento(clinicaDTO.diasAtendimento());



            Clinica savedClinica = clinicaRepositorio.save(clinica);
            return new ClinicaDTO(
                    savedClinica.getNome(),
                    savedClinica.getDescricao(),
                    savedClinica.getQuantidadeMax(),
                    savedClinica.getMaxPorHorario(),
                    savedClinica.getInicio(),
                    savedClinica.getFim(),
                    savedClinica.getInicio2(),
                    savedClinica.getFim2(),
                    savedClinica.getImage(),
                    savedClinica.getDiasAtendimento()
            );
    }
}
