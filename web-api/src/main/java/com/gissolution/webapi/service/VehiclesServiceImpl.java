package com.gissolution.webapi.service;

import com.gissolution.webapi.input.VehicleAddRequest;
import com.gissolution.webapi.output.Vehicles;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.gissolution.webdata.dao.VehiclesDao;
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
public class VehiclesServiceImpl implements Serializable {

    @Autowired
    VehiclesDao vehiclesDao;

    @RequestMapping(value = "api/vehicles", produces = "application/json", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<List<Vehicles>>> getVehicles(@RequestParam(name = "start", required = false)Integer start, @RequestParam(name = "upto", required = false) Integer upto) throws IOException {
        GenericResponse<List<Vehicles>> genericResponse = new GenericResponse<>();
        try {
            if (start == null) {
                start = 0;
            }
            if (upto == null) {
                upto = 15;
            }
            Pageable pageable = PageRequest.of(start, upto, Sort.by("id"));
            Page<VehiclesEntity> vehiclesEntities = vehiclesDao.getVehicles(pageable);
            boolean hasMorePages = true;
            if (vehiclesEntities.getTotalPages() > start) {
                hasMorePages = false;
            }
            List<Vehicles> vehiclesList = new ArrayList<>();
            for (VehiclesEntity vehiclesEntity : vehiclesEntities.getContent()) {
                vehiclesList.add(getVehiclesResponse(vehiclesEntity));
            }

            genericResponse.setError(false);
            genericResponse.setHasMorePage(hasMorePages);
            genericResponse.setResponse(vehiclesList);
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

    private Vehicles getVehiclesResponse(VehiclesEntity vehiclesEntity) {
        Vehicles vehicles = new Vehicles();
        vehicles.setVehicleNumber(vehiclesEntity.getVehicleNumber());
        vehicles.setCapacity(vehiclesEntity.getCapacity());
        vehicles.setVehicleId(vehiclesEntity.getVehicleId());
        vehicles.setName(vehiclesEntity.getName());
        vehicles.setDriverName(vehiclesEntity.getDriverName());
        vehicles.setDriverNumber(vehiclesEntity.getDriverNumber());
        return vehicles;
    }

    @RequestMapping(value = "api/vehicles", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<?>> addVehicles(@RequestBody VehicleAddRequest request) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        genericResponse.setResponse("Some Error occured. Check your input");
        try {
            VehiclesEntity vehiclesEntity = new VehiclesEntity();
            vehiclesEntity.setCapacity(request.getCapacity());
            vehiclesEntity.setVehicleNumber(request.getVehicleNumber());
            vehiclesEntity.setDriverName(request.getDriverName());
            vehiclesEntity.setDriverNumber(request.getDriverNumber());
            vehiclesEntity.setName(request.getName());
            vehiclesEntity = vehiclesDao.save(vehiclesEntity);

            return new ResponseEntity<>(new GenericResponse<>(getVehiclesResponse(vehiclesEntity)), HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            genericResponse.setError(true);
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
