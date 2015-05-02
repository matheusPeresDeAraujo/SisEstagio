package br.edu.granbery.sisestagio.dao;

import java.util.HashMap;
import java.util.List;

import br.edu.granbery.sisestagio.model.RelatorioMensal;

public class RelatorioMensalDaoImpl extends
		GenericDaoImpl<RelatorioMensal, Long> implements RelatorioMensalDao {

	@Override
	public List<RelatorioMensal> recuperaPorNome(String nome) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT bm FROM " + clazz.getName() + " bm");
		sql.append(" WHERE bm.nome LIKE :nome");

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nome", "%" + nome + "%");

		return findByQueryAndParameters(sql.toString(), parameters);
	}

}
