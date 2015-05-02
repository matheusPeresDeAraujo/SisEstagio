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
@Table(name = "projetoFinal")
public class ProjetoFinal extends Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProjetoFinal")
	private Integer idProjetoFinal;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idAluno")
	private Aluno aluno;

	public ProjetoFinal() {
	}

	public Integer getIdProjetoFinal() {
		return idProjetoFinal;
	}

	public void setIdProjetoFinal(Integer idProjetoFinal) {
		this.idProjetoFinal = idProjetoFinal;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
