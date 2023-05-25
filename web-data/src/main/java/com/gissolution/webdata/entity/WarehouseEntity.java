package com.gissolution.webdata.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "warehouse_info")
public class WarehouseEntity implements Serializable{
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    UUID id;

    @Column(name = "warehouse_title")
    String warehouseTitle;

    @Column(name = "address_plain_text")
    String addressPlainText;

    @Column(name = "latitude")
    String latitude;

    @Column(name = "longitude")
    String longitude;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWarehouseTitle() {
        return warehouseTitle;
    }

    public void setWarehouseTitle(String warehouseTitle) {
        this.warehouseTitle = warehouseTitle;
    }

    public String getAddressPlainText() {
        return addressPlainText;
    }

    public void setAddressPlainText(String addressPlainText) {
        this.addressPlainText = addressPlainText;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseEntity that = (WarehouseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
