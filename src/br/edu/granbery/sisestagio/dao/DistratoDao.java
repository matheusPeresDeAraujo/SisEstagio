/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.Distrato;

/**
 * 
 * @author knupp
 */
public interface DistratoDao extends GenericDao<Distrato, Long> {
	public List<Distrato> recuperaPorNome(String nome);
}
