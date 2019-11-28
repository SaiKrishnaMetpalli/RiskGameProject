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
@SuiteClasses({ TestPlayerAttack.class, TestPlayerFortification.class, TestPlayerReinforcement.class })
public class SuiteGamePlayOperations {

}
