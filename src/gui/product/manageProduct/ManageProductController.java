package gui.product.manageProduct;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import dto.entity.Barcode;
import dto.entity.PriceHistory;
import dto.entity.Product;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.product.ProductService;

public class ManageProductController implements Initializable{
	@FXML
	private VBox paneProduct;

	@FXML
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product, Long> plu;
	@FXML
	private TableColumn<Product, String> name;
	@FXML
	private TableColumn<Product, String> category;
	@FXML
	private TableColumn<Product, Long> quantity;
	@FXML
	private TableColumn<Product, BigDecimal> price;
	@FXML
	private TableColumn<Product, Boolean> withVat;
	@FXML
	private TableColumn<Product, BigDecimal> vatRate;
	@FXML
	private TableColumn<Product, String> barcodes;
	@FXML
	private TableColumn<Product, Boolean> eshop;
	@FXML
	private TableColumn<Product, Boolean> archived;
	@FXML
	private TableColumn<Product, String> description;
	
	private ObservableList<Product> products = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ProductService ps = new ProductService();
		products.addAll(ps.getAll());
		plu.setCellValueFactory(new PropertyValueFactory<Product, Long>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		category.setCellValueFactory(column -> new ReadOnlyStringWrapper(column.getValue().getCategory().getName()));		
		quantity.setCellValueFactory(new PropertyValueFactory<Product, Long>("quantity"));
		price.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product,BigDecimal>, ObservableValue<BigDecimal>>() {

			@Override
			public ObservableValue<BigDecimal> call(CellDataFeatures<Product, BigDecimal> param) {
				List<PriceHistory> phList = param.getValue().getPriceHistory();
				phList.sort(new Comparator<PriceHistory>() {

					@Override
					public int compare(PriceHistory o1, PriceHistory o2) {
						if(o1.getStartDateTime().isEqual(o2.getStartDateTime()))
								return 0;
						else if(o1.getStartDateTime().isAfter(o2.getStartDateTime()))
							return -1;
						else
							return 1;
					}
				});
				PriceHistory ph = phList.get(0);
				if(ph == null)
					return null;
				return new SimpleObjectProperty<BigDecimal>(ph.getSellingPrice());
			}
		});
		
		withVat.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product,Boolean>, ObservableValue<Boolean>>() {

			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<Product, Boolean> param) {
				List<PriceHistory> phList = param.getValue().getPriceHistory();
				phList.sort(new Comparator<PriceHistory>() {

					@Override
					public int compare(PriceHistory o1, PriceHistory o2) {
						if(o1.getStartDateTime().isEqual(o2.getStartDateTime()))
								return 0;
						else if(o1.getStartDateTime().isAfter(o2.getStartDateTime()))
							return -1;
						else
							return 1;
					}
				});
				PriceHistory ph = phList.get(0);
				if(ph == null)
					return null;
				return new SimpleObjectProperty<Boolean>(ph.getWithVat());
			}
		});
		
		vatRate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product,BigDecimal>, ObservableValue<BigDecimal>>() {

			@Override
			public ObservableValue<BigDecimal> call(CellDataFeatures<Product, BigDecimal> param) {
				List<PriceHistory> phList = param.getValue().getPriceHistory();
				phList.sort(new Comparator<PriceHistory>() {

					@Override
					public int compare(PriceHistory o1, PriceHistory o2) {
						if(o1.getStartDateTime().isEqual(o2.getStartDateTime()))
								return 0;
						else if(o1.getStartDateTime().isAfter(o2.getStartDateTime()))
							return -1;
						else
							return 1;
					}
				});
				PriceHistory ph = phList.get(0);
				if(ph == null)
					return null;
				return new SimpleObjectProperty<BigDecimal>(ph.getVatRate());
			}
		});
				
		barcodes.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Product, String> param) {
				List<Barcode> barcodeList = param.getValue().getBarcodes();
				StringJoiner list = new StringJoiner(", ");
				for(Barcode item : barcodeList){
					list.add(item.getBarcode());
				}
				return new ReadOnlyStringWrapper(list.toString());
			}
		});
		
		eshop.setCellValueFactory(new PropertyValueFactory<Product, Boolean>("visibilityInEshop"));
		archived.setCellValueFactory(new PropertyValueFactory<Product, Boolean>("archived"));
		description.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
		
		productTable.setItems(products);
	}
	
	public void addProduct(ActionEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/product/create/CreateProduct.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initOwner(this.paneProduct.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
