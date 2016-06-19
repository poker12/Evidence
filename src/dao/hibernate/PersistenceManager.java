package dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

	private final EntityManagerFactory emf;

	private static PersistenceManager pman;
	
	public static PersistenceManager getInstance(){
		if(pman == null)
			pman = new PersistenceManager();
		return pman;
	}
	
	private PersistenceManager() {
		super();	
		emf = Persistence.createEntityManagerFactory("BussinessCompanyProject");
	}
	
	public EntityManager createEntityManager(){
		return emf.createEntityManager();
	}
	
	public void close(){
		//emf.close();
		pman = null;
	}
	
	public boolean isOpen(){
		return emf.isOpen();
	}
}