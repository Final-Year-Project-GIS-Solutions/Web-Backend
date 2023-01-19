package com.gissolution.webapi.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Brands implements Serializable {
    @JsonProperty("brand_id")
    String brandId;

    @JsonProperty("brand_name")
    String brandName;

    @JsonProperty("brand_icon")
    String brandIcon;

    @JsonProperty("time_stamp")
    Long timeStamp;

    @JsonProperty("user_id")
    String userId;

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandIcon() {
        return brandIcon;
    }

    public void setBrandIcon(String brandIcon) {
        this.brandIcon = brandIcon;
    }
}
