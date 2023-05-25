package com.gissolution.webdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products_demo")
public class ProductsDemoEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "productId", columnDefinition = "uuid")
    UUID id;

    @Column(name = "title")
    String title;

    @Column(name = "description", length = 3000, columnDefinition = "TEXT")
    String description;

    @Column(name = "images")
    @ElementCollection(targetClass = String.class)
    List<String> images;

    @Column(name = "price")
    String price;

    @Column(name = "sizes")
    @ElementCollection(targetClass = String.class)
    List<String> sizes;

    @Column(name = "colors")
    @ElementCollection(targetClass = String.class)
    List<String> colors;

    @Column(name = "quantity")
    String quantity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
