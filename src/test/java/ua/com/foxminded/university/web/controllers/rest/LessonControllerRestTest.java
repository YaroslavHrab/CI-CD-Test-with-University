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
import ua.com.foxminded.university.service.postgre.PostgreSqlLessonService;
import ua.com.foxminded.university.service.postgre.PostgreSqlSubjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class LessonControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostgreSqlSubjectService subjectService;
    
    @MockBean
    private PostgreSqlGroupService groupService;
    
    @MockBean
    private PostgreSqlLessonService lessonService;

	@Test
	public void whenGetLessonRequestToLessonControllerIsValid_thenCorrectResponse() throws Exception {
		mockMvc.perform(get("/m.cruds/lessons"))
		        .andDo(print())
		        .andExpect(status().isOk());
	}
	
	@Test
	public void whenFindOneRequestToLessonControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(get("/m.cruds/lessons/1"))
	            .andDo(print())
	            .andExpect(status().isOk());
	}

	@Test
	public void whenNewLessonRequestToLessonControllerIsValid_thenCorrectResponse() throws Exception {
	    String lesson = "{\"lessonNumber\": 1,\"beginingTime\": \"2021-03-04T08:30:00\"," +  
	            "\"group_id\": 1,\"subject_id\": 1}";
		mockMvc.perform(post("/m.cruds/lessons")
		        .content(lesson)
		        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isOk());
	}

	@Test
	public void whenNewLessonRequestToLessonControllerIsInvalid_thenErrorsPresent() throws Exception {
        String lesson = "{\"lessonNumber\": 10,\"beginingTime\": \"2021-03-04T08:30:00\"," +  
                "\"groupId\": -1,\"subjectId\": -1}";
		mockMvc.perform(post("/m.cruds/lessons")
				.content(lesson)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(3)))
                .andExpect(jsonPath("$.errors", hasItem("Group id must be positive integer")))
                .andExpect(jsonPath("$.errors", hasItem("Subject id must be positive integer")))
                .andExpect(jsonPath("$.errors", hasItem("Lesson number must be in range from 1 to 5")));
	}

	@Test
	public void whenUpdateLessonRequestToLessonControllerIsInvalid_thenErrorsPresent() throws Exception {
        String lesson = "{\"lessonNumber\": 10,\"beginingTime\": \"2021-03-04T08:30:00\"," +  
                "\"groupId\": -1,\"subjectId\": -1}";
        mockMvc.perform(put("/m.cruds/lessons/1")
                .content(lesson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(3)))
                .andExpect(jsonPath("$.errors", hasItem("Group id must be positive integer")))
                .andExpect(jsonPath("$.errors", hasItem("Subject id must be positive integer")))
                .andExpect(jsonPath("$.errors", hasItem("Lesson number must be in range from 1 to 5")));
	}
	
    @Test
	public void whenDeleteRequestToLessonControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(delete("/m.cruds/lessons/1"))
	            .andDo(print())
	            .andExpect(status().isOk());
    }
}
