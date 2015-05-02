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
@Table(name = "relatorioMensal")
public class RelatorioMensal extends Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRelatorioMensal")
	private Integer idRelatorioMensal;
	private Date dataInicioAtividade;
	private Date dataTerminoAtividade;
	private Double horasRegistradas;
	private Double horasPenalizadas;
	private Double horasAproveitadas;
	private String observacoes;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idAluno")
	private Aluno aluno;

	public RelatorioMensal() {
	}

	public Integer getIdRelatorioMensal() {
		return idRelatorioMensal;
	}

	public void setIdRelatorioMensal(Integer idRelatorioMensal) {
		this.idRelatorioMensal = idRelatorioMensal;
	}

	public Double getHorasRegistradas() {
		return horasRegistradas;
	}

	public void setHorasRegistradas(Double horasRegistradas) {
		this.horasRegistradas = horasRegistradas;
	}

	public Double getHorasPenalizadas() {
		return horasPenalizadas;
	}

	public void setHorasPenalizadas(Double horasPenalizadas) {
		this.horasPenalizadas = horasPenalizadas;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Double getHorasAproveitadas() {
		return horasAproveitadas;
	}

	public void setHorasAproveitadas(Double horasAproveitadas) {
		this.horasAproveitadas = horasAproveitadas;
	}

	public Date getDataInicioAtividade() {
		return dataInicioAtividade;
	}

	public void setDataInicioAtividade(Date dataInicioAtividade) {
		this.dataInicioAtividade = dataInicioAtividade;
	}

	public Date getDataTerminoAtividade() {
		return dataTerminoAtividade;
	}

	public void setDataTerminoAtividade(Date dataTerminoAtividade) {
		this.dataTerminoAtividade = dataTerminoAtividade;
	}

}
