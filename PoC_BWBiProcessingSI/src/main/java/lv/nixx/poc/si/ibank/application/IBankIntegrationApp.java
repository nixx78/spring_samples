package lv.nixx.poc.si.ibank.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import lv.nixx.poc.si.ibank.application.rmi.RMIServerRunner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IBankIntegrationApp {
	
	public static void main(String[] args) throws Exception {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
    	context.start();
        
        RMIServerRunner rmiServerRunner = context.getBean(RMIServerRunner.class);
        rmiServerRunner.startRMIServer();
        
        System.out.println("application started, press any key to exit");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        
        System.out.println("application stoping");
        
        rmiServerRunner.shutdownRMIServer();
        context.stop();
        context.close();

	}

}
