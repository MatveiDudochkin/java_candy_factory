package com.irlix.web_app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "supplier")
public class Supplier {
    private int id;
    private String name;
    private String address;
}
