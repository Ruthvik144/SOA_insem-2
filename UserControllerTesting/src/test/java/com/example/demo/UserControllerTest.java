package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.UserController;
import com.example.demo.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

@Autowired
private MockMvc mockMvc;
@MockitoBean
private UserService userService;
// Success Test Case
@Test
public void testGetUser() throws Exception {
    when(userService.getUser()).thenReturn("John");
    mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().string("John"));
}
// Intentional Failure Test Case
@Test
public void testGetUserFailure() throws Exception {
    when(userService.getUser())
            .thenThrow(new RuntimeException("Service failed"));
    mockMvc.perform(get("/users"))
            .andExpect(status().isOk());
}

}