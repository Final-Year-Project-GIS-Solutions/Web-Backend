package com.gissolution.webapi.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Checkout implements Serializable {

    @JsonProperty("checkoitId")
    String checkoutId;

    @JsonProperty("deliveryPlainText")
    String deliveryAddressPlainText;

    @JsonProperty("quantity")
    String quantity;

    @JsonProperty("price")
    String price;

    @JsonProperty("latitude")
    String latitude;

    @JsonProperty("longitude")
    String longitude;

    public String getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(String checkoutId) {
        this.checkoutId = checkoutId;
    }

    public String getDeliveryAddressPlainText() {
        return deliveryAddressPlainText;
    }

    public void setDeliveryAddressPlainText(String deliveryAddressPlainText) {
        this.deliveryAddressPlainText = deliveryAddressPlainText;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
