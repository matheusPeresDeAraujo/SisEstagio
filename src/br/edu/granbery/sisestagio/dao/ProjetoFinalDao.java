/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.ProjetoFinal;

/**
 * 
 * @author knupp
 */
public interface ProjetoFinalDao extends GenericDao<ProjetoFinal, Long> {
	public List<ProjetoFinal> recuperaPorNome(String nome);
}
