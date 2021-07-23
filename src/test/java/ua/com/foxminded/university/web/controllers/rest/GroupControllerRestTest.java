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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class GroupControllerRestTest {

    @Autowired
	private MockMvc mockMvc;
    
    @MockBean
    private PostgreSqlGroupService groupService;

	@Test
	public void whenGetGroupsRequestToGroupControllerIsValid_thenCorrectResponse() throws Exception {
		mockMvc.perform(get("/m.cruds/groups"))
		        .andDo(print())
		        .andExpect(status().isOk());
	}
	
	@Test
	public void whenFindOneRequestToGroupControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(get("/m.cruds/groups/1"))
	            .andDo(print())
	            .andExpect(status().isOk());
	}

	@Test
	public void whenNewGroupRequestToGroupControllerIsValid_thenCorrectResponse() throws Exception {
	    String group = "{\"name\": \"IP-01\"}";
		mockMvc.perform(post("/m.cruds/groups")
		        .content(group)
		        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isOk());
	}

	@Test
	public void whenNewGroupRequestToGroupControllerIsInvalid_thenErrorsPresent() throws Exception {
	    String group = "{\"name\": \"\"}";
		mockMvc.perform(post("/m.cruds/groups")
				.content(group)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(3)))
                .andExpect(jsonPath("$.errors", hasItem("Group name can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Group name length must be 5 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Group name must be \"AA-00\" format")));
	}

	@Test
	public void whenUpdateGroupRequestToGroupControllerIsInvalid_thenErrorsPresent() throws Exception {
	    String group = "{\"name\": \"\"}";
        mockMvc.perform(put("/m.cruds/groups/1")
                .content(group)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(3)))
                .andExpect(jsonPath("$.errors", hasItem("Group name can`t be empty")))
                .andExpect(jsonPath("$.errors", hasItem("Group name length must be 5 digits")))
                .andExpect(jsonPath("$.errors", hasItem("Group name must be \"AA-00\" format")));
	}
	
    @Test
	public void whenDeleteRequestToGroupControllerIsValid_thenCorrectResponse() throws Exception {
	    mockMvc.perform(delete("/m.cruds/groups/1"))
	            .andDo(print())
	            .andExpect(status().isOk());
    }
}
