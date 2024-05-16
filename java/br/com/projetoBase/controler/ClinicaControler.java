/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoBase.controler;

import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.repositorio.ClinicaRepositorio;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gustavo
 */
@RestController
@RequestMapping("/home/clinica")
@CrossOrigin(origins = "*")
public class ClinicaControler {

    private final ClinicaRepositorio clinicaRepositorio;

    public ClinicaControler(ClinicaRepositorio clinicaRepositorio) {
        this.clinicaRepositorio = clinicaRepositorio;
    }
    
    @GetMapping()
    public ResponseEntity<?> listar(){
       return new ResponseEntity<>(clinicaRepositorio.findAll(),
                HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<?> salvar(@RequestBody Clinica clinica){
       return new ResponseEntity<>(clinicaRepositorio.save(clinica),
                HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
       return new ResponseEntity<>(clinicaRepositorio.findById(id),
                HttpStatus.OK);
    }
}

