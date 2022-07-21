package lv.nixx.poc.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static lv.nixx.poc.security.service.TestUser.userWithAdminRole;
import static lv.nixx.poc.security.service.TestUser.userWithSimpleRole;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerWithSecuredUrlsTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void nonSecuredHomeEndpoint() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/urlBased/home", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Success:home"));
    }

    @Test
    @WithUserDetails(userWithAdminRole)
    void accessEndpointAvailableForAdmin() throws Exception {
        mvc.perform(get("/urlBased/secured"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success:secured"));
    }

    @Test
    @WithUserDetails(userWithSimpleRole)
    void accessEndpointAvailableForAdminForbidden() throws Exception {
        mvc.perform(get("/urlBased/secured"))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""));
    }

    @Test
    @WithUserDetails(userWithSimpleRole)
    void basicSecuredIsAvailable() throws Exception {
        mvc.perform(get("/urlBased/basicSecured"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success:basicSecured"));
    }

    @Test
    @WithUserDetails(userWithAdminRole)
    void postActionIsAvailableForAdmin() throws Exception {
        mvc.perform(post("/urlBased/action").content("action.value"))
                .andExpect(status().isOk())
                .andExpect(content().string("Action: 'action.value' processed"));
    }

    @Test
    @WithUserDetails(userWithSimpleRole)
    void postActionNotAvailableForSimpleUser() throws Exception {
        mvc.perform(post("/urlBased/action").content("action"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(userWithSimpleRole)
    void getActionIsAvailableForSimpleUser() throws Exception {
        mvc.perform(get("/urlBased/action"))
                .andExpect(status().isOk())
                .andExpect(content().string("'Action' available"));
    }

    @Test
    @WithUserDetails(userWithAdminRole)
    void processActionWithPathParameterSuccess() throws Exception {
        mvc.perform(get("/urlBased/process/action1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success:action:action1"));
    }

    @Test
    @WithUserDetails(userWithAdminRole)
    void processActionWithPathParameterForbidden() throws Exception {
        mvc.perform(get("/urlBased/process/UNSUPPORTED_ACTION"))
                .andExpect(status().isForbidden());
    }


}
