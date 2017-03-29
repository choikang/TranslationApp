package org.vgu.se.translationapp.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestPersistence.class, TestTranslationNumber.class, TestTranslationPhrase.class})
public class AllTests {
}
