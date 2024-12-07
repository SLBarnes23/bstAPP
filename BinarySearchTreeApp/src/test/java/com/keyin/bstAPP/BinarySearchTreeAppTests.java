package com.keyin.bstAPP;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BinarySearchTreeAppTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testBuildTreeEndpoint() throws Exception {
        String inputNumbers = "[10,5,15,3,7]";

        mockMvc.perform(post("/tree/build")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputNumbers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inputNumbers").value("[10, 5, 15, 3, 7]"))
                .andExpect(jsonPath("$.treeStructure").value(containsString("\"value\":10")))
                .andExpect(jsonPath("$.treeStructure").value(containsString("\"left\":{\"value\":5")))
                .andExpect(jsonPath("$.treeStructure").value(containsString("\"right\":{\"value\":15")));
    }

    @Test
    void testBuildTreeWithSingleNode() throws Exception {
        String inputNumbers = "[10]";

        mockMvc.perform(post("/tree/build")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputNumbers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inputNumbers").value("[10]"))
                .andExpect(jsonPath("$.treeStructure").value(containsString("\"value\":10")))
                .andExpect(jsonPath("$.treeStructure").value(containsString("\"left\":null")))
                .andExpect(jsonPath("$.treeStructure").value(containsString("\"right\":null")));
    }

    @Test
    void testInvalidInputForBuildTreeEndpoint() throws Exception {
        String invalidInput = "invalid,data";

        mockMvc.perform(post("/tree/build")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidInput))
                .andExpect(status().isBadRequest());
    }
}
