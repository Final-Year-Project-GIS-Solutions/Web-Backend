package com.gissolution.webapi.output.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ProductBenefits implements Serializable {

    @JsonProperty("title")
    String title;

    @JsonProperty("description")
    String description;

    @JsonProperty("icon")
    String icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
