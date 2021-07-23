package ua.com.foxminded.university.web.controllers.rest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import io.restassured.http.ContentType;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import ua.com.foxminded.university.Application;
import ua.com.foxminded.university.service.postgre.PostgreSqlGroupService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class GroupControllerAuthRestTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PostgreSqlGroupService groupService;

    @Test
    public void whenGetGroupsRequestToGroupControllerWithAuthIsValid_thenCorrectResponse() throws Exception {
        mockMvc(mockMvc);
        
        given().auth().with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
            .contentType(ContentType.JSON)
        .when()
            .get("/m.cruds/groups/1")
        .then()
            .assertThat()
            .statusCode(200);
    }
}
