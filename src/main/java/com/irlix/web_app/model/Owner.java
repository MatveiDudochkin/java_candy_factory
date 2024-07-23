package com.irlix.web_app.model;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "owner")
public class Owner {
    private int id;
    private String name;
    private String surname;
    private int phone;
    private String email;
}
