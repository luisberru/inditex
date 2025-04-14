package com.bcncgroup.inditex.adapter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCorrectPrice_whenValidRequestIsSent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("date", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").value(35455));
    }
}

