package com.gissolution.webdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "product_entity")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    UUID id;


}
