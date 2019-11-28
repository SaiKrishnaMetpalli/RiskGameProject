package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * this test suit is for  all game play operation cases
 * @author sakib
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestMapOperations.class, TestPlayerOperations.class, TestCommonMethods.class })
public class SuiteStartUpOperations {

}
