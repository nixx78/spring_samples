package lv.nixx.poc.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static lv.nixx.poc.security.service.TestUser.userWithAdminRole;
import static lv.nixx.poc.security.service.TestUser.userWithSimpleRole;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MethodLevelPermissionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void accessUnsecuredResourceThenOk() throws Exception {
        mockMvc.perform(get("/method/endpointForAll"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success:methodForAll"));
    }

    @Test
    @WithUserDetails(userWithAdminRole)
    void accessEndpointAvailableForAdmin() throws Exception {
        mockMvc.perform(get("/method/adminEndpoint"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success:methodForAdmin"));

    }

    @Test
    @WithUserDetails(userWithSimpleRole)
    void accessEndpointAvailableForAdminWithWrongUser() throws Exception {
        mockMvc.perform(get("/method/adminEndpoint"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(userWithSimpleRole)
    void accessEndpointAvailableForUser() throws Exception {
        mockMvc.perform(get("/method/userEndpoint"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success:methodForUser"));
    }


}