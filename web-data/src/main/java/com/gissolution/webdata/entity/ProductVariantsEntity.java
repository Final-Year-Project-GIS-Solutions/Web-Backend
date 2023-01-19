package com.gissolution.webdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "product_variant_entity")
public class ProductVariantsEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    UUID id;


}
