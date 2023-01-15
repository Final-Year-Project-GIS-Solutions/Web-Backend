package com.gissolution.webdata.entity.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gissolution.webdata.entity.enumval.FieldType;

import javax.persistence.AttributeConverter;
import java.util.Map;

public class FieldValueConverter implements AttributeConverter<Map<String, FieldType>, String> {


    static transient ObjectMapper objectMapper = null;


    static ObjectMapper getObjectMapper() {
        if(objectMapper == null){
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }


    @Override
    public String convertToDatabaseColumn(Map<String, FieldType> stringFieldTypeMap) {
        try {
            return getObjectMapper().writeValueAsString(stringFieldTypeMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, FieldType> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s,  new TypeReference<Map<String, FieldType>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
