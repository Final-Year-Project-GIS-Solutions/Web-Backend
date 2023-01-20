package com.gissolution.webapi.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gissolution.webapi.output.generic.AddressDetailOptions;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class AddressAddRequest implements Serializable {

    @JsonProperty("addressDetailOptions")
    List<AddressDetailOptions> addressDetailOptions;

    @JsonProperty("isWarehouse")
    boolean isWarehouse;

    @JsonProperty("addressTitle")
    String addressTitle;

    public List<AddressDetailOptions> getAddressDetailOptions() {
        return addressDetailOptions;
    }

    public void setAddressDetailOptions(List<AddressDetailOptions> addressDetailOptions) {
        this.addressDetailOptions = addressDetailOptions;
    }

    public boolean isWarehouse() {
        return isWarehouse;
    }

    public void setWarehouse(boolean warehouse) {
        isWarehouse = warehouse;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }
}
