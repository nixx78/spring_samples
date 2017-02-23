package lv.nixx.poc.simple;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ReportGeneratorTest {
	
	@Test
	public void generateReport() throws IOException {
		
		ReportGenerator rg = new ReportGenerator();
		
		String report = rg.create();
		
		
		FileUtils.writeStringToFile(new File("/tmp/report.html"), report, StandardCharsets.UTF_8);
		
	}

}
