package org.vgu.se.translationapp.model.logic;

import java.util.HashMap;

import org.vgu.se.translationapp.model.entities.*;

public class TranslationNumber {
	
	private HashMap<String,String> numbers = null;
	
	private User user = null;
	
	public TranslationNumber() {
		numbers = new HashMap<String,String>();
		setUpNumbers();
		
		// A single user is created for testing 
		user = new User();
		user.setId(1);
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

	public String translate ( final String numberGER ) {
		// Perform the actual translation
		String translation = this.numbers.get( numberGER );
		
		if (translation == null) return "I don't understand";
		
		// Create the PerformedTranslation object
		PerformedTranslation performedTrans = new PerformedTranslation();
		performedTrans.setExpressionGER( numberGER );
		performedTrans.setExpressionEN( translation );
		
		// Set the user (it is assumed that the user logged in and his credentials
		// are available by a method getUser
		performedTrans.setUserID( this.getUser().getId()  );
		
		// Add the PerformedTranslation object to the internal list
		// (by getting the Singleton first!)
		StoreAndLoadTranslation.getInstance().addTranslation( performedTrans );
		
		return translation;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
