/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.RelatorioAcompanhamento;



/**
 *
 * @author knupp
 */
public interface RelatorioAcompanhamentoDao extends GenericDao<RelatorioAcompanhamento, Long>{
	public List<RelatorioAcompanhamento> recuperaPorNome(String nome);
}
