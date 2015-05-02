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
@Table(name = "projetoEstagio")
public class ProjetoEstagio extends Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProjetoDeEstagio")
	private Integer idProjetoDeEstagio;

	private String descricao;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idAluno")
	private Aluno aluno;

	public ProjetoEstagio() {
	}

	public Integer getIdProjetoDeEstagio() {
		return idProjetoDeEstagio;
	}

	public void setIdProjetoDeEstagio(Integer idProjetoDeEstagio) {
		this.idProjetoDeEstagio = idProjetoDeEstagio;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
