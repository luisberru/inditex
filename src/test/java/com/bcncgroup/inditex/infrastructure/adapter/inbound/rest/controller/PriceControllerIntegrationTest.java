package com.bcncgroup.inditex.infrastructure.adapter.inbound.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

  @Autowired private MockMvc mockMvc;

  private final String url = "/prices";

  @Test
  void shouldReturnPriceForValidRequestAt10AMOnJune14() throws Exception {
    mockMvc
        .perform(
            get(url)
                .param("date", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList").value(1))
        .andExpect(jsonPath("$.price").value(35.50));
  }

  @Test
  void shouldReturnPriceForValidRequestAt4PMOnJune14() throws Exception {
    mockMvc
        .perform(
            get(url)
                .param("date", "2020-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList").value(2))
        .andExpect(jsonPath("$.price").value(25.45));
  }

  @Test
  void shouldReturnPriceForValidRequestAt9PMOnJune14() throws Exception {
    mockMvc
        .perform(
            get(url)
                .param("date", "2020-06-14T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList").value(1));
  }

  @Test
  void shouldReturnPriceForValidRequestAt10AMOnJune15() throws Exception {
    mockMvc
        .perform(
            get(url)
                .param("date", "2020-06-15T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList").value(3));
  }

  @Test
  void shouldReturnPriceForValidRequestAt9PMOnJune16() throws Exception {
    mockMvc
        .perform(
            get(url)
                .param("date", "2020-06-16T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList").value(4));
  }

  @Test
  void shouldReturnNotFoundForRequestBeforeJune14() throws Exception {
    mockMvc
        .perform(
            get(url)
                .param("date", "2020-06-13T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
        .andExpect(status().isNotFound());
  }
  //Otras pruebas de integración para el controlador PriceController

  @Test
  void shouldReturnCorrectPrice_whenValidRequestIsSent() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/prices")
                .param("date", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value(35455));
  }

  @Test
  void integrationTest_validRequest_shouldReturnPrice() throws Exception {
    mockMvc
        .perform(
            get("/prices")
                .param("date", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList").value(1))
        .andExpect(jsonPath("$.price").value(35.50));
  }

  @Test
  void integrationTest_invalidDateFormat_shouldReturnBadRequest() throws Exception {
    mockMvc
        .perform(
            get("/prices")
                .param("date", "invalid-date")
                .param("productId", "35455")
                .param("brandId", "1"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void integrationTest_missingParameters_shouldReturnBadRequest() throws Exception {
    mockMvc
        .perform(get("/prices").param("date", "2020-06-14T10:00:00"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void integrationTest_priceNotFound_shouldReturnNotFound() throws Exception {
    mockMvc
        .perform(
            get("/prices")
                .param("date", "2020-06-13T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
        .andExpect(status().isNotFound());
  }

  
}
