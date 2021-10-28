package com.jumia.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.models.Customer;
import com.jumia.objects.CountryCustomerPhone;
import com.jumia.services.CountryCustomerPhoneService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryCustomerPhoneController.class)
public class CountryCustomerPhoneControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    CountryCustomerPhoneService countryCustomerPhoneService;

    CountryCustomerPhone ccp1 = new CountryCustomerPhone("Morocco", "212", Boolean.TRUE, "(212) 698054317");
    CountryCustomerPhone ccp2 = new CountryCustomerPhone("Morocco", "212", Boolean.FALSE, "(212) 6617344445");
    CountryCustomerPhone ccp3 = new CountryCustomerPhone("Mozambique", "258", Boolean.TRUE, "(258) 846565883");
    CountryCustomerPhone ccp4 = new CountryCustomerPhone("Mozambique", "258", Boolean.FALSE, "(258) 84330678235");
    CountryCustomerPhone ccp5 = new CountryCustomerPhone("Uganda", "265", Boolean.TRUE, "(256) 704244430");
    CountryCustomerPhone ccp6 = new CountryCustomerPhone("Uganda", "265", Boolean.FALSE, "(256) 7503O6263");
    CountryCustomerPhone ccp7 = new CountryCustomerPhone("Ethiopia", "251", Boolean.TRUE, "(251) 966002259");
    CountryCustomerPhone ccp8 = new CountryCustomerPhone("Ethiopia", "251", Boolean.FALSE, "(251) 9119454961");
    CountryCustomerPhone ccp9 = new CountryCustomerPhone("Cameroon", "237", Boolean.TRUE, "(237) 673122155");
    CountryCustomerPhone ccp10 = new CountryCustomerPhone("Cameroon", "237", Boolean.FALSE, "(237) 6780009592");

    @Test
    public void listAllPhones_Success() throws Exception {
        List<CountryCustomerPhone> countryCustomerPhones = new ArrayList<>(Arrays.asList(ccp1, ccp2, ccp3, ccp4, ccp5, ccp6, ccp7, ccp8, ccp9, ccp10));

        Mockito.when(countryCustomerPhoneService.listAll()).thenReturn(countryCustomerPhones);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/phonevalidation/phones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[3].state", is(Boolean.FALSE)));
    }

    @Test
    public void listAllWithFilters_Valid_Success() throws Exception {
        CountryCustomerPhone ccp = new CountryCustomerPhone("Morocco", null, Boolean.TRUE, null);
        List<CountryCustomerPhone> countryCustomerPhones = new ArrayList<>(Arrays.asList(ccp1));

        Mockito.when(countryCustomerPhoneService.listAllWithFilters(ccp)).thenReturn(countryCustomerPhones);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/phonevalidation/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(ccp));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].phoneNumber", is("(212) 698054317")));
    }

    @Test
    public void listAllWithFilters_InValid_Success() throws Exception {
        CountryCustomerPhone ccp = new CountryCustomerPhone("Mozambique", null, Boolean.FALSE, null);
        List<CountryCustomerPhone> countryCustomerPhones = new ArrayList<>(Arrays.asList(ccp4));

        Mockito.when(countryCustomerPhoneService.listAllWithFilters(ccp)).thenReturn(countryCustomerPhones);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/phonevalidation/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(ccp));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].phoneNumber", is("(258) 84330678235")));
    }

    @Test
    public void listAllWithFilters_ValidAndInValid_Success() throws Exception {
        CountryCustomerPhone ccp = new CountryCustomerPhone("Cameroon", null, null, null);
        List<CountryCustomerPhone> countryCustomerPhones = new ArrayList<>(Arrays.asList(ccp9, ccp10));

        Mockito.when(countryCustomerPhoneService.listAllWithFilters(ccp)).thenReturn(countryCustomerPhones);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/phonevalidation/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(ccp));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].phoneNumber", is("(237) 673122155")));
    }

    @Test
    public void listAllWithFilters_AllValid_Success() throws Exception {
        CountryCustomerPhone ccp = new CountryCustomerPhone(null, null, Boolean.TRUE, null);
        List<CountryCustomerPhone> countryCustomerPhones = new ArrayList<>(Arrays.asList(ccp1, ccp3, ccp5, ccp7, ccp9));

        Mockito.when(countryCustomerPhoneService.listAllWithFilters(ccp)).thenReturn(countryCustomerPhones);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/phonevalidation/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(ccp));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].phoneNumber", is("(212) 698054317")));
    }

    @Test
    public void listAllWithFilters_AllInValid_Success() throws Exception {
        CountryCustomerPhone ccp = new CountryCustomerPhone(null, null, Boolean.FALSE, null);
        List<CountryCustomerPhone> countryCustomerPhones = new ArrayList<>(Arrays.asList(ccp2, ccp4, ccp6, ccp8, ccp10));

        Mockito.when(countryCustomerPhoneService.listAllWithFilters(ccp)).thenReturn(countryCustomerPhones);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/phonevalidation/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(ccp));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].phoneNumber", is("(212) 6617344445")));
    }

    @Test
    public void listAllWithFilters_Success() throws Exception {
        CountryCustomerPhone ccp = new CountryCustomerPhone(null, null, null, null);
        List<CountryCustomerPhone> countryCustomerPhones = new ArrayList<>(Arrays.asList(ccp1, ccp2, ccp3, ccp4, ccp5, ccp6, ccp7, ccp8, ccp9, ccp10));

        Mockito.when(countryCustomerPhoneService.listAllWithFilters(ccp)).thenReturn(countryCustomerPhones);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/phonevalidation/phones")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(ccp));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].phoneNumber", is("(212) 698054317")));
    }
}
