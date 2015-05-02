package br.edu.granbery.sisestagio.dao;

import java.util.HashMap;
import java.util.List;

import br.edu.granbery.sisestagio.model.ProjetoEstagio;

public class ProjetoEstagioDaoImpl extends GenericDaoImpl<ProjetoEstagio, Long>
		implements ProjetoEstagioDao {

	@Override
	public List<ProjetoEstagio> recuperaPorNome(String nome) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT bm FROM " + clazz.getName() + " bm");
		sql.append(" WHERE bm.nome LIKE :nome");

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nome", "%" + nome + "%");

		return findByQueryAndParameters(sql.toString(), parameters);
	}

}
