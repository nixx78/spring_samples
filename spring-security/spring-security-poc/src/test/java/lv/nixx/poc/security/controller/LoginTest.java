package lv.nixx.poc.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static lv.nixx.poc.security.service.TestUser.userWithAdminRole;
import static lv.nixx.poc.security.service.TestUser.userWithSimpleRole;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginSuccessTest() throws Exception {

        FormLoginRequestBuilder login = formLogin("/perform_login")
                .user(userWithAdminRole)
                .password("1");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername(userWithAdminRole))
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
    @WithUserDetails(userWithAdminRole)
    void getUserInfoTest() throws Exception {
        mockMvc.perform(get("/userInfo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value("userWithAdminRole"))
                .andExpect(jsonPath("$.roles").value("ROLE_ADMIN"));
    }

    @Test
    void accessSecuredResourceUnauthenticatedThenRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/method/adminEndpoint"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login_page"));
    }

}