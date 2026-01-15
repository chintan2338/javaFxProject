package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductGroup;
import com.example.demo.repository.ProductGroupRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductGroupRepository groupRepository;
    @Autowired
    private ProductService service;
    // 1. GET: Saare products list (Page 1 Table ke liye)
    @GetMapping
    public List<Product> getAllProduct(){
        return service.getAll();
    }

    // 2. POST: Naya product add karne ke liye (Page 2 Save - New)
    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return service.save(product);
    }

    // 3. PUT: Existing product edit karne ke liye (Page 2 Save - Edit)
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return service.update(id,product);
    }

    // 4. DELETE: Product delete karne ke liye (Page 1 Delete)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.delete(id);
    }

}
