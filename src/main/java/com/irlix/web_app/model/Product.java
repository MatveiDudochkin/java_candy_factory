package com.irlix.web_app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "calories")
    private int calories;
    @Column(name = "compound")
    private String compound;

    @ManyToMany
    @JoinTable(name = "product_producttype",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "producttype_id"))
    private Set<ProductType> productTypes = new HashSet<>();
}
