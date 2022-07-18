package lv.nixx.poc.security.controller;

import lv.nixx.poc.security.model.ViewName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static lv.nixx.poc.security.service.TestUser.userWithAdminRole;
import static lv.nixx.poc.security.service.TestUser.userWithSimpleRole;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ViewDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(userWithAdminRole)
    void checkAdminUser() {
        assertAll(
                () -> sendRequestAndCheck(ViewName.View1, HttpStatus.OK, "View1 Data"),
                () -> sendRequestAndCheck(ViewName.ViewX, HttpStatus.FORBIDDEN, "For user [userWithAdminRole] view [ViewX] not allowed")
        );
    }

    @Test
    @WithUserDetails(userWithSimpleRole)
    void checkUser() throws Exception {
        sendRequestAndCheck(ViewName.ViewX, HttpStatus.OK, "ViewX Data");
    }

    private void sendRequestAndCheck(ViewName viewName, HttpStatus expectedStatus, String expectedResponse) throws Exception {
        mockMvc.perform(get("/view/data").param("viewName", viewName.name()))
                .andExpect(status().is(expectedStatus.value()))
                .andExpect(content().string(expectedResponse));
    }

}