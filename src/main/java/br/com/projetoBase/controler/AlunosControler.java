/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoBase.controler;

import br.com.projetoBase.Service.UsuarioService;
import br.com.projetoBase.dto.CadastroAluno;
import br.com.projetoBase.dto.CadastroPaciente;
import br.com.projetoBase.dto.RetornoAluno;
import br.com.projetoBase.modelo.Aluno;
import br.com.projetoBase.modelo.Pessoa;
import br.com.projetoBase.modelo.TipoUsuario;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.PessoaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.HttpResource;
import br.com.projetoBase.repositorio.AlunoRepositorio;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author gustavo
 */
@RestController
@RequestMapping("/home/alunos")
@CrossOrigin("*")
public class AlunosControler {
        @Autowired
        private AlunoRepositorio alunoRepositorio;
        @Autowired
        private PessoaRepositorio pessoaRepositorio;
        @Autowired
        private UsuarioService usuarioService;

         @GetMapping("/listAll")
            public ResponseEntity<?> listar(){
               return new ResponseEntity<>(alunoRepositorio.findAll(),
                       HttpStatus.OK);
                }
            
        @PostMapping("/salvar")
            public ResponseEntity<?> salvar(@RequestBody CadastroAluno CadastroAluno){
                    Usuario usuario = new Usuario();
                    Aluno aluno = new Aluno();

                   Pessoa pessoa = new Pessoa();
                   usuario.setTipoUsuario(TipoUsuario.PACIENTE);
                   usuario.setUser(CadastroAluno.user());
                   usuario.setPass(new BCryptPasswordEncoder().encode(CadastroAluno.pass()));

                   pessoa = CadastroAluno.aluno().getPessoa();
                   aluno.setPessoa(pessoa);

                   usuario.setPessoa(pessoa);
                   pessoaRepositorio.save(pessoa);
                   Usuario usuarioSalvo = usuarioService.salvar(usuario);
                   alunoRepositorio.save(aluno);

                   RetornoAluno retornoPaciente  = new RetornoAluno(usuarioSalvo, aluno);

                  return new ResponseEntity<>(retornoPaciente,HttpStatus.CREATED);
               }
                @GetMapping("/lisparPor{id}")
                public ResponseEntity<?> buscar(@PathVariable Long id){
                     Optional<Aluno> alunoOptional = alunoRepositorio.findById(id);
                     Aluno aluno = alunoOptional.get(); 
                   return new ResponseEntity<>(aluno,
                            HttpStatus.OK);
                }
}
