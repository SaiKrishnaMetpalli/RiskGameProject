package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is test suite class for running all the test cases.
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ SuiteStartUpOperations.class, SuiteGamePlayOperations.class, SuiteTournamentOperations.class })
public class AllTests {

}
