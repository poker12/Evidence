package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Parent root = FXMLLoader.load(getClass().getResource("/gui/main/Main.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/gui/product/manageProductCategory/manageProductCategory.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/gui/vatRate/manageVatRate/manageVatRate.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
