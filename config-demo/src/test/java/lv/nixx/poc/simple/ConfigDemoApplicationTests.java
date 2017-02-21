package lv.nixx.poc.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lv.nixx.poc.simple.Engine;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SimpleConfig.class)
public class ConfigDemoApplicationTests {
	
	@Autowired
	Engine engine;

	@Test
	public void contextLoads() {
		engine.process();
	}

}
