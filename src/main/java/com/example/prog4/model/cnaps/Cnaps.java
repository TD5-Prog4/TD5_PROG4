package com.example.prog4.model.cnaps;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "cnaps")
public class Cnaps {
    @Id
    private int id;

    private String number;
}
