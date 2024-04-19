/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoBase.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author gustavo
 */
@Entity
public class Clinica extends EntidadeAbstrata{
    private String nome;
    private String descricao;
}

