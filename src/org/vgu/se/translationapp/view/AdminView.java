package org.vgu.se.translationapp.view;

import java.io.IOException;
import java.util.List;

import org.vgu.se.translationapp.model.entities.PerformedTranslation;
import org.vgu.se.translationapp.model.logic.StoreAndLoadTranslation;

import javafx.application.*;
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
	
	private static Stage primaryStage;
	
	ListView<String> listView = new ListView<>();
	
	
	
	@Override
	public void update() {
		try {
			//Create a list of string from a list of translation object to print that on screen
			
			List<PerformedTranslation> performedTransList = StoreAndLoadTranslation.getInstance().loadTrans();
			ObservableList<String> oPerformedTrans = FXCollections.observableArrayList();
			
			if (!performedTransList.isEmpty())
				for (PerformedTranslation performed : performedTransList ) {
					String tmp = performed.getExpressionGER() + "\t\t-->>\t\t" + performed.getExpressionEN();
					oPerformedTrans.add(tmp);
				}
			listView.setItems(oPerformedTrans);
			
			//Create "close" button to close and unsubscribe the stage
			Button closeBtn = new Button("Close");
			closeBtn.setOnAction(e -> {
				StoreAndLoadTranslation.getInstance().unregister(AdminView.getInstance());
				primaryStage.close();
			});
			
			//Set layout for the stage
			VBox layout = new VBox();			
			layout.getChildren().addAll(closeBtn, listView);
			
			Scene scene = new Scene(layout, 600, 300);
			
			
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					primaryStage.setScene(scene);
					primaryStage.show();	
				}
			});
			
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws ClassNotFoundException, IOException {
		
		AdminView.primaryStage = primaryStage;
		AdminView.primaryStage.setTitle("Performed Translation");
		
		//subscribe this view to the Database
		StoreAndLoadTranslation.getInstance().register(AdminView.getInstance());
			
		//print the result
		update();			
	}

	public static void main(String[] args) {	
		launch(args);
	}
}
