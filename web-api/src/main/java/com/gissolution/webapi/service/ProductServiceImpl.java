package com.gissolution.webapi.service;

import com.gissolution.webapi.input.ProductsAddRequest;
import com.gissolution.webapi.output.Products;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.gissolution.webapi.output.generic.ProductBenefits;
import com.gissolution.webapi.output.generic.ProductVariants;
import com.gissolution.webdata.dao.*;
import com.gissolution.webdata.entity.*;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@RestController
public class ProductServiceImpl implements Serializable {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductBenefitsDao productBenefitsDao;

    @Autowired
    ProductVariantsDao productVariantsDao;

    @Autowired
    WareHouseDao wareHouseDao;

    @Autowired
    BrandDao brandDao;

    @Autowired
    CategoriesDao categoriesDao;

    @RequestMapping(value = "api/products", produces = "application/json", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<List<Products>>> getProducts(@RequestParam(name = "start", required = false)Integer start, @RequestParam(name = "upto", required = false) Integer upto) throws IOException {
        GenericResponse<List<Products>> genericResponse = new GenericResponse<>();
        try {
            if(start == null) {
                start = 0;
            }
            if(upto == null) {
                upto = 15;
            }
            Pageable pageable = PageRequest.of(start, upto, Sort.by("id"));
            Page<ProductEntity> productEntities = productDao.getProducts(pageable);
            boolean hasMorePages = true;
            if (productEntities.getTotalPages()>start) {
                hasMorePages = false;
            }
            List<Products> productsResponseList = new ArrayList<>();
            for (ProductEntity productEntity :productEntities.getContent()) {
                productsResponseList.add(getProductResponse(productEntity));
            }

            genericResponse.setError(false);
            genericResponse.setHasMorePage(hasMorePages);
            genericResponse.setResponse(productsResponseList);
            return new ResponseEntity<>(genericResponse, getHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            genericResponse.setError(true);
            genericResponse.setErrorMessage("Bad Input");
            return new ResponseEntity<>(genericResponse, getHeaders(), HttpStatus.BAD_REQUEST);
        }
    }
    MultiValueMap<String, String> getHeaders(){
        MultiValueMap<String, String> map = new HttpHeaders();
        map.set(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        return map;
    }

    private Products getProductResponse(ProductEntity productEntity) {
        Products products = new Products();
        products.setProductId(productEntity.getProductId());
        products.setName(productEntity.getName());
        products.setDescription(productEntity.getDescription());
        products.setTimestamp(productEntity.getTimeStamp());
        Set<ProductBenefits> productBenefitsSet = new LinkedHashSet<>();
        for (ProductBenefitsEntity productBenefitsEntity: productEntity.getProductBenefitsEntitySet()) {
            ProductBenefits productBenefits = new ProductBenefits();
            productBenefits.setProductBenefitId(productBenefitsEntity.getProductBenefitsId());
            productBenefits.setDescription(productBenefitsEntity.getDescription());
            productBenefits.setIcon(productBenefitsEntity.getIcon());
            productBenefits.setTitle(productBenefitsEntity.getTitle());
            productBenefitsSet.add(productBenefits);
        }
        products.setBenefits(productBenefitsSet);
        products.setBrandId(productEntity.getBrandEntity().getBrandId().toString());
        products.setCategoryId(productEntity.getCategoriesEntity().getId().toString());
        products.setType(productEntity.getType());
        products.setDryingTime(productEntity.getDryingTime());
        products.setWarranty(productEntity.getWarranty());
        products.setProtectorType(productEntity.getProtectorType());
        products.setFormula(productEntity.getFormula());
        products.setResistanceType(productEntity.getResistanceType());
        products.setQuickDry(productEntity.getQuickDry());
        products.setRemarks(productEntity.getRemarks());
        products.setReCoatingPeriod(productEntity.getReCoatingPeriod());
        products.setFinish(productEntity.getFinish());
        products.setDurability(productEntity.getDurability());
        products.setWashable(productEntity.getWashable());
        products.setAntiFungal(productEntity.getAntiFungal());
        products.setUserId(productEntity.getUserId());
        products.setWarehouseId(productEntity.getWarehouseEntity().getId());
        Set<ProductVariants> productVariantsSet = new LinkedHashSet<>();
        for (ProductVariantsEntity productVariantsEntity: productEntity.getProductVariantsEntitySet()) {
            ProductVariants productVariants = new ProductVariants();
            productVariants.setProductVariantId(productVariantsEntity.getProductVariantsId());
            productVariants.setAvailability(productVariantsEntity.getAvailability());
            productVariants.setCoverage(productVariantsEntity.getCoverage());
            productVariants.setPrice(productVariantsEntity.getPrice());
            productVariants.setColor(productVariantsEntity.getColor());
            productVariants.setColorHex(productVariantsEntity.getColorHex());
            productVariantsSet.add(productVariants);
        }
        products.setProductVariantsSet(productVariantsSet);
        return products;
    }

    @RequestMapping(value = "api/products", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody ResponseEntity<GenericResponse<?>> addProducts(@RequestBody ProductsAddRequest request) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        genericResponse.setResponse("Some error occured. Check your input");
        try {
            BrandEntity brandEntity = brandDao.getBrandByBrandId(UUID.fromString(request.getBrandId()));
            CategoriesEntity categoriesEntity = categoriesDao.getCategoryEntityById(UUID.fromString(request.getCategoryId()));
            WarehouseEntity warehouseEntity = wareHouseDao.getWareHouseEntityById(request.getWarehouseId());

            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(request.getName());
            productEntity.setDescription(request.getDescription());
            productEntity.setTimeStamp(System.currentTimeMillis());
            productEntity.setBrandEntity(brandEntity);
            productEntity.setCategoriesEntity(categoriesEntity);
            productEntity.setType(request.getType());
            productEntity.setDryingTime(request.getDryingTime());
            productEntity.setWarranty(request.getWarranty());
            productEntity.setProtectorType(request.getProtectorType());
            productEntity.setFormula(request.getFormula());
            productEntity.setResistanceType(request.getResistanceType());
            productEntity.setQuickDry(request.getQuickDry());
            productEntity.setRemarks(request.getRemarks());
            productEntity.setReCoatingPeriod(request.getReCoatingPeriod());
            productEntity.setFinish(request.getFinish());
            productEntity.setDurability(request.getDurability());
            productEntity.setWashable(request.getWashable());
            productEntity.setAntiFungal(request.getAntiFungal());
            productEntity.setWarehouseEntity(warehouseEntity);
            productEntity = productDao.save(productEntity);

            List<ProductBenefitsEntity> productBenefitsSet = new ArrayList<>();
            for (ProductBenefits productBenefit: request.getBenefits()) {
                ProductBenefitsEntity productBenefits = new ProductBenefitsEntity();
                productBenefits.setTitle(productBenefit.getTitle());
                productBenefits.setIcon(productBenefit.getTitle());
                productBenefits.setDescription(productBenefit.getDescription());
                productBenefits.setProductEntity(productEntity);
                productBenefits = productBenefitsDao.save(productBenefits);
                productBenefitsSet.add(productBenefits);
            }

            productEntity = productDao.getProductEntityById(productEntity.getProductId());
            productEntity.setProductBenefitsEntitySet(new LinkedHashSet<>(productBenefitsSet));

            List<ProductVariantsEntity> productVariantsEntityList = new ArrayList<>();
            for (ProductVariants productVariant : request.getProductVariants()) {
                ProductVariantsEntity productVariantsEntity = new ProductVariantsEntity();
                productVariantsEntity.setProductEntity(productEntity);
                productVariantsEntity.setAvailability(productVariant.getAvailability());
                productVariantsEntity.setColor(productVariant.getColor());
                productVariantsEntity.setPrice(productVariant.getPrice());
                productVariantsEntity.setCoverage(productVariant.getCoverage());
                productVariantsEntity.setColorHex(productVariant.getColorHex());
                productVariantsEntity = productVariantsDao.save(productVariantsEntity);
                productVariantsEntityList.add(productVariantsEntity);
            }
            productEntity.setProductVariantsEntitySet(new LinkedHashSet<>(productVariantsEntityList));

            return new ResponseEntity<>(new GenericResponse<>(getProductResponse(productEntity)), HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            genericResponse.setError(true);
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
