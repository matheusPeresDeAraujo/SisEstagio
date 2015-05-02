package br.edu.granbery.sisestagio.model;

import java.util.Date;


import javax.persistence.MappedSuperclass;

/**
 * 
 * @author knupp
 */
@MappedSuperclass
public abstract class Documento {

	private String protocolo;
	

	private Date dataEntrega;

	public Documento() {
		
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
}
