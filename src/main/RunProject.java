package main;

import java.util.List;

import dao.hibernate.PersistenceManager;
import dao.hibernate.productCategory.ProductCategoryDao;
import dao.hibernate.productCategory.ProductCategoryLevelContainer;
import dto.entity.ProductCategory;

public class RunProject {

	public static void main(String[] args) {
		System.out.println("Running...");
	
		ProductCategoryDao pcd = new ProductCategoryDao();
		List<ProductCategoryLevelContainer> pclcList = pcd.getFullProductCategoryHierarchy();
		for(ProductCategoryLevelContainer p : pclcList){
			System.out.println(new String(new char[p.level]).replace("\0", "  ") + p.categoryName);
		}
		
		pcd.persist(new ProductCategory("Knihy", null));
		
		if(PersistenceManager.getInstance().isOpen())
			PersistenceManager.getInstance().close();
		System.out.println("End");
	}
}
