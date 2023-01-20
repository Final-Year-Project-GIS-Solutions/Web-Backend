package com.gissolution.webdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class CategoriesEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    UUID categoriesId;

    @Column(name = "type")
    String type;

    @Column(name = "image")
    String image;

    public UUID getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(UUID categoriesId) {
        this.categoriesId = categoriesId;
    }

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
