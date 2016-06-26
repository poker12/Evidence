package dao.hibernate.productCategory;

public class ProductCategoryLevelContainer {

	public Integer level = null;
	public Long productCategoryId = null;
	public String categoryName = null;
	
	@Override
	public String toString() {
		return new String(new char[level]).replace("\0", "  ") + categoryName;
	}
	
	
	
}
