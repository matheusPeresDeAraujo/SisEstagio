package br.edu.granbery.sisestagio.dao;

import java.util.HashMap;
import java.util.List;

import br.edu.granbery.sisestagio.model.Curriculo;

public class CurriculoDaoImpl extends GenericDaoImpl<Curriculo, Long> implements
		CurriculoDao {

	@Override
	public List<Curriculo> recuperaPorNome(String nome) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT bm FROM " + clazz.getName() + " bm");
		sql.append(" WHERE bm.nome LIKE :nome");

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nome", "%" + nome + "%");

		return findByQueryAndParameters(sql.toString(), parameters);
	}

	@Override
	public boolean numeroCurriculoDisponibilidade(Integer num) {

		boolean vReturn = false;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT bm FROM " + clazz.getName() + " bm");
		sql.append(" WHERE bm.numero = :numero");

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("numero", num);

		List<Curriculo> curriculos = findByQueryAndParameters(sql.toString(),
				parameters);

		if (curriculos.size() > 0) {
			vReturn = true;
		}

		return vReturn;
	}

}
