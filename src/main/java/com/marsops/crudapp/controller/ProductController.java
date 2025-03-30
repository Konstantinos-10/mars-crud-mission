package com.marsops.crudapp.controller;

import com.marsops.crudapp.model.Product;
import com.marsops.crudapp.repository.ProductRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * The repository for accessing product data.
     */    
    private final ProductRepository repo;

    /**
     * Constructs a new ProductController with the specified repository.
     *
     * @param repo the product repository
     */
    public ProductController(final ProductRepository productRepository) {
        this.repo = productRepository;
    }
    

    /**
     * Retrieves a list of all products.
     *
     * @return a list of products
     */
    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }

    /**
     * Creates a new product.
     *
     * @param product the product to create (must not be null)
     * @return the created product
     */
    @PostMapping
    public Product create(final @RequestBody Product product) {
        System.out.println("🚀 New product received: " + product.getName());
        return repo.save(product);
    }

    /**
     * Updates an existing product.
     *
     * @param id the ID of the product to update
     * @param updated the new product data
     * @return the updated product
     */
    @PutMapping("/{id}")
    public Product update(final @PathVariable Long id, final @RequestBody Product updated) {
        return repo.findById(id)
                   .map(product -> {
                       product.setName(updated.getName());
                       product.setQuantity(updated.getQuantity());
                       System.out.println("🛰 Updating product: " + product.getName());
                       return repo.save(product);
                   })
                   .orElseThrow();
    }

    /**
     * Deletes the product with the specified ID.
     *
     * @param id the ID of the product to delete
     */
    @DeleteMapping("/{id}")
    public void delete(final @PathVariable Long id) {
        System.out.println("🛑 Deleting product with id: " + id);
        repo.deleteById(id);
    }
}
