package org.vgu.se.translationapp.model.logic;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.vgu.se.translationapp.model.entities.*;

public class TranslationNumber {
	
	private LinkedHashMap<String,String> dictionary = null;
	
	StoreAndLoadTranslation dbaccess = StoreAndLoadTranslation.getInstance();
	
	private User user = null;
	
	public TranslationNumber() {
		dictionary = new LinkedHashMap<String,String>();
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
		dictionary.put("eins", "one");
		dictionary.put("zwei", "two");
		dictionary.put("drei", "three");
		dictionary.put("vier", "four");
		dictionary.put("fuenf", "five");
		dictionary.put("sechs", "six");
		dictionary.put("seben", "one");
		dictionary.put("acht", "two");
		dictionary.put("neun", "three");
		dictionary.put("zehn", "four");
		dictionary.put("elf", "five");
		dictionary.put("zwoelf", "six");
		dictionary.put("dreizehn", "one");
		dictionary.put("vierzehn", "two");
		dictionary.put("fuenfzehn", "three");
		dictionary.put("sechzehn", "four");
		dictionary.put("siebzehn", "five");
		dictionary.put("achtzehn", "six");
		dictionary.put("neunzehn", "one");
		dictionary.put("zwanzig", "two");
		// ... and so on ;-)
		dictionary.put("weiss", "white");
		dictionary.put("Hund", "dog");
		dictionary.put("Himmel", "sky");
		dictionary.put("Katze", "cat");
		dictionary.put("Schnee", "snow");
		dictionary.put("zwei Fliegen mit einer Klappe schlagen", "to kill two birds with one stone");
		dictionary.put("Wie heisst du?", "What's your name?");
		dictionary.put("Wie alt bist du?", "How old are you?");
		dictionary.put("Ich komme aus Vietnam", "I come from Vietnam");
	}

	public String translate ( final String numberGER ) throws PersistenceExeception {
		// Perform the actual translation
		String translation = this.dictionary.get( numberGER );
		
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
