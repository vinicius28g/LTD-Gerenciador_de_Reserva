package br.com.projetoBase.configuracoes;

import br.com.projetoBase.modelo.Usuario;
import br.com.projetoBase.repositorio.UsuarioRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token;

        var authorizationHeader = request.getHeader(CostantesSeguranca.HEADER_STRING);

        if(authorizationHeader != null){
            token = authorizationHeader.replace(CostantesSeguranca.PREFIXO_TOKEN, "");
            var subject = this.tokenService.getSubject(token);

            Usuario usuario = this.usuarioRepositorio.findByUser(subject);

            var authetication = new UsernamePasswordAuthenticationToken(usuario,
                    null,
                    usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authetication);
        }

        filterChain.doFilter(request,response);

    }

}
