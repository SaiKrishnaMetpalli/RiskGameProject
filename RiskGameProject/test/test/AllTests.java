package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is test suite class for running all the test cases.
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestFortification.class, TestMapOperations.class, TestPlayerOperations.class, TestReinforcement.class,
		TestAttack.class })
public class AllTests {

}
