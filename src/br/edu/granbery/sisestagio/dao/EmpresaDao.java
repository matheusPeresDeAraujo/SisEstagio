/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.Empresa;

/**
 * 
 * @author knupp
 */
public interface EmpresaDao extends GenericDao<Empresa, Long> {
	public List<Empresa> recuperaPorNome(String nome);
}
