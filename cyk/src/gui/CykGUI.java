package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Algoritmo;
import model.Gramatica;

public class CykGUI {
	
	private Algoritmo main;

    @FXML
    private TextField cadenatf;

    @FXML
    private TextArea gramativata;

    @FXML
    private Label resultado;

	
	@FXML
	private BorderPane mainPanel;

	public CykGUI(Algoritmo cyk) {

		main = cyk;
		
	}

	public void loadSettinWindow(Object object) throws IOException {
	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);
	}
	
    @FXML
    void evaluar(ActionEvent event) throws  IOException {

    	String cadena = cadenatf.getText();
    	String gramatica = gramativata.getText();
    	boolean flag = false;
    	

		Gramatica miGramatica = new Gramatica(gramatica);
        ArrayList<ArrayList<String>> miMatriz = new ArrayList<>();

        Algoritmo.inicializarMatriz(miMatriz, cadena);
        flag= Algoritmo.algoritmoCYK(miMatriz, cadena,miGramatica);
    	
    	if (flag== false) {
    		
    		resultado.setText("La gramatica no genera la cadena '" + cadena+ "'");
    	}
    	
    	else if (flag == true) {
    		
    		resultado.setText("La gramatica si genera la cadena '" + cadena+ "'");
    	}
    }
}
