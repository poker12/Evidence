package dao.hibernate.entryOfGoods;

import java.util.List;

import javax.persistence.EntityManager;

import dao.hibernate.HibernateGenericDao;
import dao.hibernate.PersistenceManager;
import dto.entity.EntryOfGoods;

public class EntryOfGoodsDao extends HibernateGenericDao<EntryOfGoods, Long>{

	public EntryOfGoodsDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<EntryOfGoods> getAll() throws IllegalArgumentException {
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		List<EntryOfGoods> list = em.createQuery("SELECT e FROM dto.entity.EntryOfGoods e ORDER BY e.added DESC").getResultList();
		em.close();
		return list;
	}

	
}
