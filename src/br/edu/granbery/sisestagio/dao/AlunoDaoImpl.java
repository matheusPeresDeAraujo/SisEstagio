package br.edu.granbery.sisestagio.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.edu.granbery.sisestagio.model.Aluno;

public class AlunoDaoImpl extends GenericDaoImpl<Aluno, Long> implements
		AlunoDao {

	@Override
	public List<Aluno> recuperaPorNome(String nome) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT bm FROM " + clazz.getName() + " bm");
		sql.append(" WHERE bm.nome LIKE :nome");

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nome", "%" + nome + "%");

		return findByQueryAndParameters(sql.toString(), parameters);
	}

	@Override
	public Aluno findAlunoByCampoFilter(String nomeCampoFilter,
			String valorCampoFilter) {
		return (Aluno) session.createCriteria(Aluno.class)
				.add(Restrictions.eq(nomeCampoFilter, valorCampoFilter))
				.uniqueResult();
	}

}
