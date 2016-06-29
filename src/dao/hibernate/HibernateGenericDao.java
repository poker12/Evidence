package dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;

public abstract class HibernateGenericDao<T, ID extends Serializable> implements GenericDao<T, ID>{

	private Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	protected HibernateGenericDao() {  
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
        		.getGenericSuperclass()).getActualTypeArguments()[0];  
	}  
	
	@Override
	public void persist(T obj) {
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void remove(T obj) {
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public T merge(T obj) {
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		T entity = em.merge(obj);
		em.getTransaction().commit();
		em.close();
		return entity;
	}
	
	@Override
	public T updateIfExists(T obj, ID id) throws EntityNotFoundException{
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		T entity = findById(id);
		if(entity != null)
			em.merge(obj);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() throws IllegalArgumentException{
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		List<T> list = em.createQuery("FROM " + persistentClass.getName()).getResultList();
		em.close();
		return list;
	}

	@Override
	public void lock(T obj, LockModeType lockMode) {
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.lock(obj, lockMode);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public LockModeType getLockMode(T obj) {
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		LockModeType lmt = em.getLockMode(obj);
		em.getTransaction().commit();
		em.close();
		return lmt;
	}

	@Override
	public void refresh(T obj) throws EntityNotFoundException{
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.refresh(obj);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void refresh(T obj, LockModeType lockMode) throws EntityNotFoundException{
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.refresh(obj, lockMode);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public T findById(ID id) throws IllegalArgumentException{
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		T entity = em.find(persistentClass, id);
		em.close();
		return entity;
	}
}