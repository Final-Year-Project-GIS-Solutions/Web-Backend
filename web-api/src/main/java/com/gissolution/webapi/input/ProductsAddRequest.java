package com.gissolution.webapi.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gissolution.webapi.output.generic.ProductBenefits;
import com.gissolution.webapi.output.generic.ProductVariants;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ProductsAddRequest implements Serializable {

    @JsonProperty("name")
    String name;

    @JsonProperty("description")
    String description;

    @JsonProperty("timestamp")
    Long timestamp;

    @JsonProperty("benefits")
    Set<ProductBenefits> benefits;

    @JsonProperty("brandId")
    String brandId;

    @JsonProperty("categoryId")
    String categoryId;

    @JsonProperty("type")
    String type;

    @JsonProperty("dryingTime")
    String dryingTime;

    @JsonProperty("warranty")
    String warranty;

    @JsonProperty("protectorType")
    String protectorType;

    @JsonProperty("formula")
    String formula;

    @JsonProperty("resistanceType")
    String resistanceType;

    @JsonProperty("quickDry")
    Boolean quickDry;

    @JsonProperty("remarks")
    String remarks;

    @JsonProperty("reCoatingPeriod")
    String reCoatingPeriod;

    @JsonProperty("finish")
    String finish;

    @JsonProperty("durability")
    String durability;

    @JsonProperty("washable")
    String washable;

    @JsonProperty("antiFungal")
    Boolean antiFungal;

    @JsonProperty("userId")
    String userId;

    @JsonProperty("warehouseId")
    UUID warehouseId;

    @JsonProperty("variants")
    Set<ProductVariants> productVariants;

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

    public Set<ProductVariants> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(Set<ProductVariants> productVariants) {
        this.productVariants = productVariants;
    }
}
