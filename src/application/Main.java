package application;
	
import java.io.IOException;

import dao.hibernate.PersistenceManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import service.barcodeReader.BluetoothServer;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/main/Main.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/gui/vatRate/manageVatRate/ManageVatRate.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Evidence");
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
		
		Thread thread = new Thread(BluetoothServer.getInstance());
		thread.start();
		launch(args);
	}
	
}
