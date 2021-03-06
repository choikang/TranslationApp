package org.vgu.se.translationapp.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.vgu.se.translationapp.model.entities.PerformedTranslation;
import org.vgu.se.translationapp.model.logic.PersistenceExeception;
import org.vgu.se.translationapp.model.logic.StoreAndLoadTranslation;
import org.vgu.se.translationapp.model.logic.TranslationNumber;
import org.vgu.se.translationapp.model.logic.TranslationPhrase;
import org.vgu.se.translationapp.view.AdminView;
import org.vgu.se.translationapp.view.StatisticView;

import javafx.application.Application;
import javafx.application.Platform;


public class Main {
	public static void main(String[] args) throws PersistenceExeception {
		TranslationNumber tnObject = new TranslationNumber();
		TranslationPhrase tpObject = new TranslationPhrase();
		List<PerformedTranslation> storedList = new ArrayList<PerformedTranslation>();
		
		StoreAndLoadTranslation dbObject = StoreAndLoadTranslation.getInstance();
		dbObject.loadPerformedTranslations();
		
		int option = 0; 
		int userID = 0;
		boolean flag = false;
		String input = "", result = "";
		String in = "";
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-------------------TRANSLATION PROGRAM-------------------");
		System.out.print("Greetings! Please sign in with your User ID: ");
		userID = sc.nextInt();
		tnObject.signIn(userID);
		tpObject.signIn(userID);
		
		System.out.println("Welcome! Please find your desired options as following: " + "\n" +
				   		   "1. German - English Num Translator" + "\n" +
				   		   "2. German - English Phrase Translator" + "\n" +
				   		   "3. Request translation for not yet supported word/phrase" + "\n" +
				   		   "4. Print your translation history." + "\n" +
				   		   "5. Admin view: Translation history" + "\n" +
				   		   "6. Admin view: Searching statistics" + "\n" +
				   		   "7. Exit");
		while (option != 7) {
			while (flag == false) {
				System.out.print("My choice is: ");
				in = sc.next();
				try {
					option = Integer.parseInt(in);
					flag = true;
				} catch (Exception e) {
					System.out.println("Input format not supported. Please try again.");
				}
			}
			if (option == 1) {
				System.out.print("German word: ");
				input = sc.next();
				result = tnObject.translateNum(input);
				System.out.println("Translation Result: " + result);
			}
			else if (option == 2) {
				input = sc.nextLine();
				System.out.print("German term/phrase: ");
				input = sc.nextLine();
				result = tpObject.translateTerm(input);
				System.out.println("Translation Result: " + result);
			}
			else if (option == 3) {
				System.out.print("German word/phrase: ");
				System.out.println("Much appreciation for your contribution.");
			}
			else if (option == 4) {
				storedList = dbObject.getListOfCurrentTranslation();
				if (storedList.size() == 0) {
					System.out.println("No history yet recorded.");
				}
				else {
					System.out.println("UserID" + "\t" + "Input" + "\t" + "Translation Result");
					for (int i = 0; i < storedList.size(); i++) {
						System.out.println(storedList.get(i).getUserID() + "\t" +
										   storedList.get(i).getExpressionGER() + "\t" + storedList.get(i).getExpressionEN());
					}
					System.out.println("Translation history loaded successfully.");
				}
			}
			else if (option == 4) {
				
			}
			else if (option == 5) {
				//launch javafx stage in a different thread in order to allow current program keep running
				(new Thread() {
					public void run(){
						Application.launch(AdminView.class, args);
					}
				}).start();
			}
			else if (option == 6){
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							StatisticView.getInstance().display();
						} catch (ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
				
			}
			else if (option == 7){

				System.out.println("The application is closing in 3..2..1..");
			}
			else {
				System.out.println("Mismatch Command Input. Please try again");
			}
			flag = false;
		}
		
		sc.close();
	}
}
