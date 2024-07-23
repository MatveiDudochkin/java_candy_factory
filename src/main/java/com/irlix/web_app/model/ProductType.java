package com.irlix.web_app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @ManyToMany
    @JoinTable(name = "product_producttype",
            joinColumns = @JoinColumn(name = "producttype_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productTypes = new HashSet<>();
}
