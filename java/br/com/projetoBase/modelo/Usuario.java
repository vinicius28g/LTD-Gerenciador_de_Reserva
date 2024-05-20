package br.com.projetoBase.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario extends EntidadeAbstrata implements UserDetails {


    public Usuario() {}

    public Usuario(String user, String password, String nome, TipoUsuario tipoUsuario, Clinica clinica) {
        this.user = user;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.clinica = clinica;
    }
	
    @NotNull
    @Column(unique = true)
    private String user;

    @NotNull
    private String password;

    @NotNull
    private TipoUsuario tipoUsuario;

    @NotNull
	private String nomeCompleto;
    
    private String telefone;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	@ManyToOne
	private Clinica clinica;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	switch (tipoUsuario.getCodigo()) {
			case 1: {
				return List.of(new SimpleGrantedAuthority(TipoUsuario.ADMIN.getNome()), 
						new SimpleGrantedAuthority(TipoUsuario.COORDENADOR.getNome()),
						new SimpleGrantedAuthority(TipoUsuario.FUNCIONARIO.getNome()),
						new SimpleGrantedAuthority(TipoUsuario.PACIENTE.getNome()));
			}
			case 2: {
				return List.of(new SimpleGrantedAuthority(TipoUsuario.COORDENADOR.name()),
						new SimpleGrantedAuthority(TipoUsuario.FUNCIONARIO.getNome()),
						new SimpleGrantedAuthority(TipoUsuario.PACIENTE.getNome()));
			}
			case 3: {
				return List.of(new SimpleGrantedAuthority(TipoUsuario.FUNCIONARIO.getNome()),
						new SimpleGrantedAuthority(TipoUsuario.PACIENTE.getNome()));
			}
			case 4: {
				return List.of(new SimpleGrantedAuthority(TipoUsuario.PACIENTE.name()));
			}
			default :{
				throw new IllegalArgumentException("Unexpected value: " + tipoUsuario.getCodigo());
			}
	    }	
    	
    }
   
    @Override
    public String getUsername() {
        return this.user;
    }
    
    @Override
	public String getPassword() {
		return this.password;
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
	
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
