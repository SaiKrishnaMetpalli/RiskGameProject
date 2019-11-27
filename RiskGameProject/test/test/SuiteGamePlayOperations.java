package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPlayerAttack.class, TestPlayerFortification.class, TestPlayerReinforcement.class })
public class SuiteGamePlayOperations {

}
