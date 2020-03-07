package io.billie.rest.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract public class BaseModel {

  @Override
  public String toString() {
    String str = "";
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      str = objectMapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return str;
  }
}