package org.vgu.se.translationapp.testjunit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.vgu.se.translationapp.model.logic.TranslationNumber;

public class TestTranslationNumberJ {
	private TranslationNumber trans = new TranslationNumber();
	private String eins;
	private String zwei;
	private String negTest;

	@Before
	public void setUp() throws Exception {
		eins = trans.translateNum("eins");
		zwei = trans.translateNum("zwei");
		negTest = trans.translateNum("hahaha");
	}

	@Test
	public void testTranslationNumber(){
		assertEquals("one", eins);
		assertEquals("two", zwei);
		assertEquals("I don't understand", negTest);
	}

//		if (statusTest) {
//			System.out.println("\nTest Suite was successful!");
//		} else {
//			System.out.println("\nTest Suite was NOT successful!" );
//		}



//	public static void main(String[] args) {
//		TestTranslationNumber client = new TestTranslationNumber();
//		client.run();
//
//	}

}
