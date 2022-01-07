package br.edu.ifrn.ifstudy.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * #####################################
 * 
 * Objetivo:    Esta classe serve para criar a entidade Usuário
 * 
 * @author Ana Beatriz (beatriz.monteiro@escolar.ifrn.edu.br)
 * Dâmarys Nascimento (damarys.n@escolar.ifrn.edu.br)
 * Klyfithon Paulo (klyfithon.p@escolar.ifrn.edu.br)
 * 
 * Data de Criação:    05/01/2022
 * 
 * #####################################
 * 
 * Última alteração:   
 * 
 * @author 
 * Data:    
 * Alteração:
 * 
 * #####################################
 * 
 * @version 1.0
 * 
 * #####################################
 * 
 */

@Entity

public class Usuario {
	
	
	public static final String 	PROF ="PROF";
	public static final String ALUNO ="ALUNO";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@NotBlank(message = "O campo nome é obrigatório.")
	@Size(min = 2, message = "O nome deve ter no mínimo 2 caracteres")
	private String nome;
	
	@Column(nullable = false)
	@NotBlank(message = "O campo senha é obrigatório.")
	private String email;
	
	@Column(nullable = false)
	@NotBlank(message = "O campo senha é obrigatório.")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
	private String senha;
	
	@Column(nullable = false)
	@NotBlank(message="O campo escolaridade é obrigatório. ")
	private String escolaridade;
	
	@OneToMany(mappedBy="usuario")
	private List<Relatar> relatar;
	
	@Column(nullable = false)
	private String perfil= ALUNO;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEscolaridade() {
		return escolaridade;
	}
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	
	
	
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public List<Relatar> getRelatar() {
		return relatar;
	}
	public void setRelatar(List<Relatar> relatar) {
		this.relatar = relatar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
