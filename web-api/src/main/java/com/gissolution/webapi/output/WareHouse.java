package com.gissolution.webapi.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class WareHouse implements Serializable {
    @JsonProperty("warehouseId")
    UUID id;

    @JsonProperty("addressId")
    UUID addressId;

    @JsonProperty("warehouseTitle")
    String wareHouseTitle;

    @JsonProperty("userId")
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public String getWareHouseTitle() {
        return wareHouseTitle;
    }

    public void setWareHouseTitle(String wareHouseTitle) {
        this.wareHouseTitle = wareHouseTitle;
    }
}
