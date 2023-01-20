package com.gissolution.webapi.service;

import com.gissolution.webapi.output.WareHouse;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.gissolution.webdata.dao.AddressDao;
import com.gissolution.webdata.dao.UserDao;
import com.gissolution.webdata.dao.WareHouseDao;
import com.gissolution.webdata.entity.WarehouseEntity;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class WarehouseServiceImpl {


    private static final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    @Autowired
    WareHouseDao wareHouseDao;

    @Autowired
    AddressDao addressDao;

    @Autowired
    UserDao userDao;

    @Autowired
    Environment environment;

    @RequestMapping(value = "api/warehouse", produces = "application/json", method = RequestMethod.GET)
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public @ResponseBody ResponseEntity<GenericResponse<List<WareHouse>>> getWarehouse(@RequestParam(name = "start", required = false)Integer start, @RequestParam(name = "upto", required = false) Integer upto) throws IOException {
        GenericResponse<List<WareHouse>> genericResponse = new GenericResponse<>();
        try {
            if(start == null) {
                start = 0;
            }
            if(upto == null) {
                upto = 15;
            }
            Pageable pageable = PageRequest.of(start, upto, Sort.by("id"));
            Page<WarehouseEntity> warehouseEntities = wareHouseDao.getWareHouseEntity(pageable);
            boolean hasMorePages = true;
            if (warehouseEntities.getTotalPages()>start){
                hasMorePages = false;
            }
            List<WareHouse> wareHouseResponseList = new ArrayList<>();
            for (WarehouseEntity warehouseEntity :warehouseEntities.getContent()){
                wareHouseResponseList.add(getWarehouseResponse(warehouseEntity));
            }
            genericResponse.setError(false);
            genericResponse.setHasMorePage(hasMorePages);
            genericResponse.setResponse(wareHouseResponseList);
            return new ResponseEntity<>(genericResponse, getHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error("Issue Here:{}", e.getMessage() , e);
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

    private WareHouse getWarehouseResponse(WarehouseEntity warehouseEntity){
        WareHouse wareHouse = new WareHouse();
        wareHouse.setWareHouseTitle(warehouseEntity.getWarehouseTitle());
        wareHouse.setAddressId(warehouseEntity.getAddressEntity().getId());
        wareHouse.setId(warehouseEntity.getId());
        wareHouse.setUserId(warehouseEntity.getUserId());
        return wareHouse;
    }

}
