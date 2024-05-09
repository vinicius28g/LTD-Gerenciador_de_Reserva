package br.com.projetoBase.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoBase.Service.UsuarioService;
import br.com.projetoBase.configuracoes.TokenService;
import br.com.projetoBase.dto.Login;
import br.com.projetoBase.dto.UsuarioCadastro;
import br.com.projetoBase.dto.RetornoLogin;
import br.com.projetoBase.dto.RetornoUsuario;
import br.com.projetoBase.modelo.TipoUsuario;
import br.com.projetoBase.modelo.Usuario;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "*")
public class UsuarioControler {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioService usuarioService;

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

            RetornoLogin RetornoLogin = new RetornoLogin(
                    usuario.getNomeCompleto(),
                    usuario.getTipoUsuario(),
                    tokenService.gerarToken(usuario)
            );

            return new ResponseEntity<>(RetornoLogin,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("usuario ou senha invalido",HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PostMapping("/salvar/usuario")
    public ResponseEntity<?> salvarAdm(@RequestBody UsuarioCadastro usuarioCadastro){
    	return usuarioService.salvar(usuarioCadastro, TipoUsuario.ADMIN);
    }
    
    @Transactional
    @PostMapping("/salvar/professor")
    public ResponseEntity<?> salvarPorfessor(@RequestBody UsuarioCadastro usuarioCadastro){
    	return usuarioService.salvar(usuarioCadastro, TipoUsuario.PROFESSOR);
    }
    
    @Transactional
    @PostMapping("/salvar/estagiario")
    public ResponseEntity<?> salvarEstagiario(@RequestBody UsuarioCadastro usuarioCadastro){
    	return usuarioService.salvar(usuarioCadastro, TipoUsuario.ESTAGIARIO);
    }
    
    @Transactional
    @PostMapping("/salvar/paciente")
    public ResponseEntity<?> salvarPaciente(@RequestBody UsuarioCadastro usuarioCadastro){
    	return usuarioService.salvar(usuarioCadastro, TipoUsuario.PACIENTE);
    }
    
    // --------------------------- editar ------------------------------
    @Transactional
    @PutMapping("/editar/usuario")
    public ResponseEntity<?> editarAdm(@RequestBody UsuarioCadastro usuarioCadastro){
    	return usuarioService.editar(usuarioCadastro, TipoUsuario.ADMIN);
    }
    
    @Transactional
    @PutMapping("/editar/professor")
    public ResponseEntity<?> editarPorfessor(@RequestBody UsuarioCadastro usuarioCadastro){
    	return usuarioService.editar(usuarioCadastro, TipoUsuario.PROFESSOR);
    }
    
    @Transactional
    @PutMapping("/editar/estagiario")
    public ResponseEntity<?> editarEstagiario(@RequestBody UsuarioCadastro usuarioCadastro){
    	return usuarioService.editar(usuarioCadastro, TipoUsuario.ESTAGIARIO);
    }
    
    @Transactional
    @PutMapping("/editar/paciente")
    public ResponseEntity<?> editarPaciente(@RequestBody UsuarioCadastro usuarioCadastro){
    	return usuarioService.editar(usuarioCadastro, TipoUsuario.PACIENTE);
    }
    //---------------- listAll ------------------------------------
    
    @GetMapping("/listAll/usuario")
    public ResponseEntity<?> listAllUsuario(){
    	return usuarioService.listAll(TipoUsuario.ADMIN);
    }
    
    @GetMapping("/listAll/professor")
    public ResponseEntity<?> listAllProfessor(){
    	return usuarioService.listAll(TipoUsuario.PROFESSOR);
    }
    
    @GetMapping("/listAll/estagiarrio")
    public ResponseEntity<?> listAllEstagiario(){
    	return usuarioService.listAll(TipoUsuario.ESTAGIARIO);
    }
    
    @GetMapping("/listAll/paciente")
    public ResponseEntity<?> listAllPaciente(){
    	return usuarioService.listAll(TipoUsuario.PACIENTE);
    }
    
    /*
     * get generico pos qualquer pessoa pode ver os usuario já que não vao modificar nada
     * apenas ver as informações basicas. 
     */
    public ResponseEntity<?> getUsuario(@RequestBody long id){
    	Usuario usuario = new Usuario();
    	usuario = usuarioService.buscarPorId(id);
    	RetornoUsuario retorno = new RetornoUsuario(
    			usuario.getId(), 
    			usuario.getUser(), 
    			usuario.getTipoUsuario().getNome(),
    			usuario.getNomeCompleto(), 
    			usuario.getTelefone(), 
    			usuario.getDataNascimento());
    	return new ResponseEntity<>(retorno, HttpStatus.OK);
    }
    
    
    public TipoUsuario pegarUsuario() {
    	//obtem a autenticação do usuario logado
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null && authentication.isAuthenticated()) {
    		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
    		return usuarioLogado.getTipoUsuario();
    	}
    	return null;
	}
   

}
