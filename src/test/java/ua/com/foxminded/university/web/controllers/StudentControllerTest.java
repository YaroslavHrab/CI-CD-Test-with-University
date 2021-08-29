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
import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.service.postgre.PostgreSqlGroupService;
import ua.com.foxminded.university.service.postgre.PostgreSqlStudentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostgreSqlStudentService studentService;
    
    @MockBean 
    private PostgreSqlGroupService groupService;
    
    @Test
    public void whenGetStudentsRequestToStudentControllerIsValid_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/cruds/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("students/students"));
    }

    @Test
    public void whenNewStudentRequestToStudentControllerIsValid_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/cruds/students/newstudent")).andDo(print())
                .andExpect(model().attribute("student", hasProperty("name", nullValue())))
                .andExpect(model().attribute("student", hasProperty("surname", nullValue())))
                .andExpect(model().attribute("student", hasProperty("id", nullValue())))
                .andExpect(model().attribute("student", hasProperty("groupId", nullValue())))
                .andExpect(view().name("students/add"));
    }

    @Test
	public void whenAddStudentRequestToStudentControllerIsInvalid_thenErrorsPresent() throws Exception {
        Student student = new Student("test", "test");
        student.setGroupId(-1L);
		mockMvc.perform(post("/cruds/students/addstudent")
			    .flashAttr("student", student))
		        .andDo(print())
				.andExpect(model().attributeHasErrors("student"))
				.andExpect(model().attributeHasFieldErrors("student", "name", "surname", "groupId"));
	}

	@Test
	public void whenAddStudentRequestToStudentControllerIsValid_thenCorrectResponse() throws Exception {
        Student student = new Student("Test", "Test");
		mockMvc.perform(post("/cruds/students/addstudent")
		        .flashAttr("student", student))
		        .andDo(print())
				.andExpect(model().hasNoErrors())
				.andExpect(redirectedUrl("/cruds/students"));
	}

	@Test
	public void whenUpdateStudentRequestToStudentControllerIsInvalid_thenErrorsPresent() throws Exception {
	    Student student = new Student("test", "test");
        student.setGroupId(-1L);
        mockMvc.perform(post("/cruds/students/update")
                .flashAttr("student", student))
                .andDo(print())
                .andExpect(model().attributeHasErrors("student"))
                .andExpect(model().attributeHasFieldErrors("student", "name", "surname", "groupId"));
	}

	@Test
	public void whenUpdateStudentRequestToStudentControllerIsValid_thenCorrectResponse() throws Exception {
	    Student student = new Student("Test", "Test");
		mockMvc.perform(post("/cruds/students/update")
		        .flashAttr("student", student))
		        .andDo(print())
		        .andExpect(model().hasNoErrors())
		        .andExpect(redirectedUrl("/cruds/students"));
	}
}
