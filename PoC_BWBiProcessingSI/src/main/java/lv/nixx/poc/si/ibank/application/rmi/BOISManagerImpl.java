package lv.nixx.poc.si.ibank.application.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.StringTokenizer;

import lv.nixx.poc.si.ibank.application.domain.*;
import lv.nixx.poc.si.ibank.application.gateway.*;
import lv.nixx.poc.si.ibank.application.ibank.*;
import lv.nixx.poc.si.ibank.application.util.PaymentHelper;

import org.slf4j.*;


/**
 * This class is entry point from RMI Interface to SpringIntegration Gateway 
 * 
 * @author nixx
 *
 */

public class BOISManagerImpl extends UnicastRemoteObject implements BOISManager  {
	
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private PaymentGateway paymentGateway;
	private BankInstructionGateway bankInstructionGateway;

	protected BOISManagerImpl() throws RemoteException {
		super();
	}
	
	@Override
	public String post_bank_instruction(String bankInstruction) throws RemoteException {
		setThreadName();
		
		logger.debug("BI come [" + bankInstruction + "]");

		BankInstruction biObject = new BankInstruction();
		
		// parse string with bank instruction
		StringTokenizer tokenizer = new StringTokenizer(bankInstruction,":");
		biObject.setCorrelationID(tokenizer.nextToken());
		biObject.setBiType(tokenizer.nextToken());
		biObject.setBiData(tokenizer.nextToken());
		
		BankInstructionResponse response = bankInstructionGateway.sendBankInstruction(biObject);
		logger.debug("original BI [" + response.getBankInstruction().getBiData() + "] response [" + response.getResponseData() + "]");

		return response.getCorrelationID() + ":" + response.getResponseData();
	}

	private void setThreadName() {
		String threadName = Thread.currentThread().getName();
		
		// get thread name from original RMI thread, and set new thread name
		int startPos = threadName.indexOf("(");
		int endPos = threadName.indexOf(")");
		
		if (startPos!=-1 && endPos!=-1){
			String threadNumber = threadName.substring(startPos+1, endPos);
			threadName = "T" + threadNumber + ":" + System.currentTimeMillis();
			Thread.currentThread().setName(threadName);
		}
	}

	@Override
	public String post_transaction(String[] payments) throws RemoteException {
		setThreadName();
		logger.debug("payments come, count [" + payments.length + "]");
		
		PaymentResponseList responseToPayment = paymentGateway.sendTransaction(PaymentHelper.createPaymentsList(payments));
		
		String paymentResponseAsString = PaymentHelper.createPaymentResponseString(responseToPayment);
		
		logger.debug("payment response as String [" + paymentResponseAsString + "]");
		return paymentResponseAsString;
	}

	public void setPaymentGateway(PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public void setBankInstructionGateway(
			BankInstructionGateway bankInstructionGateway) {
		this.bankInstructionGateway = bankInstructionGateway;
	}

}
