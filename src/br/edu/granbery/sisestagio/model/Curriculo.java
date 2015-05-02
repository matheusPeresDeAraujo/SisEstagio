package br.edu.granbery.sisestagio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 
 * @author knupp
 */
@Entity
@Table(name = "curriculo")
public class Curriculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCurriculo")
	private Integer idCurriculo;
	private Integer numero;
	private Double horasDeEstagio;

	@OneToMany(mappedBy = "curriculo", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Aluno> alunos = new ArrayList<Aluno>();

	public Curriculo() {
	}

	public Integer getIdCurriculo() {
		return idCurriculo;
	}

	public void setIdCurriculo(Integer idCurriculo) {
		this.idCurriculo = idCurriculo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Double getHorasDeEstagio() {
		return horasDeEstagio;
	}

	public void setHorasDeEstagio(Double horasDeEstagio) {
		this.horasDeEstagio = horasDeEstagio;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	// Converter

	@Override
	public String toString() {
		return this.getNumero().toString();
				//+ " - "	+ this.getHorasDeEstagio().toString() + " Horas";
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + idCurriculo.intValue();
		hash += (idCurriculo != null ? idCurriculo.hashCode() : 7);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Curriculo)) {
			return false;
		}
		Curriculo other = (Curriculo) object;
		if ((this.idCurriculo == null && other.idCurriculo != null)
				|| (this.idCurriculo != null && !this.idCurriculo
						.equals(other.idCurriculo))) {
			return false;
		}
		return true;
	}

}
