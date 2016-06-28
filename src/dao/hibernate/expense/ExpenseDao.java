package dao.hibernate.expense;

import java.util.List;

import javax.persistence.EntityManager;

import dao.hibernate.HibernateGenericDao;
import dao.hibernate.PersistenceManager;
import dto.entity.Expense;

public class ExpenseDao extends HibernateGenericDao<Expense, Long>{

	public ExpenseDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Expense> getAll() throws IllegalArgumentException {
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		List<Expense> list = em.createQuery("SELECT e FROM dto.entity.Expense e ORDER BY e.created DESC").getResultList();
		em.close();
		return list;
	}

	
}
