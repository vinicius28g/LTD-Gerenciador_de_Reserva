package br.com.projetoBase.Service;

import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.ClinicaRepositorio;
import org.springframework.stereotype.Service;

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
}
