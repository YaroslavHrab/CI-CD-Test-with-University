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
import ua.com.foxminded.university.service.postgre.PostgreSqlTeacherService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class TeacherControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostgreSqlTeacherService teacherService;

	@Test
	public void whenGetTeachersRequestToTeacherControllerIsValid_thenCorrectResponse() throws Exception {
		mockMvc.perform(get("/m.cruds/teachers"))
		        .andDo(print())
		        .andExpect(status().isOk());
	}
	
	@Test
	public void whenFindOneRequestToTeacherControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(get("/m.cruds/teachers/1"))
	            .andDo(print())
	            .andExpect(status().isOk());
	}

	@Test
	public void whenNewTeacherRequestToTeacherControllerIsValid_thenCorrectResponse() throws Exception {
	    String teacher = "{\"name\": \"William\",\"surname\": \"Abram\"}";
		mockMvc.perform(post("/m.cruds/teachers")
		        .content(teacher)
		        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isOk());
	}

	@Test
	public void whenNewStudentRequestToTeacherControllerIsInvalid_thenErrorsPresent() throws Exception {
	    String teacher = "{\"name\": \"\",\"surname\": \"\"}";
		mockMvc.perform(post("/m.cruds/teachers")
				.content(teacher)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(6)))
                .andExpect(jsonPath("$.errors", hasItem("Teacher name can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher name length must be from 2 to 20 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher name must begin with A-Z and contain only letters")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher surname can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher surname length must be from 2 to 20 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher surname must begin with A-Z and contain only letters")));
	}

	@Test
	public void whenUpdateTeacherRequestToTeacherControllerIsInvalid_thenErrorsPresent() throws Exception {
        String teacher = "{\"name\": \"\",\"surname\": \"\"}";
        mockMvc.perform(put("/m.cruds/teachers/1")
                .content(teacher)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(6)))
                .andExpect(jsonPath("$.errors", hasItem("Teacher name can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher name length must be from 2 to 20 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher name must begin with A-Z and contain only letters")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher surname can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher surname length must be from 2 to 20 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Teacher surname must begin with A-Z and contain only letters")));
	}
	
    @Test
	public void whenDeleteRequestToTeacherControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(delete("/m.cruds/teachers/1"))
	            .andDo(print())
	            .andExpect(status().isOk());
    }
}
