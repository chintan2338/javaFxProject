package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {//Owning Side
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Boolean isAvailable;

    // Mapping: Many Products can belong to One Group
    //@ManyToOne(cascade = CascadeType.ALL)
    // ✅ Humne sirf PERSIST (Save) aur MERGE (Update) rakha hai. REMOVE hata diya.
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "group_id")
    private ProductGroup productGroup;
}
