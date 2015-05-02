package br.edu.granbery.sisestagio.dao;

import java.util.HashMap;
import java.util.List;

import br.edu.granbery.sisestagio.model.TermoAditivo;

public class TermoAditivoDaoImpl extends GenericDaoImpl<TermoAditivo, Long>
		implements TermoAditivoDao {

	@Override
	public List<TermoAditivo> recuperaPorNome(String nome) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT bm FROM " + clazz.getName() + " bm");
		sql.append(" WHERE bm.nome LIKE :nome");

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nome", "%" + nome + "%");

		return findByQueryAndParameters(sql.toString(), parameters);
	}

}
