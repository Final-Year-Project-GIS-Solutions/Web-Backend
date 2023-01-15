package com.gissolution.webapi.output;

import java.io.Serializable;
import java.util.Set;

public class Ads implements Serializable {
    Set<String> images;
    String title;
    String discountPercentage;
    Set<String> brandImages;

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
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

    public Set<String> getBrandImages() {
        return brandImages;
    }

    public void setBrandImages(Set<String> brandImages) {
        this.brandImages = brandImages;
    }
}
