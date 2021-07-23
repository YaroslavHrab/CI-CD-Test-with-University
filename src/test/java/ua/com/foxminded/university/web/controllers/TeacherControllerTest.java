package ua.com.foxminded.university.web.controllers;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.foxminded.university.Application;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.postgre.PostgreSqlTeacherService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostgreSqlTeacherService teacherService;

    @Test
    public void whenGetTeachersRequestToTeacherControllerIsValid_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/cruds/teachers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("teachers/teachers"));
    }

    @Test
    public void whenNewTeacherRequestToTeacherControllerIsValid_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/cruds/teachers/newteacher")).andDo(print())
                .andExpect(model().attribute("teacher", hasProperty("name", nullValue())))
                .andExpect(model().attribute("teacher", hasProperty("surname", nullValue())))
                .andExpect(view().name("teachers/add"));
    }

    @Test
	public void whenAddTeacherRequestToGroupControllerIsInvalid_thenErrorsPresent() throws Exception {
		mockMvc.perform(post("/cruds/teachers/addteacher")
			    .flashAttr("teacher", new Teacher("test", "test")))
		        .andDo(print())
				.andExpect(model().attributeHasErrors("teacher"))
				.andExpect(model().attributeHasFieldErrors("teacher", "name", "surname"));
	}

	@Test
	public void whenAddTeacherRequestToTeacherControllerIsValid_thenCorrectResponse() throws Exception {
		mockMvc.perform(post("/cruds/teachers/addteacher")
		        .flashAttr("teacher",  new Teacher("Test", "Test")))
		        .andDo(print())
				.andExpect(model().hasNoErrors())
				.andExpect(redirectedUrl("/cruds/teachers"));
	}

	@Test
	public void whenUpdateTeacherRequestToTeacherControllerIsInvalid_thenErrorsPresent() throws Exception {
        mockMvc.perform(post("/cruds/teachers/update")
                .flashAttr("teacher", new Teacher("test", "test")))
                .andDo(print())
                .andExpect(model().attributeHasErrors("teacher"))
                .andExpect(model().attributeHasFieldErrors("teacher", "name", "surname"));
	}

	@Test
	public void whenUpdateTeacherRequestToTeacherControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(post("/cruds/teachers/update")
                .flashAttr("teacher",  new Teacher("Test", "Test")))
                .andDo(print())
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/cruds/teachers"));
	}
}
