package com.example.mypetshop.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/*  The Product entity represents an item we sell in our pet shop.
        Important distinction:PRODUCT vs PRODUCT VARIANT:
        - Product: The conceptual item (e.g., "Premium Dog Food")
        - Product Variant: Specific versions (e.g., "5kg bag", "15kg bag") */

@Entity
@Table(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;


    /*BASE PRICE STRATEGY (want to review it later because im not sure that its only good option)
     *
     * We use a base price + variant price modifier system:
     * - Base Price: Starting price for the product
     * - Variant Price Modifier: Additional cost for specific variants
     * - Final Price = Base Price + Variant Price Modifier
     *
     * EXAMPLE:
     * Product: "Dog Food" - Base Price: $45.99
     * Variant "5kg": Price Modifier: $0.00 → Final: $45.99
     * Variant "15kg": Price Modifier: $15.00 → Final: $60.99  */

    @Column(name = "base_price",nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @ManyToOne(fetch = FetchType.LAZY)      //CATEGORY RELATIONSHIP @ManyToOne - Many products can belong to One category
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /* LAZY LOADING vs EAGER LOADING:
        - LAZY: Category data is loaded only when accessed (better performance)
        - EAGER: Category data is loaded immediately with the product
     We use LAZY because we don't always need category info when loading products. */


    //PRODUCT VARIANTS RELATIONSHIP
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) //@OneToMany(mappedBy = "product") - One product has Many variants
    private List<ProductVariant> variants = new ArrayList<>();

    // ascade = CascadeType.ALL - Operations cascade to variants
    //orphanRemoval = true - If variant is removed from list, it's deleted from DB - NEED TO REVIEW IT!!!!!

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //constructors
    public Product() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
   /* DEFAULT CONSTRUCTOR
Creates a new Product object with no initial values
 - Sets createdAt and updatedAt to the current time
 - Leaves other fields as null/zero
 Sometimes we want to create an empty object and fill it gradually

WHY WE NEED IT:
     JPA REQUIREMENT: When JPA loads data from database, it:
   -Creates empty object using default constructor
    -Uses setters to populate the fields
    - Without this, JPA cannot create our objects! */

//PARAMETERIZED CONSTRUCTOR
    public Product(String name, String slug, String description, BigDecimal basePrice, Category category){
        this();
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.basePrice = basePrice;
        this.category = category;

    }
/*
 * PARAMETERIZED CONSTRUCTOR
 *
 * WHAT IT DOES:
 * - Creates a new Product with all essential fields pre-set
 * - Calls this() to ensure timestamps are initialized
 * - Sets the provided field values
 *
 * WHY WE NEED IT:
 * 1. CONVENIENCE: Create fully-configured objects in one line
 * 2. READABILITY: Clear what data is required to create a valid product
 * 3. SAFETY: Ensures required fields are provided upfront
 *
 * REAL-WORLD ANALOGY:
 * Like ordering a "combo meal" instead of ordering each item separately.
 *
 * EXAMPLE USAGE:
 * Product product = new Product(
 *     "Premium Dog Food",           // name
 *     "premium-dog-food",           // slug
 *     "High-quality nutrition",     // description
 *     new BigDecimal("45.99"),      // basePrice
 *     dogFoodCategory               // category
 *
 Product is ready to use immediately!
 */
































}
