package org.vgu.se.translationapp.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.vgu.se.translationapp.model.logic.PersistenceExeception;
import org.vgu.se.translationapp.model.logic.TranslationPhrase;

public class TestTranslationPhrase {
	private TranslationPhrase trans = new TranslationPhrase();

	@Test
	public void testTranslationPhrase() throws PersistenceExeception {
		assertEquals("white", trans.translateTerm("weiss"));
		assertEquals("dog", trans.translateTerm("Hund"));
		assertEquals("sky", trans.translateTerm("Himmel"));
		assertEquals("cat", trans.translateTerm("Katze"));
		assertEquals("snow", trans.translateTerm("Schnee"));
		assertEquals("to kill two birds with one stone", trans.translateTerm("zwei Fliegen mit einer Klappe schlagen"));
		assertEquals("What's your name?", trans.translateTerm("Wie heisst du?"));
		assertEquals("How old are you?", trans.translateTerm("Wie alt bist du?"));
		assertEquals("I come from Vietnam", trans.translateTerm("Ich komme aus Vietnam"));
	}

}
