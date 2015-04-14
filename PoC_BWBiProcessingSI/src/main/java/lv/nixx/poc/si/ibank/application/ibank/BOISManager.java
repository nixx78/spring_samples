package lv.nixx.poc.si.ibank.application.ibank;

import java.rmi.Remote;
import java.rmi.RemoteException;


// Interface on BOIS side 

public interface BOISManager extends Remote {
	
	public String post_bank_instruction(String bankInstruction) throws RemoteException;
	public String post_transaction(String[] payments) throws RemoteException;
	
	// more methods on BOIS side ...
	
}
