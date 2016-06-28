package dao.hibernate.companyContact;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dao.hibernate.HibernateGenericDao;
import dao.hibernate.PersistenceManager;
import dto.entity.CompanyContact;

public class CompanyContactDao extends HibernateGenericDao<CompanyContact, Long>{

	public CompanyContactDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CompanyContact> getAllSuppliers(){
		List<CompanyContact> list = new ArrayList<>();
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		list.addAll(em.createQuery("SELECT c FROM dto.entity.CompanyContact c WHERE c.savedSupplier = 1 ORDER BY c.companyName ASC").getResultList());
		em.close();
		return list;
	}
	
}
