package gui.product.create;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.hibernate.productCategory.ProductCategoryLevelContainer;
import dto.entity.PriceHistory;
import dto.entity.Product;
import dto.entity.ProductCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.product.ProductService;
import service.productCategory.ProductCategoryService;

public class CreateProductController implements Initializable{
	@FXML
	private VBox createProduct;
	@FXML
	private TextField plu;
	@FXML
	private TextField name;
	@FXML
	private TextField priceWithVat;
	@FXML
	private ComboBox<String> vatRate;
	@FXML
	private TextArea description;
	@FXML
	private ComboBox<ProductCategoryLevelContainer> category;
	@FXML
	private TextField quantity;
	@FXML
	private CheckBox eshop;
	@FXML
	private CheckBox archived;
	@FXML
	private ListView<String> imagePathList; 
	@FXML
	private ListView<String> barcodeList;
	@FXML
	private ListView<ImageView> imageList;
	
	
	private ObservableList<ProductCategoryLevelContainer> prodCategoryList = FXCollections.observableArrayList();
	private ObservableList<String> vatRates = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ProductCategoryService pcs = new ProductCategoryService();
		prodCategoryList.addAll(pcs.getProductCategoryHierarchy());
		category.setItems(prodCategoryList);
		vatRate.setItems(vatRates);
	}
	
	public void addCategory(ActionEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/product/manageProductCategory/ManageProductCategory.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveProduct(ActionEvent event){
		if(plu.getText().isEmpty()){
			Product product;
			ProductCategory selectedCategory = null;
			selectedCategory = new ProductCategoryService().findById(category.getValue().productCategoryId);
			product = new Product(name.getText(), archived.isSelected(), description.getText(), eshop.isSelected(), 0l, selectedCategory);
			PriceHistory priceHistory = new PriceHistory(product, null, 
					new BigDecimal(priceWithVat.getText()), 
					new BigDecimal(vatRate.getEditor().getText()));
			List<PriceHistory> phList = new ArrayList<>();
			phList.add(priceHistory);
			product.setPriceHistory(phList);
			ProductService pdo = new ProductService();
			pdo.persist(product);
			plu.setText(product.getId().toString());
			quantity.setText(product.getQuantity().toString());
		}
		else{
			
		}
		
	}
	
	//public void edit(Product)
	
}