/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoBase.controler;

import br.com.projetoBase.Service.ClinicaService;
import br.com.projetoBase.dto.clinica.ClinicaDTO;
import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.DiasSemanas;
import br.com.projetoBase.repositorio.ClinicaRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Set;

/**
 *
 * @author gustavo
 */
@RestController
@RequestMapping("/home/clinica")
@CrossOrigin(origins = "*")
public class ClinicaControler {

    private final ClinicaRepositorio clinicaRepositorio;

    @Autowired
    private ClinicaService clinicaService;

    public ClinicaControler(ClinicaRepositorio clinicaRepositorio) {
        this.clinicaRepositorio = clinicaRepositorio;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
       return new ResponseEntity<>(clinicaRepositorio.findAll(),
                HttpStatus.OK);
    }
    @PostMapping("/salvar")
    public ClinicaDTO salvar( @RequestParam("nome") String nome,
                              @RequestParam("descricao") String descricao,
                              @RequestParam("max") Long max,
                              @RequestParam("maxHora") int maxHora,
                              @RequestParam("inicio") String inicio,
                              @RequestParam("fim") String fim,
                              @RequestParam("inicio2") String inicio2,
                              @RequestParam("fim2") String fim2,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("diasAtendimento") String diasAtendimento) throws IOException {

                ClinicaDTO clinicaDTO = new ClinicaDTO(
                        nome,
                        descricao,
                        max,
                        maxHora,
                        LocalTime.parse(inicio),
                        LocalTime.parse(fim),
                        LocalTime.parse(inicio2),
                        LocalTime.parse(fim2),
                        file.getBytes(),
                        diasAtendimento
                );

                return clinicaService.saveClinica(clinicaDTO);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
       return new ResponseEntity<>(clinicaRepositorio.findById(id),
                HttpStatus.OK);
    }
}

