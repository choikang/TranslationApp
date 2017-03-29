package org.vgu.se.translationapp.model.logic;

import java.util.HashMap;

import org.vgu.se.translationapp.model.entities.*;

public class TranslationNumber {
	
	private HashMap<String,String> numbers = null;
	
	StoreAndLoadTranslation dbaccess = StoreAndLoadTranslation.getInstance();
	
	private User user = null;
	
	public TranslationNumber() {
		numbers = new HashMap<String,String>();
		setUpNumbers();
		
		// A single user is created for testing 
//		user = new User();
//		user.setId(newuser);
	}
	
	public void signIn(int newuser) {
		user = new User();
		user.setId(newuser);
	}
	
	private void setUpNumbers() {
		numbers.put("eins", "one");
		numbers.put("zwei", "two");
		numbers.put("drei", "three");
		numbers.put("vier", "four");
		numbers.put("fuenf", "five");
		numbers.put("sechs", "six");
		// ... and so on ;-)
		
	}

	public String translate ( final String numberGER ) throws PersistenceExeception {
		// Perform the actual translation
		String translation = this.numbers.get( numberGER );
		
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
