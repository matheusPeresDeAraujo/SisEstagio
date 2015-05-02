/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 
 * @author knupp
 */
@Entity
@Table(name = "empresa")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmpresa")
	private Integer idEmpresa;
	private String nome;
	private String contato;
	private String email;
	private String site;
	private String fone1;
	private String fone2;
	private String foneContato;
	private String rua;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private Long cnpj;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<TermoCompromisso> termoDeCompromissos = new ArrayList<TermoCompromisso>();

	public Empresa() {
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
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

	public String getFoneContato() {
		return foneContato;
	}

	public void setFoneContato(String foneContato) {
		this.foneContato = foneContato;
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

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public List<TermoCompromisso> getTermoDeCompromissos() {
		return termoDeCompromissos;
	}

	public void setTermoDeCompromissos(
			List<TermoCompromisso> termoDeCompromissos) {
		this.termoDeCompromissos = termoDeCompromissos;
	}

	// Converter

	@Override
	public String toString() {
		return this.getNome();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + idEmpresa.intValue();
		hash += (idEmpresa != null ? idEmpresa.hashCode() : 7);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Empresa)) {
			return false;
		}
		Empresa other = (Empresa) object;
		if ((this.idEmpresa == null && other.idEmpresa != null)
				|| (this.idEmpresa != null && !this.idEmpresa
						.equals(other.idEmpresa))) {
			return false;
		}
		return true;
	}

}
