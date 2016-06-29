package dao.hibernate.databaseVendor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.hibernate.PersistenceManager;

public class MySqlFunctionsDao {

	public LocalDateTime getCurrentDateTime(){
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		Query q = em.createNativeQuery("SELECT now() FROM dual", Timestamp.class);
		Timestamp t = (Timestamp)q.getSingleResult();
		em.close();
		return t.toLocalDateTime();
	}
	
}
