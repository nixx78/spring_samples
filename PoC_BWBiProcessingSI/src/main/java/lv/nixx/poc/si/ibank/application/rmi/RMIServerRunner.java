package lv.nixx.poc.si.ibank.application.rmi;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import lv.nixx.poc.si.ibank.application.ibank.BOISManager;

import org.slf4j.*;

public class RMIServerRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private BOISManager serverImpl = null;
	
	private int rmiPort = 1099;
	private String hostName = "localhost";
	private String rmiServerName ="bwPOC";
	private String serverString; 

	private Registry registry;

	public RMIServerRunner(BOISManager serverImpl){
		this.serverImpl = serverImpl;
	    serverString = "//" + hostName +":"+ rmiPort  + "/" + rmiServerName;
	}
	
	// method run implementation as RMI Server
	public void startRMIServer() throws RemoteException, MalformedURLException {
		registry = LocateRegistry.createRegistry( rmiPort );
	    logger.info("binding RMI server as ["+serverString + "]");
	    registry.rebind( rmiServerName, serverImpl );

	    logger.info("RMI server succesfully binded");
	}
	
	public void shutdownRMIServer(){
		try {
			UnicastRemoteObject.unexportObject(serverImpl, true);
			registry.unbind(rmiServerName);
    	    logger.info("RMI server succesfully shutdowned");
		} catch (Exception e) {
		    logger.error(e.getMessage(), e );
		}
	}
	
}
