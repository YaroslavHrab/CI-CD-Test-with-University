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
import ua.com.foxminded.university.model.Subject;
import ua.com.foxminded.university.service.postgre.PostgreSqlSubjectService;
import ua.com.foxminded.university.service.postgre.PostgreSqlTeacherService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostgreSqlSubjectService subjectService;
    
    @MockBean
    private PostgreSqlTeacherService teacherService;

    @Test
    public void whenGetSubjectsRequestToSubjectControllerIsValid_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/cruds/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("subjects/subjects"));
    }

    @Test
    public void whenNewSubjectRequestToSubjectControllerIsValid_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/cruds/subjects/newsubject")).andDo(print())
                .andExpect(model().attribute("subject", hasProperty("name", nullValue())))
                .andExpect(model().attribute("subject", hasProperty("teacherId", nullValue())))
                .andExpect(model().attribute("subject", hasProperty("id", nullValue())))
                .andExpect(view().name("subjects/add"));
    }

    @Test
	public void whenAddSubjectRequestToSubjectControllerIsInvalid_thenErrorsPresent() throws Exception {
        Subject subject = new Subject("test", "Desk");
        subject.setTeacherId(-1L);
		mockMvc.perform(post("/cruds/subjects/addsubject")
			    .flashAttr("subject", subject))
		        .andDo(print())
				.andExpect(model().attributeHasErrors("subject"))
				.andExpect(model().attributeHasFieldErrors("subject", "name", "teacherId"));
	}

	@Test
	public void whenAddSubjectRequestToSubjectControllerIsValid_thenCorrectResponse() throws Exception {
	    Subject subject = new Subject("Test", "Desk");
	    subject.setTeacherId(1L);
		mockMvc.perform(post("/cruds/subjects/addsubject")
		        .flashAttr("subject", subject))
		        .andDo(print())
				.andExpect(model().hasNoErrors())
				.andExpect(redirectedUrl("/cruds/subjects"));
	}

	@Test
	public void whenUpdateSubjectRequestToSubjectControllerIsInvalid_thenErrorsPresent() throws Exception {
	    Subject subject = new Subject("test", "Desk");
        subject.setTeacherId(-1L);
        mockMvc.perform(post("/cruds/subjects/update")
                .flashAttr("subject", subject))
                .andDo(print())
                .andExpect(model().attributeHasErrors("subject"))
                .andExpect(model().attributeHasFieldErrors("subject", "name", "teacherId"));
	}

	@Test
	public void whenUpdateSubjectsRequestToSubjectControllerIsValid_thenCorrectResponse() throws Exception {
	    Subject subject = new Subject("Test", "Desk");
	    subject.setTeacherId(1L);
        mockMvc.perform(post("/cruds/subjects/addsubject")
                .flashAttr("subject", subject))
                .andDo(print())
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/cruds/subjects"));
	}
}
