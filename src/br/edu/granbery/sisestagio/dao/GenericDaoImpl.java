package br.edu.granbery.sisestagio.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.granbery.sisestagio.util.HibernateUtil;

public abstract class GenericDaoImpl<T, ID extends Serializable> implements
		GenericDao<T, ID> {

	protected Session session;
	private Transaction transaction;
	protected Class<?> clazz;

	public GenericDaoImpl() {
		getSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(ID id) {
		return (T) this.session.createCriteria(clazz)
				.add(Restrictions.eq("id", id)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(Integer id) {
		return (T) this.session.createCriteria(clazz)
				.add(Restrictions.eq("id", id)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findField(String nomeCampoFilter, Object valorCampoFilter) {
		return (T) session.createCriteria(clazz)
				.add(Restrictions.eq(nomeCampoFilter, valorCampoFilter))
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findFields(String nomeCampoFilter, Object valorCampoFilter,
			String nomeCampoFilter1, Object valorCampoFilter1) {

		return (T) session.createCriteria(clazz)
				.add(Restrictions.eq(nomeCampoFilter, valorCampoFilter))
				.add(Restrictions.eq(nomeCampoFilter1, valorCampoFilter1))
				.uniqueResult();

	}

	@Override
	public void merge(T entity) throws Exception {
		try {
			this.session.merge(entity);
			commit();
		} catch (Exception e) {
			rollback();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void merge(T... entities) throws Exception {
		try {
			for (T entity : entities) {
				this.session.saveOrUpdate(entity);
			}
			commit();
		} catch (Exception e) {
			rollback();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void save(T entity) throws Exception {
		try {
			this.session.save(entity);
			commit();
		} catch (Exception e) {
			rollback();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void save(T... entities) throws Exception {
		try {
			for (T entity : entities) {
				this.session.save(entity);
			}
			commit();
		} catch (Exception e) {
			rollback();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void update(T entity) throws Exception {
		try {
			this.session.update(entity);
			commit();
		} catch (Exception e) {
			rollback();
		}
	}

	@Override
	public void update(T... entities) throws Exception {
		try {
			for (T entity : entities) {
				this.session.update(entity);
			}
			commit();
		} catch (Exception e) {
			rollback();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void remove(T entity) throws Exception {
		try {
			this.session.delete(entity);
			commit();
		} catch (Exception e) {
			rollback();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void remove(T... entities) throws Exception {
		try {
			for (T entity : entities) {
				this.session.delete(entity);
			}
			commit();
		} catch (Exception e) {
			rollback();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void removeById(ID id) throws Exception {
		try {
			T entity = find(id);
			this.session.delete(entity);
			commit();
		} catch (Exception e) {
			rollback();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void removeByIds(ID... ids) throws Exception {
		try {
			for (ID id : ids) {
				removeById(id);
			}
			commit();
		} catch (Exception e) {
			rollback();
			throw new Exception(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String ordem) {
		return session.createCriteria(clazz).addOrder(Order.asc(ordem)).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String nomeCampoFilter, Object valorCampoFilter) {
		return session.createCriteria(clazz).add(Restrictions.eq(nomeCampoFilter, valorCampoFilter)).list();
	}

	@Override
	public void commit() {
		if (!transaction.isActive()) {
			beginTransaction();
		}

		this.transaction.commit();
	}

	@Override
	public void rollback() {
		this.transaction.rollback();
	}

	@Override
	public void beginTransaction() {
		if (hasTransaction()) {
			endTransaction();
		}
		this.transaction = this.session.beginTransaction();
	}

	@Override
	public void endTransaction() {
		this.transaction = null;
	}

	@Override
	public void sessionClose() {
		this.session.close();
	}

	@Override
	public void sessionClean() {
		this.session.clear();
	}

	@Override
	public boolean hasTransaction() {
		return this.transaction != null;
	}

	@Override
	public Session getSession() {
		session = HibernateUtil.getSession();
		this.clazz = getGenericClass();
		beginTransaction();
		return session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByQuery(StringBuilder sql) {
		Query query = session.createQuery(sql.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByQueryAndParameters(String string,
			Map<String, Object> parameters) {
		Query query = session.createQuery(string.toString());

		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.list();
	}

	@SuppressWarnings("unchecked")
	private Class<?> getGenericClass() {
		ParameterizedType superclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		return (Class<T>) (superclass.getActualTypeArguments()[0]);
	}

}