package com.gissolution.webapi.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gissolution.webapi.output.generic.ProductBenefits;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class Products implements Serializable {

    @JsonProperty("product_id")
    UUID productId;

    @JsonProperty("name")
    String name;

    @JsonProperty("description")
    String description;

    @JsonProperty("timestamp")
    Long timestamp;

    @JsonProperty("benefits")
    Set<ProductBenefits> benefits;

    @JsonProperty("brand_id")
    String brandId;

    @JsonProperty("category_id")
    String categoryId;

    @JsonProperty("type")
    String type;

    @JsonProperty("drying_time")
    String dryingTime;

    @JsonProperty("warranty")
    String warranty;

    @JsonProperty("protector_type")
    String protectorType;

    @JsonProperty("formula")
    String formula;

    @JsonProperty("resistance_type")
    String resistanceType;

    @JsonProperty("quick_dry")
    Boolean quickDry;

    @JsonProperty("remarks")
    String remarks;

    @JsonProperty("re_coating_period")
    String reCoatingPeriod;

    @JsonProperty("finish")
    String finish;

    @JsonProperty("durability")
    String durability;

    @JsonProperty("washable")
    String washable;

    @JsonProperty("anti_fungal")
    Boolean antiFungal;

    @JsonProperty("user_id")
    String userId;

    @JsonProperty("warehouse_id")
    UUID warehouseId;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Set<ProductBenefits> getBenefits() {
        return benefits;
    }

    public void setBenefits(Set<ProductBenefits> benefits) {
        this.benefits = benefits;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }
}
