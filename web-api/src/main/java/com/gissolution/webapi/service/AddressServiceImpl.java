package com.gissolution.webapi.service;


import com.gissolution.webapi.input.AddressAddRequest;
import com.gissolution.webapi.output.Address;
import com.gissolution.webapi.output.generic.AddressDetailOptions;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.gissolution.webdata.dao.AddressDao;
import com.gissolution.webdata.dao.AddressDetailDao;
import com.gissolution.webdata.dao.UserDao;
import com.gissolution.webdata.dao.WareHouseDao;
import com.gissolution.webdata.entity.AddressDetailEntity;
import com.gissolution.webdata.entity.AddressEntity;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
public class AddressServiceImpl implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    AddressDao addressDao;

    @Autowired
    UserDao userDao;

    @Autowired
    Environment environment;

    @Autowired
    WareHouseDao wareHouseDao;

    @Autowired
    AddressDetailDao addressDetailDao;

    @RequestMapping(value = "api/address", produces = "application/json", method = RequestMethod.GET)
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public @ResponseBody ResponseEntity<GenericResponse<List<Address>>> getAddress(@RequestParam(name = "start", required = false)Integer start, @RequestParam(name = "upto", required = false) Integer upto) throws IOException {
        GenericResponse<List<Address>> genericResponse = new GenericResponse<>();
        try {
            if(start == null) {
                start = 0;
            }
            if(upto == null) {
                upto = 15;
            }
            Pageable pageable = PageRequest.of(start, upto, Sort.by("id"));
            Page<AddressEntity> addressEntities = addressDao.getAddress(pageable);
            boolean hasMorePages = true;
            if (addressEntities.getTotalPages()>start){
                hasMorePages = false;
            }
            List<Address> addressesResponseList = new ArrayList<>();
            for (AddressEntity addressEntity :addressEntities.getContent()){
                addressesResponseList.add(getAddressResponse(addressEntity));
            }

            genericResponse.setError(false);
            genericResponse.setHasMorePage(hasMorePages);
            genericResponse.setResponse(addressesResponseList);
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


    private Address getAddressResponse(AddressEntity addressEntity) {
        Address address = new Address();
        address.setAddressId(addressEntity.getId());
        List<AddressDetailOptions> addressDetailOptionsList = new ArrayList<>();
        for (AddressDetailEntity iterator : addressEntity.getAddressesDetailList()){
            AddressDetailOptions addressDetailOptions = new AddressDetailOptions();
            addressDetailOptions.setPostalCode(iterator.getPostalCode());
            addressDetailOptions.setStreet(iterator.getStreet());
            addressDetailOptions.setState(iterator.getState());
            addressDetailOptions.setLastName(iterator.getLastName());
            addressDetailOptions.setPhone(iterator.getPhone());
            addressDetailOptions.setCountry(iterator.getCountry());
            addressDetailOptions.setCity(iterator.getCity());
            addressDetailOptions.setAddressUrl(iterator.getAddressUrl());
            addressDetailOptions.setFirstName(iterator.getFirstName());
            addressDetailOptionsList.add(addressDetailOptions);
        }

        address.setAddressDetailOptions(new LinkedHashSet<>(addressDetailOptionsList));
        address.setAddressTitle(addressEntity.getAddressTitle());
        address.setUserId(addressEntity.getUserId());
        address.setWarehouse(addressEntity.getWarehouse());
        return address;
    }

    @RequestMapping(value = "api/address", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<?>> addAddress(@RequestBody AddressAddRequest request) {
        GenericResponse<String> genericResponseError = new GenericResponse<>();
        genericResponseError.setResponse("Some error occured. Please check your input.");
        try {
            if (request.getAddressTitle() == null || request.getAddressDetailOptions() == null) {
                throw new IllegalArgumentException("Some error occured.");
            }

            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setAddressTitle(request.getAddressTitle());
            addressEntity.setWarehouse(request.isWarehouse());
            addressEntity.setUserId(null);
            addressEntity = addressDao.save(addressEntity);

            AddressDetailEntity addressDetailEntity = new AddressDetailEntity();
            List<AddressDetailEntity> addressDetailEntitiesSet = new ArrayList<>();
            for (AddressDetailOptions action : request.getAddressDetailOptions()) {
                addressDetailEntity.setAddressUrl(action.getAddressUrl());
                addressDetailEntity.setCity(action.getCity());
                addressDetailEntity.setCountry(action.getCountry());
                addressDetailEntity.setFirstName(action.getFirstName());
                addressDetailEntity.setPhone(action.getPhone());
                addressDetailEntity.setLastName(action.getLastName());
                addressDetailEntity.setState(action.getState());
                addressDetailEntity.setStreet(action.getStreet());
                addressDetailEntity.setPostalCode(action.getPostalCode());
                addressDetailEntity.setAddressEntity(addressEntity);
                addressDetailEntity = addressDetailDao.save(addressDetailEntity);
                addressDetailEntitiesSet.add(addressDetailEntity);
            }


            addressEntity = addressDao.getAddressById(addressEntity.getId());

            addressEntity.setAddressesDetailList(new HashSet<>(addressDetailEntitiesSet));

            WarehouseEntity warehouseEntity = new WarehouseEntity();
            if (request.isWarehouse()){
                warehouseEntity.setAddressEntity(addressEntity);
                warehouseEntity.setUserId(null);
                warehouseEntity.setWarehouseTitle(request.getAddressTitle());
                warehouseEntity = wareHouseDao.save(warehouseEntity);
            }


            return new ResponseEntity<>(new GenericResponse<>(getAddressResponse(addressEntity)), HttpStatus.CREATED);

        }catch (IllegalArgumentException e){
            logger.error("Issue Here:{}", e.getMessage() , e);
            String error = e.getMessage();
            genericResponseError.setErrorMessage(error);
            genericResponseError.setError(true);
            e.printStackTrace();
            return new ResponseEntity<>(genericResponseError, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Issue Here:{}", e.getMessage() , e);
            e.printStackTrace();
            return new ResponseEntity<>(genericResponseError, HttpStatus.BAD_REQUEST);
        }
    }
}
