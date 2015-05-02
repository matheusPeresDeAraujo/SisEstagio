/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.Curriculo;


/**
 *
 * @author knupp
 */
public interface CurriculoDao extends GenericDao<Curriculo, Long>{
	public List<Curriculo> recuperaPorNome(String nome);
	public boolean numeroCurriculoDisponibilidade(Integer num);
}
