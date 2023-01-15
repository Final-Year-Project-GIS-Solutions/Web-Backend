package com.gissolution.webapi.output.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BrandImages implements Serializable {
    @JsonProperty("image")
    String image;

    @JsonProperty("brand_name")
    String brandName;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
