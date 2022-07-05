package lv.nixx.poc.security.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MethodLevelPermissionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginSuccessTest() throws Exception {
        FormLoginRequestBuilder login = formLogin("/perform_login")
                .user("admin")
                .password("1");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("admin"))
                .andExpect(redirectedUrl("/swagger-ui.html"));

    }

    @Test
    void loginWithInvalidUserThenUnauthenticated() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user("NOT_EXISTING_USER")
                .password("INVALID_PASSWORD");

        mockMvc.perform(login)
                .andExpect(unauthenticated());
    }

    @Test
    void accessUnsecuredResourceThenOk() throws Exception {
        mockMvc.perform(get("/endpointForAll"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success:methodForAll"));
    }

    @Test
    void accessSecuredResourceUnauthenticatedThenRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/adminEndpoint"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login_page"));
    }

    @Test
    @WithUserDetails("admin")
    void accessEndpointAvailableForAdmin() throws Exception {
        mockMvc.perform(get("/adminEndpoint"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success:methodForAdmin"));

    }

    @Test
    @WithUserDetails("user")
    void accessEndpointAvailableForAdminWithWrongUser() throws Exception {
        mockMvc.perform(get("/adminEndpoint"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("user")
    void accessEndpointAvailableForUser() throws Exception {
        mockMvc.perform(get("/userEndpoint"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success:methodForUser"));
    }


}