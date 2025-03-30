package com.marsops.crudapp.controller;

import com.marsops.crudapp.model.Product;
import com.marsops.crudapp.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Dependency Injection: Spring injects an instance of ProductRepository
    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    // GET /products: Returns a list of all products
    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }

    // POST /products: Creates a new product
    @PostMapping
    public Product create(@RequestBody Product product) {
        // Log a fun message for our MarsOps mission!
        System.out.println("🚀 New product received: " + product.getName());
        return repo.save(product);
    }

    // PUT /products/{id}: Updates an existing product
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product updated) {
        return repo.findById(id)
            .map(product -> {
                product.setName(updated.getName());
                product.setQuantity(updated.getQuantity());
                System.out.println("🛰 Updating product: " + product.getName());
                return repo.save(product);
            })
            .orElseThrow();  // Throws an exception if product not found.
    }

    // DELETE /products/{id}: Deletes a product
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        System.out.println("🛑 Deleting product with id: " + id);
        repo.deleteById(id);
    }
}
