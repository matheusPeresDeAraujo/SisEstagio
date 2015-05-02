package br.edu.granbery.sisestagio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.edu.granbery.sisestagio.model.TermoCompromisso;

@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAluno")
	private Integer idAluno;
	private String matricula;
	private String nome;
	private String rua;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String fone1;
	private String fone2;
	private String celular;
	private String email;
	private String senha;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idCurriculo")
	private Curriculo curriculo;

	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<RelatorioMensal> relatorioMensais = new ArrayList<RelatorioMensal>();

	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<TermoCompromisso> termoDeCompromissos = new ArrayList<TermoCompromisso>();

	@OneToOne(mappedBy = "aluno")
	@Cascade(CascadeType.ALL)
	private ProjetoEstagio projetoEstagio;

	@OneToOne(mappedBy = "aluno")
	@Cascade(CascadeType.ALL)
	private ProjetoFinal projetoFinal;

	@OneToOne(mappedBy = "aluno")
	@Cascade(CascadeType.ALL)
	private RelatorioAcompanhamento relatorioAcompanhamento;

	public Aluno() {
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}

	public List<RelatorioMensal> getRelatorioMensais() {
		return relatorioMensais;
	}

	public void setRelatorioMensais(List<RelatorioMensal> relatorioMensais) {
		this.relatorioMensais = relatorioMensais;
	}

	public List<TermoCompromisso> getTermoDeCompromissos() {
		return termoDeCompromissos;
	}

	public void setTermoDeCompromissos(
			List<TermoCompromisso> termoDeCompromissos) {
		this.termoDeCompromissos = termoDeCompromissos;
	}

	@Override
	public String toString() {
		return this.getNome();
	}

	// Converter

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + idAluno.intValue();
		hash += (idAluno != null ? idAluno.hashCode() : 7);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Aluno)) {
			return false;
		}
		Aluno other = (Aluno) object;
		if ((this.idAluno == null && other.idAluno != null)
				|| (this.idAluno != null && !this.idAluno.equals(other.idAluno))) {
			return false;
		}
		return true;
	}

	public ProjetoEstagio getProjetoEstagio() {
		return projetoEstagio;
	}

	public void setProjetoEstagio(ProjetoEstagio projetoEstagio) {
		this.projetoEstagio = projetoEstagio;
	}

	public ProjetoFinal getProjetoFinal() {
		return projetoFinal;
	}

	public void setProjetoFinal(ProjetoFinal projetoFinal) {
		this.projetoFinal = projetoFinal;
	}

	public RelatorioAcompanhamento getRelatorioAcompanhamento() {
		return relatorioAcompanhamento;
	}

	public void setRelatorioAcompanhamento(
			RelatorioAcompanhamento relatorioAcompanhamento) {
		this.relatorioAcompanhamento = relatorioAcompanhamento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}