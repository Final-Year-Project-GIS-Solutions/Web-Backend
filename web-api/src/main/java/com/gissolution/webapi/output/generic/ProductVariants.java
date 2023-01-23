package com.gissolution.webapi.output.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class ProductVariants implements Serializable {

    @JsonProperty("productVariantId")
    UUID productVariantId;

    @JsonProperty("availability")
    String availability;

    @JsonProperty("coverage")
    String coverage;

    @JsonProperty("price")
    String price;

    @JsonProperty("color")
    String color;

    @JsonProperty("colorHex")
    String colorHex;

    public UUID getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(UUID productVariantId) {
        this.productVariantId = productVariantId;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }
}
