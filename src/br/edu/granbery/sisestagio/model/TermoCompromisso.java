/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.model;

import java.util.ArrayList;
import java.util.Date;
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

/**
 * 
 * @author knupp
 */
@Entity
@Table(name = "termoDeCompromisso")
public class TermoCompromisso extends Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTermoDeCompromisso")
	private Integer idTermoDeCompromisso;
	private Date dataInicio;
	private Date dataTermino;
	private Boolean declaracaoDeTrabalho;
	private Boolean trouxeCtps;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idAluno")
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;

	@OneToMany(mappedBy = "termoDeCompromisso", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<TermoAditivo> termoAditivos = new ArrayList<TermoAditivo>();

	@OneToOne(mappedBy = "termoDeCompromisso")
	@Cascade(CascadeType.ALL)
	private Distrato distrato;

	public TermoCompromisso() {
	}

	public Integer getIdTermoDeCompromisso() {
		return idTermoDeCompromisso;
	}

	public void setIdTermoDeCompromisso(Integer idTermoDeCompromisso) {
		this.idTermoDeCompromisso = idTermoDeCompromisso;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Boolean getDeclaracaoDeTrabalho() {
		return declaracaoDeTrabalho;
	}

	public void setDeclaracaoDeTrabalho(Boolean declaracaoDeTrabalho) {
		this.declaracaoDeTrabalho = declaracaoDeTrabalho;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<TermoAditivo> getTermoAditivos() {
		return termoAditivos;
	}

	public void setTermoAditivos(List<TermoAditivo> termoAditivos) {
		this.termoAditivos = termoAditivos;
	}

	// Converter

	@Override
	public String toString() {
		return this.getProtocolo().toString() + " - " + this.getDataEntrega();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + idTermoDeCompromisso.intValue();
		hash += (idTermoDeCompromisso != null ? idTermoDeCompromisso.hashCode()
				: 7);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof TermoCompromisso)) {
			return false;
		}
		TermoCompromisso other = (TermoCompromisso) object;
		if ((this.idTermoDeCompromisso == null && other.idTermoDeCompromisso != null)
				|| (this.idTermoDeCompromisso != null && !this.idTermoDeCompromisso
						.equals(other.idTermoDeCompromisso))) {
			return false;
		}
		return true;
	}

	public Boolean getTrouxeCtps() {
		return trouxeCtps;
	}

	public void setTrouxeCtps(Boolean trouxeCtps) {
		this.trouxeCtps = trouxeCtps;
	}

	public Distrato getDistrato() {
		return distrato;
	}

	public void setDistrato(Distrato distrato) {
		this.distrato = distrato;
	}

}