package com.gissolution.webapi.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class DeliveryBoxes implements Serializable {
    @JsonProperty("deliveryboxesId")
    UUID deliveryBoxesId;

    @JsonProperty("name")
    String name;

    @JsonProperty("size")
    String size;

    @JsonProperty("weight")
    String weight;

    public UUID getDeliveryBoxesId() {
        return deliveryBoxesId;
    }

    public void setDeliveryBoxesId(UUID deliveryBoxesId) {
        this.deliveryBoxesId = deliveryBoxesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
