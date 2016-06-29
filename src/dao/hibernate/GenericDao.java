package dao.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.LockModeType;

public interface GenericDao<T, ID extends Serializable> {
	void persist(T obj);
	void remove(T obj);
	T merge(T obj);
	T updateIfExists(T obj, ID id);
	T findById(ID id);
	List<T> getAll();
	void lock(T obj, LockModeType lockMode);
	LockModeType getLockMode(T obj);
	void refresh(T obj);
	void refresh(T obj, LockModeType lockMode);
}