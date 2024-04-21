package br.com.projetoBase.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoBase.Service.UsuarioService;
import br.com.projetoBase.configuracoes.TokenService;
import br.com.projetoBase.dto.Login;
import br.com.projetoBase.dto.UsuarioCadastro;
import br.com.projetoBase.dto.UsuarioRetorno;
import br.com.projetoBase.modelo.Endereco;
import br.com.projetoBase.modelo.Pessoa;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.EnderecoRepositorio;
import br.com.projetoBase.repositorio.PessoaRepositorio;
import br.com.projetoBase.repositorio.UsuarioRepositorio;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "*")
public class UsuarioControler {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioControler(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private EnderecoRepositorio  enderecoRepositorio;
    

    @GetMapping("/teste")
    public ResponseEntity<?> testar(@AuthenticationPrincipal Usuario usuario){
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login){
        try{
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(login.username(),
                            login.password());

            Authentication authenticate = this.authenticationManager
                    .authenticate(usernamePasswordAuthenticationToken);

            var usuario = (Usuario) authenticate.getPrincipal();

            UsuarioRetorno usuarioRetorno = new UsuarioRetorno(
                    usuario.getPessoa().getNomeCompleto(),
                    usuario.getTipoUsuario(),
                    tokenService.gerarToken(usuario)
            );

            return new ResponseEntity<>(usuarioRetorno,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    @PostMapping("/usuario")
    public ResponseEntity<?> salvar(@RequestBody UsuarioCadastro usuarioCadastro){

        Usuario usuario = new Usuario();
       
        Pessoa pessoa = new Pessoa();
        usuario.setTipoUsuario(usuarioCadastro.tipoUsuario());
        usuario.setUser(usuarioCadastro.user());
        usuario.setPass(new BCryptPasswordEncoder().encode(usuarioCadastro.pass()));
        
        pessoa.setNomeCompleto(usuarioCadastro.nome());
               
        usuario.setPessoa(pessoa);
        pessoaRepositorio.save(pessoa);
        Usuario usuarioSalvo = usuarioService.salvar(usuario);

        return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
    }

//    @PostMapping("/professor/{offset}/{pageSize}")
//    public ResponseEntity<?> listar(@PathVariable int offset,
//                                    @PathVariable int pageSize){
//
//        Specification<Usuario> usuarioSpecification = ((root, query, criteriaBuilder) ->
//                criteriaBuilder.equal(root.get("tipoUsuario"), TipoUsuario.USER));
//
//        return new ResponseEntity<>(usuarioRepositorio.findAll(
//                usuarioSpecification,
//                PageRequest.of(offset,pageSize)),
//                HttpStatus.OK);
//
//    }
    @GetMapping("/carregarUser")
    public ResponseEntity<?> carregarUser(@AuthenticationPrincipal Usuario usuario){
        UsuarioRetorno usuarioRetorno = new UsuarioRetorno(
                usuario.getPessoa().getNomeCompleto(),
                usuario.getTipoUsuario(),
                tokenService.gerarToken(usuario)
        );
        return new ResponseEntity<>(usuarioRetorno,HttpStatus.OK);
    }
    @PostMapping("/teste2")
    public ResponseEntity<?>teste123(@RequestBody int numero){
    	System.out.println(numero);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
   

}
