package com.crud.redes.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "headquarters")
@Data
public class Headquarters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String address;
    private String city;
    private Boolean active;
    private Boolean isPrimary;
}
