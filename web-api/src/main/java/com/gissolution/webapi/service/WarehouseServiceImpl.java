package com.gissolution.webapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.gissolution.webapi.input.WareHouseAddRequest;
import com.gissolution.webapi.output.WareHouse;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.gissolution.webapi.serviceFireStore.WarehouseServiceFireStore;
import com.gissolution.webdata.dao.WareHouseDao;
import com.gissolution.webdata.entity.WarehouseEntity;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
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
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
public class WarehouseServiceImpl {


    private static final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    @Autowired
    WareHouseDao wareHouseDao;


    @Autowired
    WarehouseServiceFireStore warehouseServiceFireStore;

    private final RestTemplate restTemplate;

    public WarehouseServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

//    @RequestMapping(value = "api/warehouse", produces = "application/json", method = RequestMethod.GET)
//    @Transactional(rollbackFor = {Exception.class, Error.class})
//    public @ResponseBody ResponseEntity<GenericResponse<List<WareHouse>>> getWarehouse(@RequestParam(name = "start", required = false)Integer start, @RequestParam(name = "upto", required = false) Integer upto) throws IOException {
//        GenericResponse<List<WareHouse>> genericResponse = new GenericResponse<>();
//        try {
//            if(start == null) {
//                start = 0;
//            }
//            if(upto == null) {
//                upto = 15;
//            }
//            Pageable pageable = PageRequest.of(start, upto, Sort.by("id"));
//            Page<WarehouseEntity> warehouseEntities = wareHouseDao.getWareHouseEntity(pageable);
//            boolean hasMorePages = false;
//            if (warehouseEntities.getTotalPages()>start){
//                hasMorePages = true;
//            }
//            List<WareHouse> wareHouseResponseList = new ArrayList<>();
//            for (WarehouseEntity warehouseEntity :warehouseEntities.getContent()){
//                wareHouseResponseList.add(getWarehouseResponse(warehouseEntity));
//            }
//            genericResponse.setError(false);
//            genericResponse.setHasMorePage(hasMorePages);
//            genericResponse.setResponse(wareHouseResponseList);
//            return new ResponseEntity<>(genericResponse, getHeaders(), HttpStatus.ACCEPTED);
//        } catch (Exception e) {
//            logger.error("Issue Here:{}", e.getMessage() , e);
//            genericResponse.setError(false);
//            genericResponse.setErrorMessage("Bad Input");
//            return new ResponseEntity<>(genericResponse, getHeaders(), HttpStatus.BAD_REQUEST);
//        }
//    }

    MultiValueMap<String, String> getHeaders(){
        MultiValueMap<String, String> map = new HttpHeaders();
        map.set(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        return map;
    }

    private WareHouse getWarehouseResponse(WarehouseEntity warehouseEntity){
        WareHouse wareHouse = new WareHouse();
        wareHouse.setAddressPlainText(warehouseEntity.getAddressPlainText());
        wareHouse.setId(warehouseEntity.getId().toString());
        wareHouse.setWarehouseTitle(warehouseEntity.getWarehouseTitle());
        wareHouse.setLatitude(warehouseEntity.getLatitude());
        wareHouse.setLongitude(warehouseEntity.getLongitude());
        return wareHouse;
    }

    @RequestMapping(value = "api/firebase/warehouse", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<?>> addWarehouseFirebase(@RequestBody WareHouseAddRequest request) throws ExecutionException, InterruptedException {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        genericResponse.setResponse("Some Error Occured. Check your Input");
        try {

            String url = "http://localhost:5000/gisSolution/textToLatLong?text="+request.getAddressPlainText();
            JsonNode latLong =  this.restTemplate.getForObject(url, JsonNode.class);
            ArrayNode arrayList = (ArrayNode) latLong.get("location");

            WarehouseEntity warehouseEntity = new WarehouseEntity();
            warehouseEntity.setAddressPlainText(request.getAddressPlainText());
            warehouseEntity.setWarehouseTitle(request.getWarehouseTitle());
            warehouseEntity.setLatitude(arrayList.get(0).toString());
            warehouseEntity.setLongitude(arrayList.get(1).toString());
            warehouseEntity = wareHouseDao.save(warehouseEntity);

            WareHouse wareHouse = new WareHouse();
            wareHouse.setId(warehouseEntity.getId().toString());
            wareHouse.setWarehouseTitle(warehouseEntity.getWarehouseTitle());
            wareHouse.setAddressPlainText(warehouseEntity.getAddressPlainText());
            wareHouse.setLatitude(warehouseEntity.getLatitude());
            wareHouse.setLongitude(warehouseEntity.getLongitude());

            warehouseServiceFireStore.postWarehouse(wareHouse);
            return new ResponseEntity<>(new GenericResponse<>(getWarehouseResponse(warehouseEntity)), HttpStatus.CREATED);
        } catch (Exception e) {
            genericResponse.setError(true);
            genericResponse.setResponse(e.getMessage());
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "api/firebase/warehouse", method = RequestMethod.GET)
    public GenericResponse<List<WareHouse>> getWareHouseFirebase() throws ExecutionException, InterruptedException {
        return warehouseServiceFireStore.getWarehouse();
    }

}
