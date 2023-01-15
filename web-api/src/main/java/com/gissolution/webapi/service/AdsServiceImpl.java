package com.gissolution.webapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gissolution.webapi.output.Ads;
import com.gissolution.webapi.output.generic.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class AdsServiceImpl {

    @RequestMapping(value = "/api/ads", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<GenericResponse<List<Ads>>> getAds() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        GenericResponse<List<Ads>> value = objectMapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("ads.json"), new GenericResponse<List<Ads>>().getClass());
        return new ResponseEntity<GenericResponse<List<Ads>>>(value, HttpStatus.ACCEPTED);
    }

}
