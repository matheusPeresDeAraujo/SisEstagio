/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.RelatorioMensal;

/**
 * 
 * @author knupp
 */
public interface RelatorioMensalDao extends GenericDao<RelatorioMensal, Long> {
	public List<RelatorioMensal> recuperaPorNome(String nome);
}
