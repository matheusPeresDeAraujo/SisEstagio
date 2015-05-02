/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author knupp
 */
@Entity
@Table(name = "relatorioDeAcompanhamento")
public class RelatorioAcompanhamento extends Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRelatorioDeAcompanhamento")
	private Integer idRelatorioDeAcompanhamento;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idAluno")
	private Aluno aluno;

	public RelatorioAcompanhamento() {
	}

	public Integer getIdRelatorioDeAcompanhamento() {
		return idRelatorioDeAcompanhamento;
	}

	public void setIdRelatorioDeAcompanhamento(
			Integer idRelatorioDeAcompanhamento) {
		this.idRelatorioDeAcompanhamento = idRelatorioDeAcompanhamento;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
