package com.gissolution.webdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "product_variants")
public class ProductVariantsEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "productVariantsId", columnDefinition = "uuid")
    UUID id;

    @Column(name = "availability")
    String availability;

    @Column(name = "coverage")
    String coverage;

    @Column(name = "price")
    String price;

    @Column(name = "color_name")
    String color;

    @Column(name = "color_hex")
    String colorHex;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity productEntity;

    public UUID getProductVariantsId() {
        return id;
    }

    public void setProductVariantsId(UUID productVariantsId) {
        this.id = productVariantsId;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
