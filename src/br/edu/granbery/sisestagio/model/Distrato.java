package br.edu.granbery.sisestagio.model;

import java.util.Date;
import javax.persistence.*;

/**
 * 
 * @author knupp
 */
@Entity
@Table(name = "distrato")
public class Distrato extends Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDistrato")
	private Integer idDistrato;
	
	private Date dataTermino;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idTermoDeCompromisso")
	private TermoCompromisso termoDeCompromisso;

	public Distrato() {
	}

	public Integer getIdDistrato() {
		return idDistrato;
	}

	public void setIdDistrato(Integer idDistrato) {
		this.idDistrato = idDistrato;
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
