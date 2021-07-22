package com.example.springbootapp.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//////junit 4
//@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerTest {

    public static final String URI_TEMPLATE = "/hello-world";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void helloWorld_basic2() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get(URI_TEMPLATE + "/hello-world")
                .accept(MediaType.APPLICATION_JSON);

        String expected = "Hello World";

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void helloWorld_basic() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get(URI_TEMPLATE + "/hello-world")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        String expected = "Hello World";
        assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }
}