package lv.nixx.poc.rest;

import java.util.UUID;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

public class CRUDSampleControllerMockTest {

	UUID key = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");

	MockMvc mockMvc;

	@InjectMocks
	CRUDSampleController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}
	
	@Test
	public void addPersonUsingJSONString() throws Exception {
		 this.mockMvc.perform(
		            post("/person")
		                    .content(PersonFixtures.createPersonJSON(key))
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .accept(MediaType.APPLICATION_JSON))
		            .andDo(print())
		            .andExpect(status().isCreated());
	}
	
	
	@Test
	public void getPersonPrintBody() throws Exception {
		 this.mockMvc.perform(get("/person/{id}",key.toString())
			 .accept(MediaType.APPLICATION_JSON)).andDo(print());
		
	}
	
	@Test
	public void getMethodTest() throws Exception{
		
		 this.mockMvc.perform(get("/person/{id}",key.toString())
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(jsonPath("$.id").value(key.toString()))
		       .andExpect(jsonPath("$.name").value("person.name"))
		       .andExpect(jsonPath("$.surname").value("person.surname"));
	}

}
