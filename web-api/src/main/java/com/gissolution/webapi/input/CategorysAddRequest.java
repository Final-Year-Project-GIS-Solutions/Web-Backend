package com.gissolution.webapi.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CategorysAddRequest implements Serializable {

    @JsonProperty("type")
    String type;

    @JsonProperty("image")
    String image;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
