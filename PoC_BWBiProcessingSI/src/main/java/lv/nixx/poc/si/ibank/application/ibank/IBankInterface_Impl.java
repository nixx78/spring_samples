package lv.nixx.poc.si.ibank.application.ibank;

import org.slf4j.*;

// Mock class for BankWorld implementation
public class IBankInterface_Impl implements IBankInterface {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean update_account_details(String accountDetails) {
		logger.debug("update_account_details:" + accountDetails);
		
		return true;
	}

	@Override
	public boolean update_account_balance(String accountBalance) {
		logger.debug("update_account_balance:" + accountBalance);
		return true;
	}

	@Override
	public boolean update_statements(String statements) {
		logger.debug("update_account_balance:" +  statements);
		return true;
	}

}
