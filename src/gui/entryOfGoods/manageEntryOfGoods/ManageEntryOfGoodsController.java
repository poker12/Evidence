package gui.entryOfGoods.manageEntryOfGoods;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import dto.entity.EntryOfGoods;
import dto.entity.Product;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import service.entryOfGoods.EntryOfGoodsService;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class ManageEntryOfGoodsController implements Initializable{
	@FXML
	private VBox paneProduct;
	@FXML
	private TableView<EntryOfGoods> entryOfGoodsTable;
	@FXML
	private TableColumn<EntryOfGoods, LocalDateTime> added;
	@FXML
	private TableColumn<EntryOfGoods, Long> plu;
	@FXML
	private TableColumn<EntryOfGoods, String> productName;
	@FXML
	private TableColumn<EntryOfGoods, Long> quantity;
	@FXML
	private TableColumn<EntryOfGoods, BigDecimal> pricePerPiece;
	@FXML
	private TableColumn<EntryOfGoods, Boolean> withVat;
	@FXML
	private TableColumn<EntryOfGoods, BigDecimal> vatRate;
	
	private ObservableList<EntryOfGoods> entryOfGoodsList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		EntryOfGoodsService entryOfGoodsService = new EntryOfGoodsService();
		entryOfGoodsList.addAll(entryOfGoodsService.getAll());
		
		added.setCellValueFactory(new PropertyValueFactory<EntryOfGoods, LocalDateTime>("added"));
		plu.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Long>(column.getValue().getId()));
		productName.setCellValueFactory(column -> new ReadOnlyStringWrapper(column.getValue().getProduct().getName()));
		quantity.setCellValueFactory(new PropertyValueFactory<EntryOfGoods, Long>("productQuantity"));
		pricePerPiece.setCellValueFactory(new PropertyValueFactory<EntryOfGoods, BigDecimal>("pricePerPiece"));
		withVat.setCellValueFactory(new PropertyValueFactory<EntryOfGoods, Boolean>("withVat"));
		vatRate.setCellValueFactory(new PropertyValueFactory<EntryOfGoods, BigDecimal>("vatRate"));
		
		entryOfGoodsTable.setItems(entryOfGoodsList);
	}

	
	
}
