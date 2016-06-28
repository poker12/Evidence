package gui.product.manageProductCategory;

import java.net.URL;
import java.util.ResourceBundle;

import dao.hibernate.productCategory.ProductCategoryLevelContainer;
import dto.entity.ProductCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import service.productCategory.ProductCategoryService;

public class ManageProductCategoryController implements Initializable{
	@FXML
	private ListView<ProductCategoryLevelContainer> categoryTable;
	@FXML
	private TextField superCategorySelected;
	@FXML
	private TextField nameOfNewCategory;
	@FXML
	private TextField categorySelectedForEdit;
	@FXML
	private TextField categorySelectedForDelete;
	
	private ProductCategoryLevelContainer selectedCategory = null;
	private ObservableList<ProductCategoryLevelContainer> categoryList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		refreshProductCategoryList();
		categoryTable.setCellFactory(new Callback<ListView<ProductCategoryLevelContainer>, ListCell<ProductCategoryLevelContainer>>() {
			
			@Override
			public ListCell<ProductCategoryLevelContainer> call(ListView<ProductCategoryLevelContainer> param) {
				return new ListCell<ProductCategoryLevelContainer>(){

					@Override
					protected void updateItem(ProductCategoryLevelContainer arg0, boolean arg1) {
						super.updateItem(arg0, arg1);
						if(arg0 != null)
							setText(new String(new char[arg0.level]).replace("\0", "  ") + arg0.categoryName);
						else
							setText(null);
					}
					
				};
			}
		});

		categoryTable.setItems(categoryList);
	}

	public void refreshProductCategoryList(){
		ProductCategoryService productCategoryService = new ProductCategoryService();
		categoryList.clear();
		ProductCategoryLevelContainer none = new ProductCategoryLevelContainer();
		none.level = 0;
		none.categoryName = "HLAVNÍ KATEGORIE";
		none.productCategoryId = null;
		categoryList.add(none);
		categoryList.addAll(productCategoryService.getProductCategoryHierarchy());
	}
	
	public void createCategory(ActionEvent event){
		ProductCategoryService productCategoryService = new ProductCategoryService();
		ProductCategory foundCategory = null;
		if(selectedCategory != null)
			if(selectedCategory.productCategoryId != null)
				foundCategory = productCategoryService.findById(selectedCategory.productCategoryId);
		ProductCategory catToSave = new ProductCategory(nameOfNewCategory.getText(), foundCategory);
		productCategoryService.persist(catToSave);
		refreshProductCategoryList();
	}
	
	public void selectCategory(MouseEvent event){
		selectedCategory = categoryTable.getSelectionModel().getSelectedItem();
		superCategorySelected.setText(selectedCategory.categoryName);
		categorySelectedForEdit.setText(selectedCategory.categoryName);
		categorySelectedForDelete.setText(selectedCategory.categoryName);
	}
}
