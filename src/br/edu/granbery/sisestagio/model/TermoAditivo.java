/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author knupp
 */
@Entity
@Table(name = "termoAditivo")
public class TermoAditivo extends Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTermoAditivo")
	private Integer idTermoAditivo;
	private Date dataInicio;
	private Date dataTermino;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idTermoDeCompromisso")
	private TermoCompromisso termoDeCompromisso;

	public TermoAditivo() {
	}

	public Integer getIdTermoAditivo() {
		return idTermoAditivo;
	}

	public void setIdTermoAditivo(Integer idTermoAditivo) {
		this.idTermoAditivo = idTermoAditivo;
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

	public TermoCompromisso getTermoDeCompromisso() {
		return termoDeCompromisso;
	}

	public void setTermoDeCompromisso(TermoCompromisso termoDeCompromisso) {
		this.termoDeCompromisso = termoDeCompromisso;
	}

}
