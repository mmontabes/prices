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
                .andExpect(jsonPath("$.price").value(25.45));
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



    @Test
    void test1_priceAt10AM_June14() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.priceList").value(1));
    }

    @Test
    void test2_priceAt16PM_June14() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.priceList").value(2));
    }

    @Test
    void test3_priceAt21PM_June14() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.priceList").value(1));
    }

    @Test
    void test4_priceAt10AM_June15() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.priceList").value(3));
    }

    @Test
    void test5_priceAt21PM_June16() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.priceList").value(4));
    }
    @Test
    void shouldReturn404_whenNoPriceFound() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-20T10:00:00")  // out of range
                        .param("productId", "99999")           //non-existing product
                        .param("brandId", "999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400_whenMissingParameters() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14T10:00:00"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400_whenDateIsMalformed() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020/06/14 10:00")  // wrong format
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest());
    }
}
