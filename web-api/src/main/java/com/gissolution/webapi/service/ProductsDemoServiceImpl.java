package com.gissolution.webapi.service;

import com.gissolution.webapi.input.ProductDemoAddRequest;
import com.gissolution.webapi.output.ProductDemo;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.gissolution.webapi.serviceFireStore.ProductsServiceFireStore;
import com.gissolution.webdata.dao.ProductDemoDao;
import com.gissolution.webdata.entity.ProductsDemoEntity;
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
import java.util.concurrent.ExecutionException;

@RestController
public class ProductsDemoServiceImpl implements Serializable {

    @Autowired
    ProductDemoDao productDemoDao;

    @Autowired
    ProductsServiceFireStore productsServiceFireStore;

    @RequestMapping(value = "api/firebase/products", produces = "application/json", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<List<ProductDemo>>> getProducts(@RequestParam(name = "start", required = false)Integer start, @RequestParam(name = "upto", required = false) Integer upto) throws IOException {
        GenericResponse<List<ProductDemo>> genericResponse = new GenericResponse<>();
        try {
            if (start == null) {
                start = 0;
            }
            if (upto == null) {
                upto = 15;
            }
            Pageable pageable = PageRequest.of(start, upto, Sort.by("id"));
            Page<ProductsDemoEntity> productsDemoEntities = productDemoDao.getProducts(pageable);
            boolean hasMorePAges = true;
            if (productsDemoEntities.getTotalPages()>start) {
                hasMorePAges = false;
            }
            List<ProductDemo> productDemoResponseList = new ArrayList<>();
            for (ProductsDemoEntity productsDemoEntity :productsDemoEntities.getContent()) {
                productDemoResponseList.add(getProductDemoResponse(productsDemoEntity));
            }

            genericResponse.setResponse(productDemoResponseList);
            genericResponse.setError(false);
            genericResponse.setHasMorePage(hasMorePAges);
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

    private ProductDemo getProductDemoResponse(ProductsDemoEntity productsDemoEntity) {
        ProductDemo productDemo = new ProductDemo();
        productDemo.setName(productsDemoEntity.getName());
        productDemo.setPrice(productsDemoEntity.getPrice());
        productDemo.setBrandName(productsDemoEntity.getBrandName());
        productDemo.setProductId(productsDemoEntity.getId().toString());
        productDemo.setTotalQuantity(productsDemoEntity.getTotalQuantity());
        return productDemo;
    }

    @RequestMapping(value = "api/firebase/products", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<?>> addProductsDemo(@RequestBody ProductDemoAddRequest request) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        genericResponse.setResponse("Some Error Occured. Check your input");
        try {
            ProductsDemoEntity productsDemoEntity = new ProductsDemoEntity();
            productsDemoEntity.setBrandName(request.getBrandName());
            productsDemoEntity.setTotalQuantity(request.getTotalQuantity());
            productsDemoEntity.setName(request.getName());
            productsDemoEntity.setPrice(request.getPrice());
            productsDemoEntity = productDemoDao.save(productsDemoEntity);

            ProductDemo productDemo = new ProductDemo();
            productDemo.setTotalQuantity(productsDemoEntity.getTotalQuantity());
            productDemo.setName(productsDemoEntity.getName());
            productDemo.setPrice(productsDemoEntity.getPrice());
            productDemo.setBrandName(productsDemoEntity.getBrandName());
            productDemo.setProductId(productsDemoEntity.getId().toString());

            productsServiceFireStore.postProducts(productDemo);
            return new ResponseEntity<>(new GenericResponse<>(getProductDemoResponse(productsDemoEntity)), HttpStatus.CREATED);
        } catch (Exception e) {
            genericResponse.setError(true);
            genericResponse.setResponse(e.getMessage());
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "api/firebase/products", method = RequestMethod.GET)
    public GenericResponse<List<ProductDemo>> getProductsDemo() throws ExecutionException, InterruptedException {
        return productsServiceFireStore.getProducts();
    }
}
