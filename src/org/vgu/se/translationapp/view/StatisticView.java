package org.vgu.se.translationapp.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.vgu.se.translationapp.model.entities.PerformedTranslation;
import org.vgu.se.translationapp.model.logic.StoreAndLoadTranslation;
import org.vgu.se.translationapp.model.logic.TranslationNumber;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hoàng Bryan Nguyễn on 29/03/2017.
 */
public class StatisticView extends Application implements ViewObserver {
    private static StatisticView statView = null;

    public static StatisticView getInstance() {
        if ( statView == null ) {
            statView = new StatisticView();
        }
        return statView;
    }

    TranslationNumber tnObject = new TranslationNumber();

    ListView<String> listView = new ListView<>();

    private static Stage primaryStage;

    int totalNum, totalPhrase, totalException, total;
    double perNum, perPhrase, perExeption;

    @Override
    public void update() {
        totalNum = 0;
        totalPhrase = 0;
        totalException =0;
//		List<PerformedTranslation> performedTransList = null;
        try {
            List<PerformedTranslation> performedTransList = StoreAndLoadTranslation.getInstance().loadTrans();
//          ObservableList<String> oPerformedTrans = FXCollections.observableArrayList();

            for (PerformedTranslation performed : performedTransList ) {
                if(performed.getExpressionEN().equals("NO RESULT FOUND")){
                    totalException++;
                }

                else if(tnObject.getNumMap().get(performed.getExpressionGER()) != null){
                    totalNum++;
                }
                else{
                    totalPhrase++;
                }
            }
            total = totalException + totalNum + totalPhrase;
            perNum =  100.0 * (double) totalNum / (double) total;
            perPhrase = 100.0 * (double) totalPhrase/ (double) total;
            perExeption = 100.0 * (double) totalException / (double) total;
//            listView.setItems(oPerformedTrans);

            Button closeBtn = new Button("Close");
            closeBtn.setOnAction(e -> {
                StoreAndLoadTranslation.getInstance().unregister(AdminView.getInstance());
                primaryStage.close();
            });

            //Set layout for the stage
            VBox layout = new VBox();
            Label num = new Label("Performed translation of numbers (1-20): " + Math.round(perNum) + "%");
            Label phrase = new Label("Performed translation of phrases and terms: " + Math.round(perPhrase) + "%");
            Label illegal = new Label("Illegal translations (no translation given): " + Math.round(perExeption)+ "%");
            layout.getChildren().addAll(num, phrase, illegal, closeBtn);

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

        StatisticView.primaryStage = primaryStage;
        StatisticView.primaryStage.setTitle("Distribution of performed translations:");

        //subscribe this view to the Database
        StoreAndLoadTranslation.getInstance().register(StatisticView.getInstance());

        update();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
