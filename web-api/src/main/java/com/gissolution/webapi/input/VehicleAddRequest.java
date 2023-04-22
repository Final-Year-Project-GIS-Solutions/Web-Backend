package com.gissolution.webapi.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class VehicleAddRequest implements Serializable {
    @JsonProperty("name")
    String name;

    @JsonProperty("vehicleNumber")
    String vehicleNumber;

    @JsonProperty("driverName")
    String driverName;

    @JsonProperty("driverNumber")
    String driverNumber;

    @JsonProperty("capacity")
    String capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
