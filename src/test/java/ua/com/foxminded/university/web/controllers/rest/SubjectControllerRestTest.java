package ua.com.foxminded.university.web.controllers.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.foxminded.university.Application;
import ua.com.foxminded.university.service.postgre.PostgreSqlSubjectService;
import ua.com.foxminded.university.service.postgre.PostgreSqlTeacherService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class SubjectControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostgreSqlSubjectService subjectService;
    
    @MockBean
    private PostgreSqlTeacherService teacherService;

	@Test
	public void whenGetSubjectRequestToSubjectControllerIsValid_thenCorrectResponse() throws Exception {
		mockMvc.perform(get("/m.cruds/subjects"))
		        .andDo(print())
		        .andExpect(status().isOk());
	}
	
	@Test
	public void whenFindOneRequestToSubjectControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(get("/m.cruds/subjects/1"))
	            .andDo(print())
	            .andExpect(status().isOk());
	}

	@Test
	public void whenNewSubjectRequestToSubjectControllerIsValid_thenCorrectResponse() throws Exception {
	    String subject = "{\"name\": \"Physics\",\"description\": \"Very important subject\",\"teacher\": {" + 
                "\"name\": \"Manuel\",\"surname\": \"Murphy\",\"id\": 1},\"teacher_id\": 1 }";
		mockMvc.perform(post("/m.cruds/subjects")
		        .content(subject)
		        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isOk());
	}

	@Test
	public void whenNewSubjectRequestToSubjectControllerIsInvalid_thenErrorsPresent() throws Exception {
	    String subject = "{\"name\": \"\",\"description\": \"Very important subject\",\"teacher\": {" + 
                "\"name\": \"Manuel\",\"surname\": \"Murphy\",\"id\": 1},\"teacher_id\": -1 }";
		mockMvc.perform(post("/m.cruds/subjects")
				.content(subject)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(4)))
                .andExpect(jsonPath("$.errors", hasItem("Subject name can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Subject name length must be from 2 to 20 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Subject name must begin with A-Z and contain only letters")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher id must be positive integer")));
	}

	@Test
	public void whenUpdateSubjectRequestToSubjectControllerIsInvalid_thenErrorsPresent() throws Exception {
        String subject = "{\"name\": \"\",\"description\": \"Very important subject\",\"teacher\": {" + 
                "\"name\": \"Manuel\",\"surname\": \"Murphy\",\"id\": 1},\"teacher_id\": -1 }";
        mockMvc.perform(put("/m.cruds/subjects/1")
                .content(subject)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(4)))
                .andExpect(jsonPath("$.errors", hasItem("Subject name can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Subject name length must be from 2 to 20 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Subject name must begin with A-Z and contain only letters")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher id must be positive integer")));
	}
	
    @Test
	public void whenDeleteRequestToSubjectControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(delete("/m.cruds/subjects/1"))
	            .andDo(print())
	            .andExpect(status().isOk());
    }
}
