package com.gissolution.webapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gissolution.webapi.output.Brands;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.gissolution.webdata.dao.BrandDao;
import com.gissolution.webdata.dao.ImagesDao;
import com.gissolution.webdata.dao.UserDao;
import com.gissolution.webdata.entity.BrandEntity;
import com.gissolution.webdata.entity.UserEntity;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BrandsServiceImpl {

    @Autowired
    BrandDao brandDao;

    @Autowired
    ImagesDao imagesDao;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/api/brands", produces = "application/json", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<List<Brands>>> getBrands(@RequestParam(name = "start", required = false)Integer start, @RequestParam(name = "upto", required = false) Integer upto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        GenericResponse<List<Brands>> genericResponse = new GenericResponse<>();
        try {
            if(start == null) {
                start = 0;
            }
            if(upto == null) {
                upto = 15;
            }
            Pageable pageable = PageRequest.of(start, upto, Sort.by("id"));
            Page<BrandEntity> brandEntities = brandDao.getBrand(pageable);
            boolean hasMorePages = brandEntities.getTotalPages()>start;
            List<Brands> brandsResponseList = new ArrayList<>();
            for (BrandEntity brandEntity :brandEntities.getContent()) {
                brandsResponseList.add(getBrandResponse(brandEntity));
            }
            genericResponse.setError(false);
            genericResponse.setHasMorePage(hasMorePages);
            genericResponse.setResponse(brandsResponseList);
            return new ResponseEntity<>(genericResponse, getHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            genericResponse.setError(false);
            genericResponse.setErrorMessage("Bad Input");
            return new ResponseEntity<>(genericResponse, getHeaders(), HttpStatus.BAD_REQUEST);

        }

    }
    MultiValueMap<String, String> getHeaders(){
        MultiValueMap<String, String> map = new HttpHeaders();
        map.set(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        return map;
    }

    private Brands getBrandResponse(BrandEntity brandEntity) {
        Brands brands = new Brands();
        brands.setBrandIcon(brandEntity.getImageEntity().getImageUrl());
        brands.setBrandName(brandEntity.getBrandName());
        brands.setBrandId(brandEntity.getBrandId().toString());
        brands.setTimeStamp(brandEntity.getTimeStamp());
        return  brands;
    }


}
