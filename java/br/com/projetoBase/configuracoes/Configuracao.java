package br.com.projetoBase.configuracoes;

import br.com.projetoBase.modelo.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.projetoBase.modelo.TipoUsuario;

@Configuration
@EnableWebSecurity
public class Configuracao {

    @Autowired
    private FilterToken filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/home/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/home/validarToken").permitAll()
                .requestMatchers(HttpMethod.POST, "/home/salvar/paciente").permitAll()
                .requestMatchers(HttpMethod.POST, "/home/salvar/usuario").hasAnyAuthority(TipoUsuario.ADMIN.getNome())
                .requestMatchers(HttpMethod.POST, "/home/salvar/coordenador").hasAnyAuthority(TipoUsuario.ADMIN.getNome())
                .requestMatchers(HttpMethod.POST, "/home/salvar/funcionario").hasAnyAuthority(TipoUsuario.COORDENADOR.getNome())
                //----------------------------------- editar ------------------------------
                .requestMatchers(HttpMethod.PUT, "/home/editar/paciente").permitAll()
                .requestMatchers(HttpMethod.PUT, "/home/editar/usuario").hasAnyAuthority(TipoUsuario.ADMIN.getNome())
                .requestMatchers(HttpMethod.PUT, "/home/editar/coordenador").hasAnyAuthority(TipoUsuario.ADMIN.getNome())
                .requestMatchers(HttpMethod.PUT, "/home/editar/funcionario").hasAnyAuthority(TipoUsuario.COORDENADOR.getNome())
              //----------------------------------- consulta ------------------------------
                .requestMatchers(HttpMethod.POST, "/home/consulta/salvar").permitAll()
                .requestMatchers(HttpMethod.GET, "/home/consulta/listar").permitAll()
                .requestMatchers(HttpMethod.GET, "/home/consulta/por-clinica").permitAll()
                .requestMatchers(HttpMethod.PUT, "/home/consulta/agendar").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/home/consulta/deletar").permitAll()
                .requestMatchers(HttpMethod.PUT, "/home/consulta/info").permitAll()
                .requestMatchers(HttpMethod.GET, "/home/consulta/listarByDay").permitAll()
                .requestMatchers(HttpMethod.GET, "/home/consulta/listarByPaciente").permitAll()
//                .requestMatchers(HttpMethod.POST, "/home/clinica/salvar").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
//                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
