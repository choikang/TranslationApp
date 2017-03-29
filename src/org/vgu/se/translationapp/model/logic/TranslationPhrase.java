package org.vgu.se.translationapp.model.logic;

import java.util.HashMap;

import org.vgu.se.translationapp.model.entities.PerformedTranslation;
import org.vgu.se.translationapp.model.entities.User;

public class TranslationPhrase {
	private HashMap<String,String> phrase = null;
	
	StoreAndLoadTranslation dbaccess = StoreAndLoadTranslation.getInstance();
	
	private User user = null;
	
	public TranslationPhrase() {
		phrase = new HashMap<String, String>();
		setUpDict();
		
		// A single user is created for testing 
//		user = new User();
//		user.setId(newuser);
	}
	
	public void signIn(int newuser) {
		user = new User();
		user.setId(newuser);
	}
	
	private void setUpDict() {
		phrase.put("weiss", "white");
		phrase.put("Hund", "dog");
		phrase.put("Himmel", "sky");
		phrase.put("Katze", "cat");
		phrase.put("Schnee", "snow");
		phrase.put("zwei Fliegen mit einer Klappe schlagen", "to kill two birds with one stone");
		phrase.put("Wie heisst du?", "What's your name?");
		phrase.put("Wie alt bist du?", "How old are you?");
		phrase.put("Ich komme aus Vietnam", "I come from Vietnam");
	}
	
	public String translateTerm ( final String numberGER ) throws PersistenceExeception {
		// Perform the actual translation
		String translation = this.phrase.get( numberGER );
		
		if (translation == null) {
			PerformedTranslation performedTrans = new PerformedTranslation();
			performedTrans.setExpressionGER(numberGER);
			performedTrans.setExpressionEN("NO RESULT FOUND");
			performedTrans.setUserID( this.getUser().getId()  );
			
			dbaccess.addTranslation( performedTrans );
			dbaccess.storePerformedTranslations();
			
			return "I don't understand";
		}
		
		// Create the PerformedTranslation object
		PerformedTranslation performedTrans = new PerformedTranslation();
		performedTrans.setExpressionGER( numberGER );
		performedTrans.setExpressionEN( translation );
		
		// Set the user (it is assumed that the user logged in and his credentials
		// are available by a method getUser
		performedTrans.setUserID( this.getUser().getId()  );
		
		// Add the PerformedTranslation object to the internal list
		// (by getting the Singleton first!)
		dbaccess.addTranslation( performedTrans );
		dbaccess.storePerformedTranslations();
		
		return translation;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
