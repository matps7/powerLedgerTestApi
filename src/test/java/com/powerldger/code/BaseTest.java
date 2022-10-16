package com.powerldger.code;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestConfiguration.class})
public abstract class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    //GET
    protected void getTestApplicationJson(String URI, String jsonLineTest, MediaType mediaType) throws Exception {
        mockMvc.perform(get(URI)
                        .accept(mediaType))
                .andExpect(content().json(jsonLineTest))
                .andExpect(status().isOk());
    }

    protected void getTestIsStatusOk(String URI, MediaType mediaType) throws Exception {
        mockMvc.perform(get(URI)
                        .accept(mediaType))
                .andExpect(status().isOk());
    }

    //POST
    protected void postTestIsStatusCreated(String URI, String requestJson, MediaType mediaType) throws Exception {
        mockMvc.perform(post(URI)
                        .contentType(mediaType)
                        .content(requestJson))
                .andExpect(status().isCreated());
    }
    protected void postTestIsStatus5xx(String URI, String requestJson, MediaType mediaType) throws Exception {
        mockMvc.perform(post(URI)
                        .contentType(mediaType)
                        .content(requestJson))
                .andExpect(status().is5xxServerError());
    }


}
