package com.ktu.couriers.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

public class MockCRUDController<T>{

    private final MockMvcController<T> mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final Class<T> responseType;


    public MockCRUDController(Class<T> responseType, MockMvc mockMvc) {
        this.responseType = responseType;
        this.mockMvc = new MockMvcController<>(mockMvc);
    }

    public T post(String url, T object) throws Exception {
        String json = this.mockMvc.post(url, object).getContentAsString();
        return fromJson(json, responseType);
    }

    public List<T> list(String url) throws Exception {
        String json = this.mockMvc.get(url).getContentAsString();
        return fromJsonToList(json, responseType);
    }

    public List<T> search(String url) throws Exception {
        String json = this.mockMvc.get(url).getContentAsString();
        return fromJsonToList(json, responseType);
    }

    public T get(String url) throws Exception {
        String json = this.mockMvc.get(url).getContentAsString();
        return fromJson(json, responseType);
    }

    public T put(String url, T object) throws Exception {
        String json = this.mockMvc.put(url, object).getContentAsString();
        return fromJson(json, responseType);
    }

    public void delete(String url) throws Exception {
        mockMvc.delete(url);
    }

    private <R> R fromJson(String json, Class<R> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    private <R> List<R> fromJsonToList(String json, Class<R> clazz) throws JsonProcessingException {
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return objectMapper.readValue(json, type);
    }
}
