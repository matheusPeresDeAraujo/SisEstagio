package br.edu.granbery.sisestagio.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.edu.granbery.sisestagio.model.Usuario;

public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Long> implements
		UsuarioDao {

	@Override
	public List<Usuario> recuperaPorNome(String nome) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT bm FROM " + clazz.getName() + " bm");
		sql.append(" WHERE bm.nome LIKE :nome");

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nome", "%" + nome + "%");

		return findByQueryAndParameters(sql.toString(), parameters);
	}

	@Override
	public boolean emailDisponibilidade(String email) {

		boolean vReturn = false;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT bm FROM " + clazz.getName() + " bm");
		sql.append(" WHERE bm.email LIKE :email");

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", "%" + email + "%");

		List<Usuario> usuarios = findByQueryAndParameters(sql.toString(),
				parameters);

		if (usuarios.size() > 0) {
			vReturn = true;
		}

		return vReturn;
	}

	public Usuario login(String email, String senha) throws Exception {
		Criteria criteria = session.createCriteria(clazz);
		criteria.add(Restrictions.eq("email", email))
		  .add(Restrictions.eq("senha", senha));
		Usuario usuario = (Usuario) criteria.uniqueResult();

		return usuario;
	}

}
