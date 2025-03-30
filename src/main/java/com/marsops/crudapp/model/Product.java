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
    /**
     * The unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The unique identifier for the product.
     */
    private String name;

    /**
     * The available quantity of the product.
     */
    private int quantity;

    /**
     * The type of resource (e.g., Oxygen, Food, Fuel).
     */
    private String resourceType;

    /**
     * A detailed description of the product.
     */
    private static final int DESCRIPTION_MAX_LENGTH = 1024;
    // Then use:
    @Column(length = DESCRIPTION_MAX_LENGTH)
    private String description;

    /**
     * The date and time when the product was last restocked.
     */
    private LocalDateTime lastRestockDate;

    /**
     * Determines if the product is in a critical state.
     * For example, if the quantity is less than a critical threshold
     * (here, 10), the resource might need urgent replenishment.
     *
     * @return true if quantity is less than 10, false otherwise.
     */
    private static final int CRITICAL_QUANTITY = 10;

    @Transient
    public boolean isCritical() {
        return this.quantity < CRITICAL_QUANTITY;
    }
}
