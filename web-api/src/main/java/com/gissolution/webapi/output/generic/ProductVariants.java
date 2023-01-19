package com.gissolution.webapi.output.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ProductVariants implements Serializable {

    @JsonProperty("availability")
    String availability;

    @JsonProperty("coverage")
    String coverage;

    @JsonProperty("price")
    String price;

    @JsonProperty("color")
    String color;

    @JsonProperty("color_hex")
    String colorHex;

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
