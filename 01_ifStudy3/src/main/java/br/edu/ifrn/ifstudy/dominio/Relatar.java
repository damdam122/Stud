package br.edu.ifrn.ifstudy.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

/**
 * 
 * #####################################
 * 
 * Objetivo:    Esta classe serve para criar a entidade Relatar
 * que tem como funcionalidade armazenar os dados inseridos pelo usuário
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
public class Relatar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRelatar;
	
	
	@Column
	private String documento;
	
	@Column(nullable = false)
	@NotBlank(message = "O campo Matéria é obrigatório")
	private String materia;
	
	@Column(nullable = false)
	@NotBlank(message = "O campo Conteudo é obrigatório")
	private String conteudo;
	
	@Column(nullable = false)
	@NotBlank(message = "O campo Data é obrigatório")
	private String data;
	
	//@NotBlank(message = "O campo de tempo estudado é obrigatório. Lembre-se que é por minuto")
	@Column(nullable = false)
	private int duracao;
	
	@Column(nullable = false)
	private String metodo;
	
	@Column(nullable = false)
	private int numeroQuestao;
	
	@Column(nullable = false)
	private int contNumeroQuestao = 0;
	
	@Column(nullable = false)
	private int numeroAcerto;
	
	@Column(nullable = false)
	private int numeroErro;
	
	/*@Column(nullable = false)
	private String radioFacil;
	
	@Column(nullable = false)
	private String radioMedio;
	
	@Column(nullable = false)
	private String radioDificil;*/
	
	//um cadastro do estudo para muitos arquivos estudados
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List <Arquivo> arquivos;
	
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;
	
	public int getIdRelatar() {
		return idRelatar;
	}
	public void setIdRelatar(int idRelatar) {
		this.idRelatar = idRelatar;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public int getNumeroQuestao() {
		return numeroQuestao;
	}
	public void setNumeroQuestao(int numeroQuestao) {
		this.numeroQuestao = numeroQuestao;
	}
	
	public int getContNumeroQuestao() {
		return contNumeroQuestao;
	}
	public void setContNumeroQuestao(int contNumeroQuestao) {
		this.contNumeroQuestao = contNumeroQuestao;
	}
	public int getNumeroAcerto() {
		return numeroAcerto;
	}
	public void setNumeroAcerto(int numeroAcerto) {
		this.numeroAcerto = numeroAcerto;
	}
	public int getNumeroErro() {
		return numeroErro;
	}
	public void setNumeroErro(int numeroErro) {
		this.numeroErro = numeroErro;
	}
	/*public String getRadioFacil() {
		return radioFacil;
	}
	public void setRadioFacil(String radioFacil) {
		this.radioFacil = radioFacil;
	}
	public String getRadioMedio() {
		return radioMedio;
	}
	public void setRadioMedio(String radioMedio) {
		this.radioMedio = radioMedio;
	}
	public String getRadioDificil() {
		return radioDificil;
	}
	public void setRadioDificil(String radioDificil) {
		this.radioDificil = radioDificil;
	}*/
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public List<Arquivo> getArquivos() {
		return arquivos;
	}
	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRelatar;
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
		Relatar other = (Relatar) obj;
		if (idRelatar != other.idRelatar)
			return false;
		return true;
	}

}
