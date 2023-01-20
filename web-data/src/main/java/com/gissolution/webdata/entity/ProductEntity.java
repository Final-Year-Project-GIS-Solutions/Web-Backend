package com.gissolution.webdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "productId", columnDefinition = "uuid")
    UUID id;


    @Column(name = "name")
    String name;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "time_stamp")
    Long timeStamp;

    @OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
    Set<ProductBenefitsEntity> productBenefitsEntitySet;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    BrandEntity brandEntity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    CategoriesEntity categoriesEntity;

    @Column(name = "type")
    String type;

    @Column(name = "drying_time")
    String dryingTime;

    @Column(name = "warranty")
    String warranty;

    @Column(name = "protector_type")
    String protectorType;

    @Column(name = "formula")
    String formula;

    @Column(name = "resistance_type")
    String resistanceType;

    @Column(name = "quick_dry")
    Boolean quickDry;

    @Column(name = "remarks")
    String remarks;

    @Column(name = "re_coating_period")
    String reCoatingPeriod;

    @Column(name = "finish")
    String finish;

    @Column(name = "durability")
    String durability;

    @Column(name = "washable")
    String washable;

    @Column(name = "anti_fungal")
    Boolean antiFungal;

    @Column(name = "userId")
    String userId;

    @OneToOne
    @JoinColumn(name = "warehouse_id")
    WarehouseEntity warehouseEntity;

    @OneToMany
    @JoinColumn(name = "product_variants")
    Set<ProductVariantsEntity> productVariantsEntitySet;

    public UUID getProductId() {
        return id;
    }

    public void setProductId(UUID productId) {
        this.id = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Set<ProductBenefitsEntity> getProductBenefitsEntitySet() {
        return productBenefitsEntitySet;
    }

    public void setProductBenefitsEntitySet(Set<ProductBenefitsEntity> productBenefitsEntitySet) {
        this.productBenefitsEntitySet = productBenefitsEntitySet;
    }

    public BrandEntity getBrandEntity() {
        return brandEntity;
    }

    public void setBrandEntity(BrandEntity brandEntity) {
        this.brandEntity = brandEntity;
    }

    public CategoriesEntity getCategoriesEntity() {
        return categoriesEntity;
    }

    public void setCategoriesEntity(CategoriesEntity categoriesEntity) {
        this.categoriesEntity = categoriesEntity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDryingTime() {
        return dryingTime;
    }

    public void setDryingTime(String dryingTime) {
        this.dryingTime = dryingTime;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getProtectorType() {
        return protectorType;
    }

    public void setProtectorType(String protectorType) {
        this.protectorType = protectorType;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getResistanceType() {
        return resistanceType;
    }

    public void setResistanceType(String resistanceType) {
        this.resistanceType = resistanceType;
    }

    public Boolean getQuickDry() {
        return quickDry;
    }

    public void setQuickDry(Boolean quickDry) {
        this.quickDry = quickDry;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReCoatingPeriod() {
        return reCoatingPeriod;
    }

    public void setReCoatingPeriod(String reCoatingPeriod) {
        this.reCoatingPeriod = reCoatingPeriod;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getDurability() {
        return durability;
    }

    public void setDurability(String durability) {
        this.durability = durability;
    }

    public String getWashable() {
        return washable;
    }

    public void setWashable(String washable) {
        this.washable = washable;
    }

    public Boolean getAntiFungal() {
        return antiFungal;
    }

    public void setAntiFungal(Boolean antiFungal) {
        this.antiFungal = antiFungal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public WarehouseEntity getWarehouseEntity() {
        return warehouseEntity;
    }

    public void setWarehouseEntity(WarehouseEntity warehouseEntity) {
        this.warehouseEntity = warehouseEntity;
    }

    public Set<ProductVariantsEntity> getProductVariantsEntitySet() {
        return productVariantsEntitySet;
    }

    public void setProductVariantsEntitySet(Set<ProductVariantsEntity> productVariantsEntitySet) {
        this.productVariantsEntitySet = productVariantsEntitySet;
    }
}
