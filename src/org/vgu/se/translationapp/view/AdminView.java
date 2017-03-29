package org.vgu.se.translationapp.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.vgu.se.translationapp.model.entities.PerformedTranslation;
import org.vgu.se.translationapp.model.logic.StoreAndLoadTranslation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AdminView extends Application implements ViewObserver{
	
	private static AdminView adminView = null;
	
	public static AdminView getInstance() {
		if ( adminView == null ) {
			adminView = new AdminView();
		}
		return adminView;
	}
	
	ListView<String> listView = new ListView<>();
	
	
	@Override
	public void update() {
		
//		List<PerformedTranslation> performedTransList = null;
		try {
			List<PerformedTranslation> performedTransList = StoreAndLoadTranslation.getInstance().loadTrans();
			ObservableList<String> oPerformedTrans = FXCollections.observableArrayList();
			
			for (PerformedTranslation performed : performedTransList ) {
				String tmp = "German: " + performed.getExpressionGER() + "\tEnglish: " + performed.getExpressionEN();
				oPerformedTrans.add(tmp);
			}
			listView.setItems(oPerformedTrans);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void start(Stage primaryStage) throws ClassNotFoundException, IOException {
			
		Label title = new Label("Performed Translation list:");
		Button closeBtn = new Button("Close");
		closeBtn.setOnAction(e -> {
			StoreAndLoadTranslation.getInstance().unregister(AdminView.getInstance());
			primaryStage.close();
		});
		
		update();		
		
		//set up scene
		primaryStage.setTitle("Performed Translation");
		
		VBox layout = new VBox();
		layout.getChildren().addAll(title, listView, closeBtn);
		
		Scene scene = new Scene(layout, 400, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		StoreAndLoadTranslation.getInstance().register(AdminView.getInstance());
		launch(args);
	}

	
}
