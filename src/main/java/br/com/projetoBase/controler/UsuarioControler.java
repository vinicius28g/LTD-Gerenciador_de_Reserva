package br.com.projetoBase.controler;

import br.com.projetoBase.configuracoes.TokenService;
import br.com.projetoBase.dto.Login;
import br.com.projetoBase.dto.UsuarioCadastro;
import br.com.projetoBase.dto.UsuarioRetorno;
import br.com.projetoBase.modelo.TipoUsuario;
import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tcc")
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
                    usuario.getNome(),
                    usuario.getTipoUsuario(),
                    tokenService.gerarToken(usuario)
            );

            return new ResponseEntity<>(usuarioRetorno,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/usuario")
    public ResponseEntity<?> salvar(@RequestBody UsuarioCadastro usuarioCadastro){

        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(usuarioCadastro.tipoUsuario());
        usuario.setNome(usuarioCadastro.nome());
        usuario.setUser(usuarioCadastro.user());
        usuario.setPass(new BCryptPasswordEncoder().encode(usuarioCadastro.pass()));

        Usuario usuarioSalvo = usuarioRepositorio.save(usuario);

        return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
    }

    @PostMapping("/professor/{offset}/{pageSize}")
    public ResponseEntity<?> listar(@PathVariable int offset,
                                    @PathVariable int pageSize){

        Specification<Usuario> usuarioSpecification = ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoUsuario"), TipoUsuario.USER));

        return new ResponseEntity<>(usuarioRepositorio.findAll(
                usuarioSpecification,
                PageRequest.of(offset,pageSize)),
                HttpStatus.OK);

    }
    @GetMapping("/carregarUser")
    public ResponseEntity<?> carregarUser(@AuthenticationPrincipal Usuario usuario){
        UsuarioRetorno usuarioRetorno = new UsuarioRetorno(
                usuario.getNome(),
                usuario.getTipoUsuario(),
                tokenService.gerarToken(usuario)
        );
        return new ResponseEntity<>(usuarioRetorno,HttpStatus.OK);
    }

}
