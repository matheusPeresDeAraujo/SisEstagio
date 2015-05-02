/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.TermoAditivo;

/**
 * 
 * @author knupp
 */
public interface TermoAditivoDao extends GenericDao<TermoAditivo, Long> {
	public List<TermoAditivo> recuperaPorNome(String nome);
}
