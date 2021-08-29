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
import ua.com.foxminded.university.service.postgre.PostgreSqlGroupService;
import ua.com.foxminded.university.service.postgre.PostgreSqlStudentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class StudentControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostgreSqlStudentService studentService;
    
    @MockBean 
    private PostgreSqlGroupService groupService;

	@Test
	public void whenGetStudentsRequestToStudentControllerIsValid_thenCorrectResponse() throws Exception {
		mockMvc.perform(get("/m.cruds/students"))
		        .andDo(print())
		        .andExpect(status().isOk());
	}
	
	@Test
	public void whenFindOneRequestToStudentControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(get("/m.cruds/students/1"))
	            .andDo(print())
	            .andExpect(status().isOk());
	}

	@Test
	public void whenNewStudentRequestToStudentControllerIsValid_thenCorrectResponse() throws Exception {
	    String student = "{\"name\": \"William\",\"surname\": \"Abram\",\"group_id\": 1}";
		mockMvc.perform(post("/m.cruds/students")
		        .content(student)
		        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isOk());
	}

	@Test
	public void whenNewStudentRequestToStudentControllerIsInvalid_thenErrorsPresent() throws Exception {
	    String student = "{\"name\": \"\",\"surname\": \"\",\"groupId\": -1}";
		mockMvc.perform(post("/m.cruds/students")
				.content(student)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(7)))
                .andExpect(jsonPath("$.errors", hasItem("Student name can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Student name length must be from 2 to 20 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Student name must begin with A-Z and contain only letters")))
                .andExpect(jsonPath("$.errors", hasItem("Student surname can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Student surname length must be from 2 to 20 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Student surname must begin with A-Z and contain only letters")))
                .andExpect(jsonPath("$.errors", hasItem("Group id must be positive integer")));
	}

	@Test
	public void whenUpdateStudentRequestToStudentControllerIsInvalid_thenErrorsPresent() throws Exception {
	    String student = "{\"name\": \"\",\"surname\": \"\",\"groupId\": -1}";
        mockMvc.perform(put("/m.cruds/students/1")
                .content(student)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(7)))
                .andExpect(jsonPath("$.errors", hasItem("Student name can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Student name length must be from 2 to 20 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Student name must begin with A-Z and contain only letters")))
                .andExpect(jsonPath("$.errors", hasItem("Student surname can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Student surname length must be from 2 to 20 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Student surname must begin with A-Z and contain only letters")))
                .andExpect(jsonPath("$.errors", hasItem("Group id must be positive integer")));
	}
	
    @Test
	public void whenDeleteRequestToStudentControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(delete("/m.cruds/students/1"))
	            .andDo(print())
	            .andExpect(status().isOk());
    }
}
