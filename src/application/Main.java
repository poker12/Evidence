package application;
	
import dao.hibernate.PersistenceManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/main/Main.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/gui/product/manageProductCategory/ManageProductCategory.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/gui/vatRate/manageVatRate/ManageVatRate.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/gui/expense/createExpense/CreateExpense.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					if(PersistenceManager.getInstance().isOpen())
						PersistenceManager.getInstance().close();
					Platform.exit();
					System.exit(0);
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
