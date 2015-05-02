package br.edu.granbery.sisestagio.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface GenericDao<T, ID extends Serializable> {

	public void merge(T entity) throws Exception;

	public void merge(T... entities) throws Exception;

	public void save(T entity) throws Exception;

	public void save(T... entities) throws Exception;

	public void update(T entity) throws Exception;

	public void update(T... entities) throws Exception;

	public void remove(T entity) throws Exception;

	public void remove(T... entities) throws Exception;

	public void removeById(ID id) throws Exception;

	public void removeByIds(ID... ids) throws Exception;

	public T find(ID id);

	public T find(Integer id);

	public T findField(String nomeCampoFilter, Object valorCampoFilter);

	public T findFields(String nomeCampoFilter, Object valorCampoFilter,
			String nomeCampoFilter1, Object valorCampoFilter1);

	public List<T> findAll(String ordem);
	public List<T> findAll(String nomeCampoFilter, Object valorCampoFilter);

	public void commit();

	public void rollback();

	public void beginTransaction();

	public void endTransaction();

	public void sessionClose();

	public void sessionClean();

	public boolean hasTransaction();

	public Session getSession();

	public List<T> findByQuery(StringBuilder sql);

	public List<T> findByQueryAndParameters(String string,
			Map<String, Object> parameters);

}
