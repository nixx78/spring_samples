package lv.nixx.poc.ibank.rmi;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;

import lv.nixx.poc.si.ibank.application.ibank.BOISManager;

public class GenericRMIServerTest {
	
	private final String rmiServerString = "//localhost:1099/bwPOC";

	protected BOISManager getConnectionToServer() throws MalformedURLException, RemoteException, NotBoundException { 
		Registry registry = LocateRegistry.getRegistry("localhost", 1099);
		System.out.println("connecting to server [" + rmiServerString + "]");
	    BOISManager boisServer = null;
	    
	    try {
	    	boisServer = (BOISManager) registry.lookup("bwPOC");
	    } catch (ConnectException ex) {
	    	System.err.println("error [" + ex.getMessage() + "] please check that BOIS RMI Server [" + rmiServerString + "] started");
	    	throw ex;
	    }
		
		return boisServer;
	}

}
