package com.gissolution.webapi.output.generic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GenericResponse<T> implements Serializable {

    T response;

    Boolean hasMorePage;

    Boolean isError;

    Integer errorStatus;

    String errorMessage;

    public GenericResponse(T r) {
        this.response = r;
        this.isError = false;
        this.errorMessage = null;
        this.hasMorePage = false;
    }

    public GenericResponse() {

    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Boolean getHasMorePage() {
        return hasMorePage;
    }

    public void setHasMorePage(Boolean hasMorePage) {
        this.hasMorePage = hasMorePage;
    }

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public Integer getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(Integer errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return "";
        }
    }
}
