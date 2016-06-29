package dao.hibernate.productCategory;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import dao.hibernate.HibernateGenericDao;
import dao.hibernate.PersistenceManager;
import dto.entity.ProductCategory;

public class ProductCategoryDao extends HibernateGenericDao<ProductCategory, Long>{

	public ProductCategoryDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ProductCategoryLevelContainer> getFullProductCategoryHierarchy(){
		List<ProductCategoryLevelContainer> list = new ArrayList<>();
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		StoredProcedureQuery sp = null;
		sp = em.createStoredProcedureQuery("returnProductCategoryHierarchy");
		sp.registerStoredProcedureParameter("enter_supercategory_id", Integer.class, ParameterMode.IN);
		sp.registerStoredProcedureParameter("enter_lvl", Integer.class, ParameterMode.IN);
		sp.registerStoredProcedureParameter("level_list", String.class, ParameterMode.INOUT);
		sp.registerStoredProcedureParameter("id_list", String.class, ParameterMode.INOUT);
		sp.registerStoredProcedureParameter("name_list", String.class, ParameterMode.INOUT);
		sp.setParameter("enter_supercategory_id", 0);
		sp.setParameter("enter_lvl", -1);
		sp.setParameter("level_list", "");
		sp.setParameter("id_list", "");
		sp.setParameter("name_list", "");
		String levelList = null;
		String idList = null;
		String nameList = null;
		levelList = (String) sp.getOutputParameterValue("level_list");
		idList = (String) sp.getOutputParameterValue("id_list");
		nameList = (String) sp.getOutputParameterValue("name_list");
		if(levelList == null || levelList.isEmpty() == true){
			em.getTransaction().commit();
			em.close();
			return list;
		}
		if(idList == null || idList.isEmpty() == true){
			em.getTransaction().commit();
			em.close();
			return list;
		}
		if(nameList == null || nameList.isEmpty() == true){
			em.getTransaction().commit();
			em.close();
			return list;
		}
		
		String[] levelParsed = levelList.split(";");
		String[] idParsed = idList.split(";");
		String[] nameParsed = nameList.split(";");
		
		for(int i = 0; i < levelParsed.length; i++){
			ProductCategoryLevelContainer pclc = new ProductCategoryLevelContainer();
			pclc.level = Integer.parseInt(levelParsed[i]);
			pclc.productCategoryId = Long.parseLong(idParsed[i]);
			pclc.categoryName = nameParsed[i];
			list.add(pclc);
		}
		
		em.getTransaction().commit();
		em.close();
		return list;
	}
}
