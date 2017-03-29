package org.vgu.se.translationapp.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.vgu.se.translationapp.model.logic.PersistenceExeception;
import org.vgu.se.translationapp.model.logic.TranslationNumber;

public class TestTranslationNumber {
	private TranslationNumber trans = new TranslationNumber();

	@Test
	public void testTranslationNumber() throws PersistenceExeception{
		assertEquals("one", trans.translateNum("eins"));
		assertEquals("two", trans.translateNum("zwei"));
		assertEquals("three", trans.translateNum("drei"));
		assertEquals("four", trans.translateNum("vier"));
		assertEquals("five", trans.translateNum("fuenf"));
		assertEquals("six", trans.translateNum("sechs"));
		assertEquals("seven", trans.translateNum("sieben"));
		assertEquals("eight", trans.translateNum("acht"));
		assertEquals("nine", trans.translateNum("neun"));
		assertEquals("ten", trans.translateNum("zehn"));
		assertEquals("eleven", trans.translateNum("elf"));
		assertEquals("twelve", trans.translateNum("zwoelf"));
		assertEquals("thirteen", trans.translateNum("dreizehn"));
		assertEquals("fourteen", trans.translateNum("vierzehn"));
		assertEquals("fifteen", trans.translateNum("fuenfzehn"));
		assertEquals("sixteen", trans.translateNum("sechzehn"));
		assertEquals("seventeen", trans.translateNum("siebzehn"));
		assertEquals("eighteen", trans.translateNum("achtzehn"));
		assertEquals("nineteen", trans.translateNum("neunzehn"));
		assertEquals("twenty", trans.translateNum("zwanzig"));
		assertEquals("I don't understand", trans.translateNum("hahaha"));
	}
}
