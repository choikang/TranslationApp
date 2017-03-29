package org.vgu.se.translationapp.test;

import java.util.List;

import org.vgu.se.translationapp.model.entities.*;
import org.vgu.se.translationapp.model.logic.PersistenceExeception;
import org.vgu.se.translationapp.model.logic.StoreAndLoadTranslation;

public class TestPersistence {

	public void run() {

		// Load Data, so that new Objects will be added correctly, without deleting old ones
		try {
			StoreAndLoadTranslation.getInstance().loadPerformedTranslations();
		} catch (PersistenceExeception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Grab current number of objects:
		int sizeBefore = StoreAndLoadTranslation.getInstance().size();

		PerformedTranslation pt1 = new PerformedTranslation();
		pt1.setExpressionGER("eins");
		pt1.setExpressionEN("one");

		PerformedTranslation pt2 = new PerformedTranslation();
		pt2.setExpressionGER("zwei");
		pt2.setExpressionEN("two");

		// Add Translation Objects
		StoreAndLoadTranslation.getInstance().addTranslation(pt1);
		StoreAndLoadTranslation.getInstance().addTranslation(pt2);

		// Store Data persistently on disk
		try {
			StoreAndLoadTranslation.getInstance().storePerformedTranslations();
		} catch (PersistenceExeception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Re-Load Data
		try {
			StoreAndLoadTranslation.getInstance().loadPerformedTranslations();
		} catch (PersistenceExeception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get the new size:
		int sizeAfter = StoreAndLoadTranslation.getInstance().size();

		// Get current List
		List<PerformedTranslation> liste = StoreAndLoadTranslation.getInstance().getListOfCurrentTranslation();

		// Check, if data in the re-loaded list is the same as before:
		boolean statusTest = true;
		for ( PerformedTranslation pt : liste ) {
			if (pt.equals(pt1) && ( pt.hashCode() == pt1.hashCode() )) {
				statusTest = true;
			} else if (pt.equals(pt2) && ( pt.hashCode() == pt2.hashCode() ) ) {
				statusTest = true;
			} else {
				statusTest = false;
			}
		}

		boolean overallTest = true;
		// Print out status on Test Case for checking the objects
		if (statusTest) {
			System.out.println("Test Report: Stored objects could be found again! --> Test Case successful!");
		} else {
			System.out.println("Test Report: Stored objects could NOT be found again! --> Test Case NOT successful!");
			overallTest = false;
		}

		// Check size, print status:
		if ( sizeAfter == sizeBefore + 2 ) {
			System.out.println("Test Report: Loaded List has been increased by 2! Before: " + sizeBefore + ", After: " + sizeAfter + "--> Test Case successful!" );
		} else {
			System.out.println("Test Report: Loaded List has NOT been increased by 2! Before: " + sizeBefore + ", After: " + sizeAfter + "--> Test Case NOT successful!");
			overallTest = false;
		}

		// Print final Report for Test Suite
		if (overallTest) {
			System.out.println("All Test Cases are successful --> Test Suite successful!");
		} else {
			System.out.println("Some Test Cases are NOT successful --> Test Suite NOT successful!");
		}

	}

	public static void main(String[] args) {
		TestPersistence pers = new TestPersistence();
		pers.run();

	}

}
