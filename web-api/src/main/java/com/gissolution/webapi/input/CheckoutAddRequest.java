package com.gissolution.webapi.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CheckoutAddRequest implements Serializable {

    @JsonProperty("deliveryPlainText")
    String deliveryAddressPlainText;

    @JsonProperty("quantity")
    String quantity;

    @JsonProperty("price")
    String price;

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
}
