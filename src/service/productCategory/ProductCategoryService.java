package service.productCategory;

import java.util.List;

import dao.hibernate.productCategory.ProductCategoryDao;
import dao.hibernate.productCategory.ProductCategoryLevelContainer;
import dto.entity.ProductCategory;

public class ProductCategoryService {

	
	public List<ProductCategoryLevelContainer> getProductCategoryHierarchy(){
		ProductCategoryDao pcd = new ProductCategoryDao();
		return pcd.getFullProductCategoryHierarchy();
	}
	
	public ProductCategory findById(Long id){
		ProductCategoryDao pcd = new ProductCategoryDao();
		return pcd.findById(id);
	}
}
