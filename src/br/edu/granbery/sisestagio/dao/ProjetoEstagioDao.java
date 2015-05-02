/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.ProjetoEstagio;

/**
 * 
 * @author knupp
 */
public interface ProjetoEstagioDao extends GenericDao<ProjetoEstagio, Long> {
	public List<ProjetoEstagio> recuperaPorNome(String nome);
}
