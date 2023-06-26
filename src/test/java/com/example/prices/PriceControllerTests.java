package com.example.prices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFirstPrice() throws Exception {
        //Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId= 1;

        //When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/consulta-precios/consulta-precios?applicationDate="+applicationDate+"&productId="+productId+ "&brandId="+brandId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));


    }


    @Test
    public void testSecondtPrice() throws Exception {
        //Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        Integer productId = 35455;
        Integer brandId= 1;

        //When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/consulta-precios/consulta-precios?applicationDate="+applicationDate+"&productId="+productId+ "&brandId="+brandId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));


    }

    @Test
    public void testThirdPrice() throws Exception {
        //Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 21, 0);
        Integer productId = 35455;
        Integer brandId= 1;

        //When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/consulta-precios/consulta-precios?applicationDate="+applicationDate+"&productId="+productId+ "&brandId="+brandId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50));


    }

    @Test
    public void testFourthPrice() throws Exception {
        //Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        Integer productId = 35455;
        Integer brandId= 1;

        //When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/consulta-precios/consulta-precios?applicationDate="+applicationDate+"&productId="+productId+ "&brandId="+brandId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));


    }
    @Test
    public void testFifthPrice() throws Exception {
        //Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        Integer productId = 35455;
        Integer brandId= 1;

        //When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/consulta-precios/consulta-precios?applicationDate="+applicationDate+"&productId="+productId+ "&brandId="+brandId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));

    }

}