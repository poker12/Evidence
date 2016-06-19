package main;

import java.util.List;

import dao.hibernate.PersistenceManager;
import dao.hibernate.productCategory.ProductCategoryDao;
import dao.hibernate.productCategory.ProductCategoryLevelContainer;

public class RunProject {

	public static void main(String[] args) {
		System.out.println("Running...");
		//PersistenceManager.getInstance().createEntityManager();
	
		ProductCategoryDao pcd = new ProductCategoryDao();
		List<ProductCategoryLevelContainer> pclcList = pcd.getFullProductCategoryHierarchy();
		for(ProductCategoryLevelContainer p : pclcList){
			System.out.println(new String(new char[p.level]).replace("\0", "  ") + p.categoryName);
		}
		
		if(PersistenceManager.getInstance().isOpen())
			PersistenceManager.getInstance().close();
		System.out.println("End");
	}
}
