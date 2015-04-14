package lv.nixx.poc.si.ibank.application.ibank;

// BankWorld interface prototype, contains business methods on BankWorld side 

public interface IBankInterface {
	public boolean update_account_details(String accountDetails);
	public boolean update_account_balance(String accountBalance);
	public boolean update_statements(String statements);
	
	// ... more methods on BankWorld side 
}
