package gui.expense.manageExpense;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageExpenseController {
	@FXML
	private VBox paneProduct;
	@FXML
	private ToggleGroup expenseListSelectChoice;

	public void addExpense(ActionEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/expense/createExpense/CreateExpense.fxml"));
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
