package com.ktu.couriers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MockMvcController<T> {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private String baseUrl = "/api";

    public MockMvcController(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    public MockHttpServletResponse post(String url, T object) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(constructUrl(url))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(object)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
    }

    public MockHttpServletResponse get(String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(constructUrl(url)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
    }

    public MockHttpServletResponse put(String url, T object) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.put(constructUrl(url))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(object)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
    }

    public void delete(String url) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(constructUrl(url)))
                .andExpect(status().isOk());
    }

    private String constructUrl(String url) {
        return baseUrl + url;
    }

}
