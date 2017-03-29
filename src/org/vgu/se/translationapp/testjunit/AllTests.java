package org.vgu.se.translationapp.testjunit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestPersistenceJ.class, TestTranslationNumberJ.class})
public class AllTests {
}
