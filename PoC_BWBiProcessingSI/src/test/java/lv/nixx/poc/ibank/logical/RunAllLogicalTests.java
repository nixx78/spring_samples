package lv.nixx.poc.ibank.logical;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		BankInstruction_Test.class,
		PostTransaction_Test.class
})

public class RunAllLogicalTests {
}
