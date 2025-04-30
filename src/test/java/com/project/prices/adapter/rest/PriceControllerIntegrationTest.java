package com.project.prices.adapter.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPrice_shouldReturnPrice_whenValidParams() throws Exception {
        String date = "2020-06-14T16:00:00"; // valid date in data.sql
        Long productId = 35455L;
        Long brandId = 1L;

        mockMvc.perform(get("/api/prices")
                        .param("date", date)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.price").value(35.5));
    }

    @Test
    void getPrice_shouldReturn404_whenNoPriceFound() throws Exception {
        String date = "2020-06-14T10:00:00";
        Long productId = 999L;
        Long brandId = 999L;

        mockMvc.perform(get("/api/prices")
                        .param("date", date)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isNotFound());
    }
}
