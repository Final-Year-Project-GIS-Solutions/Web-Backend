package com.gissolution.webdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "product_benefits")
public class ProductBenefitsEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "productBenefitsId", columnDefinition = "uuid")
    UUID id;

    @Column(name = "title")
    String title;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "icon")
    String icon;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity productEntity;

    public UUID getProductBenefitsId() {
        return id;
    }

    public void setProductBenefitsId(UUID productBenefitsId) {
        this.id = productBenefitsId;
    }

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

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
