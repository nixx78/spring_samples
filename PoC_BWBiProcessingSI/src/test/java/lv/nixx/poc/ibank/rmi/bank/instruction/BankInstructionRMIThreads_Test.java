package lv.nixx.poc.ibank.rmi.bank.instruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.*;

import lv.nixx.poc.ibank.rmi.GenericRMIServerTest;
import lv.nixx.poc.si.ibank.application.ibank.BOISManager;

import org.junit.*;

public class BankInstructionRMIThreads_Test extends GenericRMIServerTest {

	@Test
	@Ignore
	public void screateMultiplyCall() throws InterruptedException{
		
		int iterationsCount = 100;
		for (int i = 0; i<iterationsCount; i++){
			multiplyThreadsTest();
			Thread.sleep(400);
		}
	}
    
	public void multiplyThreadsTest() {
    	
		final int threadCount = 10;
		
				
		List<Thread> threadList = new ArrayList<Thread>();
		for (int i = 0; i < threadCount; i++) {
			try {
				Thread t = new Thread(new BISender(), "T" + i);
				threadList.add(t);
				t.start();

			} catch (Exception ex) {
				System.err.println(ex);
				ex.printStackTrace();
			}
		}

		// wait for all threads
		for (Iterator<Thread> iterator = threadList.iterator(); iterator.hasNext();) {
			Thread thread = iterator.next();
			if (thread!=null) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("thread test end");
	}

	class BISender implements Runnable {

		@Override
		public void run() {
		    
		    BOISManager rmiServer;
			try {
			    rmiServer = getConnectionToServer();

			    final String threadName = Thread.currentThread().getName(); 
			    final String correlationID = threadName + "#" + System.currentTimeMillis() + "";
			    final String biType = "BI_TYPE1";
			    
			    String data = correlationID + ":" + biType + ":BI data";
				System.out.println( threadName +  ": send [" + data + "]");
			    String response = rmiServer.post_bank_instruction(data);
			
				assertNotNull(response);
				
				StringTokenizer st = new StringTokenizer(response, ":");
				assertEquals(correlationID, st.nextToken());
				assertEquals(biType, st.nextToken());
				
				System.out.println("BI response [" + response + "]");

			} catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
				fail();
			}
			
		}
		
	}

}
