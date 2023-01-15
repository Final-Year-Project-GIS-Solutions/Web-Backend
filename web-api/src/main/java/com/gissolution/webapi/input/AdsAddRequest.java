package com.gissolution.webapi.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gissolution.webapi.output.generic.BrandImages;

import java.io.Serializable;
import java.util.Set;

public class AdsAddRequest implements Serializable {
    @JsonProperty("image")
    Set<String> image;

    @JsonProperty("title")
    String title;

    @JsonProperty("discount_percentage")
    String discountPercentage;

    @JsonProperty("brandImages")
    Set<BrandImages> brandImages;

    public Set<String> getImage() {
        return image;
    }

    public void setImage(Set<String> image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Set<BrandImages> getBrandImages() {
        return brandImages;
    }

    public void setBrandImages(Set<BrandImages> brandImages) {
        this.brandImages = brandImages;
    }
}
