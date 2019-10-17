package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestFortification.class, TestMapOperations.class, TestPlayerOperations.class, TestReinforcement.class })
public class AllTests {

}
