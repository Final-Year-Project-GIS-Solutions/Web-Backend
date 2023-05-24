package com.gissolution.webapi.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ProductDemoAddRequest implements Serializable {

    @JsonProperty("name")
    String name;

    @JsonProperty("price")
    String price;

    @JsonProperty("totalQuantity")
    String totalQuantity;

    @JsonProperty("brandName")
    String brandName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}