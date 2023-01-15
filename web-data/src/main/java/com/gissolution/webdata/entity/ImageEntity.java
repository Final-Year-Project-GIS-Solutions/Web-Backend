package com.gissolution.webdata.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "images")
public class ImageEntity implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    UUID id;

    @Column(name="image_url")
    String imageUrl;

    @Column(name="storage_type")
    String storageType;

    @Column(name="file_type")
    String fileType;

    @Column(name="original_name")
    String originalNameName;

    @Column(name = "Original_file_type")
    String originalFileType;


    public UUID getId() {
        return id;
    }

    public void setId(UUID imageId) {
        this.id = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getOriginalNameName() {
        return originalNameName;
    }

    public void setOriginalNameName(String originalNameName) {
        this.originalNameName = originalNameName;
    }

    public String getOriginalFileType() {
        return originalFileType;
    }

    public void setOriginalFileType(String originalFileType) {
        this.originalFileType = originalFileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageEntity)) return false;
        ImageEntity that = (ImageEntity) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

