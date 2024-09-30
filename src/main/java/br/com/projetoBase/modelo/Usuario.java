package br.com.projetoBase.modelo;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario extends EntidadeAbstrata implements UserDetails {


    public Usuario() {}

    public Usuario(String user, String password, String nome, TipoUsuario tipoUsuario) {
        this.user = user;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        
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
	
	private String cpf;
	
	private String matricula;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataUpdate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDelete;
	
	@ManyToMany(mappedBy = "gerenciadores")
	private Set<Evento> eventos= new HashSet<>();
//	
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	switch (tipoUsuario.getCodigo()) {
			case 1: {
				return List.of( new SimpleGrantedAuthority(TipoUsuario.COORDENADOR.getNome()),
						new SimpleGrantedAuthority(TipoUsuario.PROFESSOR.getNome()),
						new SimpleGrantedAuthority(TipoUsuario.ALUNO.getNome()));
			}
			case 2: {
				return List.of(new SimpleGrantedAuthority(TipoUsuario.PROFESSOR.getNome()),
						new SimpleGrantedAuthority(TipoUsuario.ALUNO.getNome()));
			}
			case 3: {
				return List.of(new SimpleGrantedAuthority(TipoUsuario.ALUNO.name()));
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


	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Date getDataDelete() {
		return dataDelete;
	}

	public void setDataDelete(Date dataDelete) {
		this.dataDelete = dataDelete;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}
	
	

}
