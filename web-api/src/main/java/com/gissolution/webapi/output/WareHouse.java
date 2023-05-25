package com.gissolution.webapi.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class WareHouse implements Serializable {
    @JsonProperty("warehouseId")
    String id;

    @JsonProperty("address")
    String addressPlainText;

    @JsonProperty("warehouseTitle")
    String warehouseTitle;

    @JsonProperty("latitude")
    String latitude;

    @JsonProperty("longitude")
    String longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressPlainText() {
        return addressPlainText;
    }

    public void setAddressPlainText(String addressPlainText) {
        this.addressPlainText = addressPlainText;
    }

    public String getWarehouseTitle() {
        return warehouseTitle;
    }

    public void setWarehouseTitle(String warehouseTitle) {
        this.warehouseTitle = warehouseTitle;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
