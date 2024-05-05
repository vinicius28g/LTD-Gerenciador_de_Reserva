/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.projetoBase.repositorio;

import br.com.projetoBase.modelo.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gustavo
 */
public interface AlunoRepositorio extends JpaRepository<Aluno, Long>{
    
}
