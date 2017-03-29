package org.vgu.se.translationapp.test;

import org.vgu.se.translationapp.model.logic.PersistenceExeception;
import org.vgu.se.translationapp.model.logic.TranslationNumber;

public class TestTranslationNumber { 
	
	public void run() throws PersistenceExeception {
		TranslationNumber trans = new TranslationNumber();
		String eins = trans.translateNum("eins");
		
		boolean statusTest = true;
		
		if (eins.equals("one")) {
			System.out.println("Input: eins, Expected Output = Actual Output: one! --> Test Case succesful!" );
		} else {
			System.out.println("Input: eins, Expected Output is one, but was: " + eins + " ! --> Test Case NOT succesful!" );
			statusTest = false;
		}
		
		String zwei = trans.translateNum("zwei");
		
		if (zwei.equals("two")) {
			System.out.println("Input: zwei, Expected Output = Actual Output: two! --> Test Case succesful!" );
		} else {
			System.out.println("Input: zwei, Expected Output is two, but was: " + zwei + " ! --> Test Case NOT succesful!" );
			statusTest = false;
		}
		
		String negTest = trans.translateNum("hahaha");
		
		if (negTest.equals("I don't understand")) {
			System.out.println("Illegial Input given, Expected Output = Actual Output: I don't understand! --> Test Case succesful!" );
		} else {
			System.out.println("Illegial Input given, Expected Output = I don't understand, but was: " + negTest + " ! --> Test Case NOT succesful!" );
			statusTest = false;
		}
		
		
		
		if (statusTest) {
			System.out.println("\nTest Suite was successful!");
		} else {
			System.out.println("\nTest Suite was NOT successful!" );
		}
		
	}
}
