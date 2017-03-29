package org.vgu.se.translationapp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.vgu.se.translationapp.model.entities.PerformedTranslation;
import org.vgu.se.translationapp.model.logic.PersistenceExeception;
import org.vgu.se.translationapp.model.logic.StoreAndLoadTranslation;

public class TestPersistence {
	private PerformedTranslation pt1 = new PerformedTranslation();
	private PerformedTranslation pt2 = new PerformedTranslation();
	private List<PerformedTranslation> liste = new ArrayList<PerformedTranslation>();
	private int sizeAfter;
	private int sizeBefore;

	@Before
	public void setUp() throws Exception{

		// Load Data, so that new Objects will be added correctly, without deleting old ones
		try {
			StoreAndLoadTranslation.getInstance().loadPerformedTranslations();
		} catch (PersistenceExeception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Grab current number of objects:
		sizeBefore = StoreAndLoadTranslation.getInstance().size();


		pt1.setExpressionGER("eins");
		pt1.setExpressionEN("one");


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
		sizeAfter = StoreAndLoadTranslation.getInstance().size();

		// Get current List
		liste = StoreAndLoadTranslation.getInstance().getListOfCurrentTranslation();
	}


	@Test
	public void testPersistence() {
		assertEquals(liste.get(0),pt1);
		assertEquals(liste.get(1),pt2);
	}

	@Test
	public void testSize(){
		assertEquals(sizeBefore + 2,sizeAfter);
	}


}
