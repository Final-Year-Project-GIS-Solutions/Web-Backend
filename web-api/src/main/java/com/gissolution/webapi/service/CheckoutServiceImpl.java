package com.gissolution.webapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.gissolution.webapi.input.CheckoutAddRequest;
import com.gissolution.webapi.output.Checkout;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.gissolution.webapi.serviceFireStore.CheckoutServiceFireStore;
import com.gissolution.webdata.dao.CheckoutDao;
import com.gissolution.webdata.dao.ProductDemoDao;
import com.gissolution.webdata.entity.CheckoutEntity;
import com.gissolution.webdata.entity.ProductsDemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class CheckoutServiceImpl implements Serializable {

    @Autowired
    CheckoutDao checkoutDao;

    @Autowired
    ProductDemoDao productDemoDao;

    @Autowired
    CheckoutServiceFireStore checkoutServiceFireStore;

    private final RestTemplate restTemplate;

    public CheckoutServiceImpl(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    @RequestMapping(value = "api/firebase/checkout", method = RequestMethod.GET)
    public GenericResponse<List<Checkout>> getCheckout() throws ExecutionException, InterruptedException {
        return checkoutServiceFireStore.getCheckout();
    }

    private Checkout getCheckoutResponse(CheckoutEntity checkoutEntity) {
        Checkout checkout = new Checkout();
        checkout.setQuantity(checkoutEntity.getQuantity());
        checkout.setPrice(checkoutEntity.getPrice());
        checkout.setLongitude(checkoutEntity.getLongitude());
        checkout.setLatitude(checkoutEntity.getLatitude());
        checkout.setDeliveryAddressPlainText(checkoutEntity.getDeliveryPlainText());
        checkout.setCheckoutId(checkoutEntity.getId().toString());
        return checkout;
    }

    @RequestMapping(value = "api/firebase/checkout", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<?>> addCheckout(@RequestBody CheckoutAddRequest request) throws ExecutionException, InterruptedException {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        genericResponse.setResponse("Some Error Occured. Check your input");
        try {

            String url = "http://localhost:5000/gisSolution/textToLatLong?text="+request.getDeliveryAddressPlainText();
            JsonNode latLong =  this.restTemplate.getForObject(url, JsonNode.class);
            ArrayNode arrayList = (ArrayNode) latLong.get("location");

            CheckoutEntity checkoutEntity = new CheckoutEntity();
            checkoutEntity.setDeliveryPlainText(request.getDeliveryAddressPlainText());
            checkoutEntity.setQuantity(request.getQuantity());
            checkoutEntity.setPrice(request.getPrice());
            checkoutEntity.setLatitude(arrayList.get(0).toString());
            checkoutEntity.setLongitude(arrayList.get(1).toString());
            checkoutEntity = checkoutDao.save(checkoutEntity);

            Checkout checkout = new Checkout();
            checkout.setCheckoutId(checkoutEntity.getId().toString());
            checkout.setDeliveryAddressPlainText(checkoutEntity.getDeliveryPlainText());

            checkout.setLatitude(arrayList.get(0).toString());
            checkout.setLongitude(arrayList.get(1).toString());
            checkout.setQuantity(checkoutEntity.getQuantity());
            checkout.setPrice(checkoutEntity.getPrice());

            checkoutServiceFireStore.postCheckout(checkout);
            return new ResponseEntity<>(new GenericResponse<>(getCheckoutResponse(checkoutEntity)), HttpStatus.CREATED);
        } catch (Exception e) {
            genericResponse.setError(true);
            genericResponse.setResponse(e.getMessage());
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
