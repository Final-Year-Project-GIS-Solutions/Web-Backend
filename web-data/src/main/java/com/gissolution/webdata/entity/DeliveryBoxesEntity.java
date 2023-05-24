package com.gissolution.webdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "delivery_boxes_info")
public class DeliveryBoxesEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "boxes_id", columnDefinition = "uuid")
    UUID boxesId;

    @Column(name = "name")
    String boxName;

    @Column(name = "size")
    String size;

    @Column(name = "weight")
    String weight;

    public UUID getBoxesId() {
        return boxesId;
    }

    public void setBoxesId(UUID boxesId) {
        this.boxesId = boxesId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
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
