package com.Desert.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "status")
    private String status;

}
