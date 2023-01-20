package com.gissolution.webapi.output;

import com.gissolution.webapi.output.generic.AddressDetailOptions;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class Address implements Serializable {

    UUID addressId;
    String addressTitle;
    Set<AddressDetailOptions> addressDetailOptions;
    boolean isWarehouse;

    String userId;

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public Set<AddressDetailOptions> getAddressDetailOptions() {
        return addressDetailOptions;
    }

    public void setAddressDetailOptions(Set<AddressDetailOptions> addressDetailOptions) {
        this.addressDetailOptions = addressDetailOptions;
    }

    public boolean isWarehouse() {
        return isWarehouse;
    }

    public void setWarehouse(boolean warehouse) {
        isWarehouse = warehouse;
    }
}
