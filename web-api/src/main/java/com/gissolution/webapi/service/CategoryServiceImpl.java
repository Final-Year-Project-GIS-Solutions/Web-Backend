package com.gissolution.webapi.service;

import com.gissolution.webapi.input.CategorysAddRequest;
import com.gissolution.webapi.output.Categories;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.gissolution.webdata.dao.CategoriesDao;
import com.gissolution.webdata.entity.CategoriesEntity;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryServiceImpl implements Serializable {

    @Autowired
    CategoriesDao categoriesDao;

    @RequestMapping(value = "api/categories", produces = "application/json", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<List<Categories>>> getCategories(@RequestParam(name = "start", required = false)Integer start, @RequestParam(name = "upto", required = false) Integer upto) throws IOException {
        GenericResponse<List<Categories>> genericResponse = new GenericResponse<>();
        try {
            if(start == null) {
                start = 0;
            }
            if(upto == null) {
                upto = 15;
            }
            Pageable pageable = PageRequest.of(start, upto, Sort.by("id"));
            Page<CategoriesEntity> categoriesEntities = categoriesDao.getCategories(pageable);
            boolean hasMorePages = true;
            if (categoriesEntities.getTotalPages() > start) {
                hasMorePages = false;
            }
            List<Categories> categoriesResponseList = new ArrayList<>();
            for (CategoriesEntity categoriesEntity : categoriesEntities.getContent()) {
                categoriesResponseList.add(getCategoriesResponse(categoriesEntity));
            }
            genericResponse.setError(false);
            genericResponse.setHasMorePage(hasMorePages);
            genericResponse.setResponse(categoriesResponseList);
            return new ResponseEntity<>(genericResponse, getHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            genericResponse.setError(true);
            genericResponse.setErrorMessage("Bad Input");
            return new ResponseEntity<>(genericResponse, getHeaders(), HttpStatus.BAD_REQUEST);
        }

    }
    MultiValueMap<String, String> getHeaders(){
        MultiValueMap<String, String> map = new HttpHeaders();
        map.set(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        return map;
    }

    private Categories getCategoriesResponse(CategoriesEntity categoriesEntity) {
        Categories categories = new Categories();
        categories.setCategoryId(categoriesEntity.getCategoriesId().toString());
        categories.setImage(categoriesEntity.getImage());
        categories.setType(categoriesEntity.getType());
        return categories;
    }

    @RequestMapping(value = "api/categories", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<?>> addCategories(@RequestBody CategorysAddRequest request) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        genericResponse.setResponse("Some Error occured. Check your Input");
        try {
            CategoriesEntity categoriesEntity = new CategoriesEntity();
            categoriesEntity.setImage(request.getImage());
            categoriesEntity.setType(request.getType());
            categoriesEntity = categoriesDao.save(categoriesEntity);
            return new ResponseEntity<>(new GenericResponse<>(getCategoriesResponse(categoriesEntity)), HttpStatus.CREATED);
        } catch (Exception e)  {
            genericResponse.setError(true);
            genericResponse.setResponse(e.getMessage());
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
