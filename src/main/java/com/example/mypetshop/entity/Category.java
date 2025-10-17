package com.example.mypetshop.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String slug;  /* slug is a URL-friendly version of the name. Ex: Name: "Dog Food" → Slug: "dog-food"  */

    private  String description;

    @Column(name = "image_url")
    private String imageUrl; //This will store the path to category images, later w ill be added functionality to upload them

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    /*BIDIRECTIONAL RELATIONSHIP parent<->children
    "mappedBy = 'parent'" means: "The connection is managed by the 'parent' field in Category class"
     WHY BIDIRECTIONAL?
     - From a parent, we can get all its children: category.getChildren()
     - From a child, we can get its parent: category.getParent()
     - This makes navigation in both directions possible
     */

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private  List<Product> products = new ArrayList<>();

    /*
     CASCADE TYPES
     cascade = CascadeType.ALL means:
     If we save a category, its children are also saved
     If we delete a category, its children are also deleted
     */

    //constructors

    public Category(){}

    public Category(String name, String slug, String description) {
        this.name = name;
        this.slug = slug;
        this.description = description;}

        //adds child category and sets its parent
    public void addChildCategory(Category child){
        children.add(child);
        child.setParent(this);
    }
// Adds a product to this category and sets this category on the product
    public void addProduct(Product product){
        products.add(product);
        product.setCategory(this); //maintain the reverse relationship
    }

    //checking if its main category and has no parent
    public boolean isMainCategory() {
        return parent == null;
    }

    //checking if its subcategory - parent exist, is not null
    public boolean isSubCategory() {
        return parent != null;
    }

    //getters and setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getParent() {
        return parent;
    }
    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }
    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}














