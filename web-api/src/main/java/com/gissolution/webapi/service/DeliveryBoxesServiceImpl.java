package com.gissolution.webapi.service;

import com.gissolution.webapi.input.DeliveryBoxesAddRequest;
import com.gissolution.webapi.output.DeliveryBoxes;
import com.gissolution.webapi.output.Vehicles;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.gissolution.webdata.dao.DeliveryBoxesDao;
import com.gissolution.webdata.entity.DeliveryBoxesEntity;
import com.gissolution.webdata.entity.VehiclesEntity;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DeliveryBoxesServiceImpl implements Serializable {

    @Autowired
    DeliveryBoxesDao deliveryBoxesDao;

    @RequestMapping(value = "api/deliveryBoxes", produces = "application/json", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<List<DeliveryBoxes>>> getBoxes(@RequestParam(name = "start", required = false)Integer start, @RequestParam(name = "upto", required = false) Integer upto) throws IOException {
        GenericResponse<List<DeliveryBoxes>> genericResponse = new GenericResponse<>();
        try {
            if (start == null) {
                start = 0;
            }
            if (upto == null) {
                upto = 15;
            }
            Pageable pageable = PageRequest.of(start, upto, Sort.by("id"));
            Page<DeliveryBoxesEntity> deliveryBoxesEntities = deliveryBoxesDao.getBoxes(pageable);
            boolean hasMorePages = true;
            if (deliveryBoxesEntities.getTotalPages() > start) {
                hasMorePages = false;
            }
            List<DeliveryBoxes> deliveryBoxesList = new ArrayList<>();
            for (DeliveryBoxesEntity deliveryBoxesEntity : deliveryBoxesEntities.getContent()) {
                deliveryBoxesList.add(getBoxesResponse(deliveryBoxesEntity));
            }

            genericResponse.setError(false);
            genericResponse.setHasMorePage(hasMorePages);
            genericResponse.setResponse(deliveryBoxesList);
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

    private DeliveryBoxes getBoxesResponse(DeliveryBoxesEntity deliveryBoxesEntity) {
        DeliveryBoxes deliveryBoxes = new DeliveryBoxes();
        deliveryBoxes.setDeliveryBoxesId(deliveryBoxesEntity.getBoxesId());
        deliveryBoxes.setName(deliveryBoxesEntity.getBoxName());
        deliveryBoxes.setSize(deliveryBoxesEntity.getSize());
        deliveryBoxes.setWeight(deliveryBoxesEntity.getWeight());
        return deliveryBoxes;
    }

    @RequestMapping(value = "api/deliveryBoxes", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<?>> addBoxes(@RequestBody DeliveryBoxesAddRequest request) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        genericResponse.setResponse("Some Error occured. Check your input");
        try {
            DeliveryBoxesEntity deliveryBoxes = new DeliveryBoxesEntity();
            deliveryBoxes.setBoxName(request.getName());
            deliveryBoxes.setSize(request.getSize());
            deliveryBoxes.setWeight(request.getWeight());
            deliveryBoxes = deliveryBoxesDao.save(deliveryBoxes);

            return new ResponseEntity<>(new GenericResponse<>(getBoxesResponse(deliveryBoxes)), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            genericResponse.setError(true);
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
