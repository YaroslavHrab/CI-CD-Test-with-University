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
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.service.postgre.PostgreSqlGroupService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class GroupControllerTest {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private PostgreSqlGroupService groupService;

	@Test
	public void whenGetGroupsRequestToGroupControllerIsValid_thenCorrectResponse() throws Exception {
		mockMvc.perform(get("/cruds/groups"))
		        .andDo(print())
		        .andExpect(status().isOk())
				.andExpect(view().name("groups/groups"));
	}

	@Test
	public void whenNewGroupRequestToGroupControllerIsValid_thenCorrectResponse() throws Exception {
		mockMvc.perform(get("/cruds/groups/newgroup"))
		        .andDo(print())
				.andExpect(model().attribute("group", hasProperty("name", nullValue())))
				.andExpect(model().attribute("group", hasProperty("id", nullValue())))
				.andExpect(view().name("groups/add"));
	}

	@Test
	public void whenAddGroupRequestToGroupControllerIsInvalid_thenErrorsPresent() throws Exception {
		mockMvc.perform(post("/cruds/groups/addgroup")
				.flashAttr("group", new Group("Test")))
		        .andDo(print())
				.andExpect(model().attributeHasErrors("group"))
				.andExpect(model().attributeHasFieldErrors("group", "name"));
	}

	@Test
	public void whenAddGroupRequestToGroupControllerIsValid_thenCorrectResponse() throws Exception {
		mockMvc.perform(post("/cruds/groups/addgroup")
				.flashAttr("group", new Group("AA-00")))
		        .andDo(print())
				.andExpect(model().hasNoErrors())
				.andExpect(redirectedUrl("/cruds/groups"));
	}

	@Test
	public void whenUpdateGroupRequestToGroupControllerIsInvalid_thenErrorsPresent() throws Exception {
		mockMvc.perform(post("/cruds/groups/update")
				.flashAttr("group", new Group("Test")))
		        .andDo(print())
				.andExpect(model().attributeHasErrors("group"))
				.andExpect(model().attributeHasFieldErrors("group", "name"));
	}

	@Test
	public void whenUpdateGroupRequestToGroupControllerIsValid_thenCorrectResponse() throws Exception {
		mockMvc.perform(post("/cruds/groups/update")
				.flashAttr("group", new Group("AA-00")))
		        .andDo(print())
				.andExpect(model().hasNoErrors())
		        .andExpect(redirectedUrl("/cruds/groups"));
	}
}
