package org.vgu.se.translationapp.model.logic;

import java.util.HashMap;
import org.vgu.se.translationapp.model.entities.*;

public class TranslationNumber {

	private HashMap<String,String> number = null;

	StoreAndLoadTranslation dbaccess = StoreAndLoadTranslation.getInstance();

	private User user = null;

	public TranslationNumber() {
		number = new HashMap<String, String>();
		setUpDict();

		// A single user is created for testing
		user = new User();
		user.setId(0);
	}

	public void signIn(int newuser) {
		user = new User();
		user.setId(newuser);
	}

	private void setUpDict() {
		number.put("eins", "one");
		number.put("zwei", "two");
		number.put("drei", "three");
		number.put("vier", "four");
		number.put("fuenf", "five");
		number.put("sechs", "six");
		number.put("sieben", "seven");
		number.put("acht", "eight");
		number.put("neun", "nine");
		number.put("zehn", "ten");
		number.put("elf", "eleven");
		number.put("zwoelf", "twelve");
		number.put("dreizehn", "thirteen");
		number.put("vierzehn", "fourteen");
		number.put("fuenfzehn", "fifteen");
		number.put("sechzehn", "sixteen");
		number.put("siebzehn", "seventeen");
		number.put("achtzehn", "eighteen");
		number.put("neunzehn", "nineteen");
		number.put("zwanzig", "twenty");
	}

	public String translateNum ( final String numberGER ) throws PersistenceExeception {
		// Perform the actual translation
		String translation = this.number.get( numberGER );

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

	public HashMap<String, String> getNumMap() {
		return number;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
