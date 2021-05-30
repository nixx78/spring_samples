package lv.nixx.poc;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<Transaction> reader() {
		return new FlatFileItemReaderBuilder<Transaction>()
			.name("txnDataReader")
			.resource(new ClassPathResource("sample-data.csv"))
			.delimited()
			.names("account_id", "currency", "amount")
			.fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
				setTargetType(Transaction.class);
			}})
			.build();
	}

	@Bean
	public TransactionItemProcessor processor() {
		return new TransactionItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Transaction> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Transaction>()
			.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
			.sql("INSERT INTO transaction_tbl (account_id, currency, amount) VALUES (:accountId, :currency, :amount)")
			.dataSource(dataSource)
			.build();
	}
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step firstStep) {
		return jobBuilderFactory.get("transactionBatchProcessing")
			.incrementer(new RunIdIncrementer())
			.listener(listener)
			.flow(firstStep)
			.end()
			.build();
	}

	@Bean
	public Step firstStep(JdbcBatchItemWriter<Transaction> writer) {
		return stepBuilderFactory.get("firstStep")
			.<Transaction, Transaction> chunk(10)
			.reader(reader())
			.processor(processor())
			.writer(writer)
			.build();
	}
}
