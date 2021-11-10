package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Algoritmo;


public class Main extends Application {

	private CykGUI cykGUI;
	private static  Algoritmo cyk;

	public Main() {

		cyk= new Algoritmo();
		cykGUI = new CykGUI(cyk);

	}

	public static void main(String[] args) {

		launch(args);
		
		}

	

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPane.fxml"));

		loader.setController(cykGUI);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Main Window");
		cykGUI.loadSettinWindow(null);
		stage.show();
		
	}


}
