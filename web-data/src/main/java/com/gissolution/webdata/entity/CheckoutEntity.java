package com.gissolution.webdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "checkout")
public class CheckoutEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "checkoutId", columnDefinition = "uuid")
    UUID id;

    @Column(name = "delivery_plain_text")
    String deliveryPlainText;

    @Column(name = "quantity")
    String quantity;

    @Column(name = "price")
    String price;

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

    public String getDeliveryPlainText() {
        return deliveryPlainText;
    }

    public void setDeliveryPlainText(String deliveryPlainText) {
        this.deliveryPlainText = deliveryPlainText;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
}
