package ua.com.foxminded.university.web.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.foxminded.university.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters=false)
public class FrontControllerTest {

	 @Autowired
	 private MockMvc mockMvc;
	 
	 @Test
	 public void whenGetRequestToRootIsValid_thenCorrectResponse() throws Exception {
		 mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
	      .andExpect(view().name("index"));
	 }
	 
	 @Test
	 public void whenGetRequestToCrudsIsValid_thenCorrectResponse() throws Exception {
		 mockMvc.perform(get("/cruds")).andDo(print()).andExpect(status().isOk())
	      .andExpect(view().name("cruds"));
	 }
}
