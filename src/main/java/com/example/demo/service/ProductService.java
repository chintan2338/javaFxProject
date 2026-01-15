package com.example.demo.service;

import com.example.demo.entity.ProductGroup;
import com.example.demo.repository.ProductGroupRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductGroupRepository groupRepository;

    // 1. Get All Products
    public List<Product> getAll(){
        return repository.findAll();
    }

    // 2. Save New Product
    public Product save(Product product){

        ProductGroup incomingGroup = product.getProductGroup();

        if(incomingGroup != null){
            Optional<ProductGroup> existingGroup = groupRepository.findByGroupNameIgnoreCase(incomingGroup.getGroupName());

            if(existingGroup.isPresent()){
                product.setProductGroup(existingGroup.get());
            }
        }

        return repository.save(product);
    }

    // 3. Update Product
    public Product update(Long id,Product product){

        Optional<Product> optionalProduct = repository.findById(id);

        if(optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();

            existingProduct.setProductName(product.getProductName());
            existingProduct.setIsAvailable(product.getIsAvailable());

            ProductGroup incomingGroup = product.getProductGroup();

            if(incomingGroup != null){
                Optional<ProductGroup> dbGroup = groupRepository.findByGroupNameIgnoreCase(incomingGroup.getGroupName());

                if(dbGroup.isPresent()){
                    existingProduct.setProductGroup(dbGroup.get());
                }else {
                    existingProduct.setProductGroup(incomingGroup);
                }
            }
            return repository.save(existingProduct);
        }

        return null;
    }

    // 4. Delete Product
    public void delete(Long id){
        repository.deleteById(id);
    }


}
