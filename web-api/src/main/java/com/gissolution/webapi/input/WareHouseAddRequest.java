package com.gissolution.webapi.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WareHouseAddRequest implements Serializable {
    @JsonProperty("address")
    String addressPlainText;

    @JsonProperty("warehouseTitle")
    String warehouseTitle;

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
}
