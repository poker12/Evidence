package dao.hibernate.order;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.hibernate.HibernateGenericDao;
import dao.hibernate.PersistenceManager;
import dto.entity.Order;

public class OrderDao extends HibernateGenericDao<Order, Long>{

	public OrderDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCountOfInvoicesInYear(int year){
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		Query q = em.createQuery("SELECT COUNT(o) FROM dto.entity.Order o WHERE year(o.invoiceCreated) = :year");
		q.setParameter("year", year);
		Long l = (Long)q.getSingleResult();
		em.close();
		return l;
	}
	
	public List<Order> getAllInvoices(){
		List<Order> list = new ArrayList<>();
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		Query q = em.createQuery("SELECT o FROM dto.entity.Order o WHERE o.invoiceCreated IS NOT NULL ORDER BY o.invoiceCreated DESC", Order.class);
		List<Order> result = q.getResultList();
		if(result != null)
			list.addAll(result);
		em.close();
		return list;
	}
	
}
