package lv.nixx.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

public class TransactionItemProcessor implements ItemProcessor<Transaction, Transaction> {

	private static final Logger log = LoggerFactory.getLogger(TransactionItemProcessor.class);

	@Override
	public Transaction process(final Transaction transaction) {
		final String accId = transaction.getAccountId().toUpperCase();
		final String currency = transaction.getCurrency().toUpperCase();
		final BigDecimal amount = transaction.getAmount();

		final Transaction transformedTransaction = new Transaction(accId, currency, amount);

		log.info("Converting (" + transaction + ") into (" + transformedTransaction + ")");

		return transformedTransaction;
	}

}
