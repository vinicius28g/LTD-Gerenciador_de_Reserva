
package br.com.projetoBase.repositorio;

import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicaRepositorio extends JpaRepository<Clinica, Long>{

    Clinica findByNome(String clinica);

    Clinica findById(Clinica clinicaID);
}
