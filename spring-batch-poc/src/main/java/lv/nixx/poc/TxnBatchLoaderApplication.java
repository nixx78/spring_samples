package lv.nixx.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TxnBatchLoaderApplication {

	// TODO https://spring.io/guides/gs/batch-processing/

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(TxnBatchLoaderApplication.class, args)));
	}
}
