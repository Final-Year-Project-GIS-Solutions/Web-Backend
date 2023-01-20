package com.gissolution.webdata.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressEntity implements Serializable{

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    UUID id;

    @OneToMany(mappedBy = "addressEntity", fetch = FetchType.EAGER)
    Set<AddressDetailEntity> addressesDetailList;

    @Column(name = "is_warehouse")
    Boolean isWarehouse;

    @Column(name = "address_title")
    String addressTitle;

    @Column(name = "user_id")
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID addressId) {
        this.id = addressId;
    }

    public Set<AddressDetailEntity> getAddressesDetailList() {
        return addressesDetailList;
    }

    public void setAddressesDetailList(Set<AddressDetailEntity> addressesDetailList) {
        this.addressesDetailList = addressesDetailList;
    }

    public Boolean getWarehouse() {
        return isWarehouse;
    }

    public void setWarehouse(Boolean warehouse) {
        isWarehouse = warehouse;
    }
}
