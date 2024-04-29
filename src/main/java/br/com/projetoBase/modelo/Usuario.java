package br.com.projetoBase.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class Usuario extends EntidadeAbstrata implements UserDetails {

    @NotNull
    private String user;

    @NotNull
    private String pass;

    @NotNull
    private TipoUsuario tipoUsuario;
    @OneToOne
    @NotNull
    private Pessoa pessoa;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	switch (tipoUsuario.getCodigo()) {
		case 1: {
			return List.of(new SimpleGrantedAuthority(TipoUsuario.ADMIN.getNome()), new SimpleGrantedAuthority(TipoUsuario.PACIENTE.getNome()));
		}
		case 2: {
			return List.of(new SimpleGrantedAuthority(TipoUsuario.PACIENTE.name()));
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + tipoUsuario.getCodigo());
		}
        
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

    @Override
    public String getUsername() {
        return this.user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
    
    
    
}
