package lv.nixx.poc.ibank.rmi;

import lv.nixx.poc.ibank.rmi.bank.instruction.*;
import lv.nixx.poc.ibank.rmi.payment.PostTransactionRMI_Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		BankInstructionRMILogical_Test.class,
		BankInstructionRMIThreads_Test.class,
		PostTransactionRMI_Test.class
})

public class RunAllRMIClientTests {
}
