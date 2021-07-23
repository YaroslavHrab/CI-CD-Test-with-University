package ua.com.foxminded.university.web.controllers;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.com.foxminded.university.Application;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.service.postgre.PostgreSqlGroupService;
import ua.com.foxminded.university.service.postgre.PostgreSqlLessonService;
import ua.com.foxminded.university.service.postgre.PostgreSqlSubjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class LessonControllerTest {
    
    private static MockMvc mockMvc;
    
    @BeforeAll
    private static void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new LessonController(mock(PostgreSqlLessonService.class), 
                mock(PostgreSqlSubjectService.class), mock(PostgreSqlGroupService.class)))
                .build();
    }
    
    @Test
    public void whenGetLessonsRequestToLessonControllerIsValid_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/cruds/lessons"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons"));
    }

    @Test
    public void whenNewLessonRequestToLessonControllerIsValid_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/cruds/lessons/newlesson")).andDo(print())
                .andExpect(model().attribute("lesson", hasProperty("group", nullValue())))
                .andExpect(model().attribute("lesson", hasProperty("subject", nullValue())))
                .andExpect(model().attribute("lesson", hasProperty("beginingTime", nullValue())))
                .andExpect(model().attribute("lesson", hasProperty("lessonNumber", equalTo(0))))
                .andExpect(model().attribute("lesson", hasProperty("id", nullValue())))
                .andExpect(view().name("lessons/add"));
    }

    @Test
	public void whenAddLessonRequestToLessonControllerIsInvalid_thenErrorsPresent() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setGroup_id(-1L);
        lesson.setSubject_id(-1L);
        lesson.setLessonNumber(10);
		mockMvc.perform(post("/cruds/lessons/addlesson")
			    .flashAttr("lesson", lesson)
		        .param("beginningTime", LocalDateTime.now().toString()))
		        .andDo(print())
				.andExpect(model().attributeHasErrors("lesson"))
				.andExpect(model().attributeHasFieldErrors("lesson", "subject_id", "group_id", "lessonNumber"));
	}

	@Test
	public void whenAddLessonRequestToLessonControllerIsValid_thenCorrectResponse() throws Exception {
	    Lesson lesson = new Lesson();
        lesson.setGroup_id(1L);
        lesson.setSubject_id(1L);
        lesson.setLessonNumber(5);
		mockMvc.perform(post("/cruds/lessons/addlesson")
		        .flashAttr("lesson", lesson)
		        .param("beginningTime", LocalDateTime.now().toString()))
		        .andDo(print())
				.andExpect(model().hasNoErrors())
		        .andExpect(redirectedUrl("/cruds/lessons"));
	}

	@Test
	public void whenUpdateLessonRequestToLessonControllerIsInvalid_thenErrorsPresent() throws Exception {
	    Lesson lesson = new Lesson();
        lesson.setGroup_id(-1L);
        lesson.setSubject_id(-1L);
        lesson.setLessonNumber(10);
        mockMvc.perform(post("/cruds/lessons/update")
                .flashAttr("lesson", lesson)
                .param("beginningTime", LocalDateTime.now().toString()))
                .andDo(print())
                .andExpect(model().attributeHasErrors("lesson"))
                .andExpect(model().attributeHasFieldErrors("lesson", "subject_id", "group_id", "lessonNumber"));
	}

	@Test
	public void whenUpdateSubjectsRequestToLessonControllerIsValid_thenCorrectResponse() throws Exception {
	    Lesson lesson = new Lesson();
        lesson.setGroup_id(1L);
        lesson.setSubject_id(1L);
        lesson.setLessonNumber(5);
        mockMvc.perform(post("/cruds/lessons/update")
                .flashAttr("lesson", lesson)
                .param("beginningTime", LocalDateTime.now().toString()))
                .andDo(print())
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/cruds/lessons"));
	}
}
