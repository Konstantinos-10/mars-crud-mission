package com.marsops.crudapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int quantity;

    private String resourceType;

    @Column(length = 1024)
    private String description;

    private LocalDateTime lastRestockDate;

    /**
     * Determines if the product is in a critical state.
     * For example, if the quantity is less than a critical threshold (here, 10),
     * the resource might need urgent replenishment.
     *
     * @return true if quantity is less than 10, false otherwise.
     */
    @Transient  // This field is not persisted in the database.
    public boolean isCritical() {
        return this.quantity < 10;
    }
}
