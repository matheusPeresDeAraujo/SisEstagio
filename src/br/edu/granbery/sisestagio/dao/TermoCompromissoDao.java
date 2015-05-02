/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.TermoCompromisso;

/**
 * 
 * @author knupp
 */
public interface TermoCompromissoDao extends GenericDao<TermoCompromisso, Long> {
	public List<TermoCompromisso> recuperaPorNome(String nome);
}
